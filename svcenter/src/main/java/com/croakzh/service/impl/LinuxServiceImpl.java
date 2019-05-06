package com.croakzh.service.impl;

import com.croakzh.core.LinuxCommands;
import com.croakzh.core.SystemTypeEnum;
import com.croakzh.core.context.ActionContext;
import com.croakzh.core.entity.CpuInfo;
import com.croakzh.core.entity.IOStatInfo;
import com.croakzh.core.entity.MemoryInfo;
import com.croakzh.core.entity.vo.ServerVo;
import com.croakzh.core.utils.ParseUtils;
import com.croakzh.core.utils.ShellUtils;
import com.croakzh.service.AbstractSystemService;
import com.croakzh.service.Validator;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author croakzh
 */
@Service
@Slf4j
public class LinuxServiceImpl extends AbstractSystemService {

    @Override
    public CpuInfo getCpuInfo(ServerVo serverVo) throws Exception {
        String host = serverVo.getHost();
        Session session = ActionContext.getSession(host);
        CpuInfo cpuInfo = new CpuInfo();
        List<String> lines = ShellUtils.execCmd(session, LinuxCommands.CAT_CPUINFO);
        CpuInfo.CpuDetailInfo cpuDetail = new CpuInfo.CpuDetailInfo();
        for (String line : lines) {
            if (StringUtils.isEmpty(line) && cpuDetail.getCacheSize() != 0L) {
                cpuInfo.addCpu(cpuDetail);
                cpuDetail = new CpuInfo.CpuDetailInfo();
            }
            String[] splitLine = ParseUtils.whitespacesColonWhitespace.split(line);
            if (splitLine.length < 2) {
                continue;
            }
            String content = splitLine[1];
            switch (splitLine[0]) {
                case "vendor_id":
                    cpuDetail.setVendor(content);
                    break;
                case "model name":
                    cpuDetail.setModel(content);
                    break;
                case "cache size":
                    cpuDetail.setCacheSize(Long.parseLong(content.replace("KB", "").trim()));
                    break;
                case "cpu MHz":
                    cpuDetail.setMhz(Integer.parseInt(content.substring(0, content.indexOf("."))));
                    break;
                default:
                    break;
            }
        }
        if (cpuDetail.getCacheSize() != 0L) {
            cpuInfo.addCpu(cpuDetail);
        }
        cpuInfo.setTotalCores(cpuInfo.getPerCpus().size());

        List<String> lines2 = ShellUtils.execCmd(session, LinuxCommands.LS_CPU);
        for (String line : lines2) {
            String[] splitLine = ParseUtils.whitespacesColonLastWhitespace.split(line);
            if (splitLine.length < 2) {
                continue;
            }
            String content = splitLine[1].trim();
            switch (splitLine[0].trim()) {
                case "Socket(s)":
                    cpuInfo.setTotalSockets(Integer.valueOf(content));
                    break;
                case "Core(s) per socket":
                    cpuInfo.setCoresPerSocket(Integer.valueOf(content));
                    break;
                default:
                    break;
            }
        }

        return cpuInfo;
    }

    @Override
    public MemoryInfo getMemInfo(ServerVo serverVo) throws Exception {
        String host = serverVo.getHost();
        Session session = ActionContext.getSession(host);
        MemoryInfo memoryInfo = new MemoryInfo();
        List<String> lines = ShellUtils.execCmd(session, LinuxCommands.CAT_MEMINFO);
        boolean found = false;
        for (String line : lines) {
            String[] memorySplit = ParseUtils.whitespaces.split(line);
            if (memorySplit.length > 1) {
                switch (memorySplit[0]) {
                    case "MemTotal":
                        memoryInfo.setMemTotal(parseKbLong(memorySplit[1]));
                        break;
                    case "MemFree":
                        memoryInfo.setMemFree(parseKbLong(memorySplit[1]));
                        break;
                    case "MemAvailable":
                        memoryInfo.setMemAvailable(parseKbLong(memorySplit[1]));
                        found = true;
                        break;
                    case "Active(file)":
                        memoryInfo.setActiveFile(parseKbLong(memorySplit[1]));
                        break;
                    case "Inactive(file)":
                        memoryInfo.setInactiveFile(parseKbLong(memorySplit[1]));
                        break;
                    case "SReclaimable":
                        memoryInfo.setSReclaimable(parseKbLong(memorySplit[1]));
                        break;
                    case "SwapTotal":
                        memoryInfo.setSwapTotal(parseKbLong(memorySplit[1]));
                        break;
                    case "SwapFree":
                        memoryInfo.setSwapFree(parseKbLong(memorySplit[1]));
                        break;
                    default:
                        break;
                }
                memoryInfo.setSwapUsed(memoryInfo.getSwapTotal() - memoryInfo.getSwapFree());
                if (!found) {
                    memoryInfo.setMemAvailable(memoryInfo.getMemFree() + memoryInfo.getActiveFile() + memoryInfo.getInactiveFile() + memoryInfo.getSReclaimable());
                }
            }
        }

        return memoryInfo;
    }

    @Override
    public IOStatInfo getIOInfo(ServerVo serverVo) throws Exception {
        return null;
    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public String getSystemServiceChannel() {
        return SystemTypeEnum.Linux.getCode();
    }
}
