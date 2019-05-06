package com.croakzh.service.impl;

import com.croakzh.core.context.ActionContext;
import com.croakzh.core.utils.ShellUtils;
import com.croakzh.service.IPatchLogsService;
import com.croakzh.webfront.po.ApplicationPo;
import com.croakzh.webfront.po.LogPo;
import com.croakzh.webfront.po.ServerPo;
import com.croakzh.webfront.service.IApplicationService;
import com.croakzh.webfront.service.ILogService;
import com.croakzh.webfront.service.IServerService;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 批量日志处理
 *
 * @author croakzh
 */
@Service("patchLogsService")
@Slf4j
public class PatchLogsServiceImpl implements IPatchLogsService {

    /**
     * 锚点 [18:15:32:546] [ERROR]
     */
    private Pattern errorspattern = Pattern.compile("\\[\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}:\\d{3}\\] " +
            "\\[ERROR\\][\\s\\S]*");

    /**
     * application表业务层接口
     */
    @Autowired
    @Qualifier("applicationService")
    private IApplicationService applicationService;

    /**
     * server表业务层接口
     */
    @Autowired
    @Qualifier("serverService")
    private IServerService serverService;

    /**
     * log表业务层接口
     */
    @Autowired
    @Qualifier("logService")
    private ILogService logService;

    @Override
    public Boolean loadAllAppsErrorLog() {
        log.debug("Start to load error logs...");
        List<ApplicationPo> applications = applicationService.findAllApplications(new ApplicationPo());
        Map<String, Session> connections = ActionContext.getConnections();
        // 遍历应用
        for (ApplicationPo application : applications) {
            if (!connections.containsKey(application.getHost())) {
                log.warn("server {} is not connected, so the application {} is not execute load.",
                        application.getServerid(), application.getApplicationid());
                continue;
            }
            Session session = connections.get(application.getHost());
            if (session == null) {
                log.error("The server is not connected.");
                ServerPo server = new ServerPo();
                server.setServerid(application.getServerid());
                server.setStatus(1);
                serverService.updateServer(server);
            } else {
                // 操作进行日志批量操作
                List<String> logtimes = logService.findAllLogs(new LogPo(application.getServerid(), application.getApplicationid())).stream().map((LogPo::getLogtime)).collect(Collectors.toList());
                try {
                    // 设置标志位
                    boolean insert = false;
                    List<String> logs = ShellUtils.execCmd(session, "cat " + application.getDeveloppath() + "/logs/error.log");
                    LogPo logPo = new LogPo(application.getServerid(), application.getApplicationid());
                    List<String> log = new ArrayList();
                    for (String line : logs) {
                        if (errorspattern.matcher(line).matches()) {
                            String time = line.substring(0, line.indexOf("]") + 1);
                            logPo.setDetail(String.join("\n", log));
                            if (insert) {
                                logService.addLog(logPo);
                            }
                            insert = !logtimes.contains(time.trim());
                            logPo = new LogPo(application.getServerid(), application.getApplicationid());
                            log = new ArrayList();
                            logPo.setLogtime(time);
                        }
                        log.add(line);
                    }
                    if (log.size() > 0 && insert) {
                        logPo.setDetail(String.join("\n", log));
                        logService.addLog(logPo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
