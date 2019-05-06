package com.croakzh.controller;

import com.croakzh.controller.model.RSResult;
import com.croakzh.core.entity.SystemInfo;
import com.croakzh.core.entity.vo.ServerVo;
import com.croakzh.service.common.BizException;
import com.croakzh.service.impl.LinuxServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统信息
 *
 * @author croakzh
 */
@Controller
@RequestMapping("/system/")
@Slf4j
public class SystemController {

    private final LinuxServiceImpl systemService;

    @Autowired
    public SystemController(LinuxServiceImpl systemService) {
        this.systemService = systemService;
    }

    @PostMapping("info")
    public @ResponseBody
    RSResult info(@RequestBody ServerVo cond) {
        log.info("Start get system information, params: ", cond);
        RSResult result = new RSResult();
        try {
            SystemInfo systemInfo = new SystemInfo();
            systemInfo.setCpuInfo(systemService.getCpuInfo(cond));
            result.setData(systemInfo);
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription(), "获取系统信息失败！");
        } catch (Exception e) {
            result.setResult(-1);
            result.setMessage("获取系统信息失败！");
        }
        return result;
    }

    /**
     * 查看CPU详情
     *
     * @param cond 服务器信息
     * @return {@link RSResult} 服务器详情
     */
    @PostMapping("cpu/info")
    public @ResponseBody
    RSResult cpuInfo(@RequestBody ServerVo cond) {
        log.info("Start get cpu information, params : ", cond);
        RSResult result = new RSResult();
        if (StringUtils.isEmpty(cond.getHost())) {
            result.setResult(-1);
            result.setMessage("服务器IP不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(cond.getUsername()) && StringUtils.isEmpty(cond.getPassword())) {
            result.setResult(-1);
            result.setMessage("登录用户名和密码不能为空！");
            return result;
        }
        try {
            result.setData(systemService.getCpuInfo(cond));
            result.setMessage("CPU列表查询成功");
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription(), "获取CPU详情失败");
        } catch (Exception ex) {
            result.setResult(-1);
            result.setMessage("CPU详情获取失败！");
        }
        log.info("Get cpu information success.");
        return result;
    }

    @PostMapping("process/info")
    public @ResponseBody
    RSResult processInfo(@RequestBody ServerVo cond) {
        return null;
    }

    @PostMapping("memory/info")
    public @ResponseBody
    RSResult memoryInfo(@RequestBody ServerVo cond) {
        log.info("Start get memory information, params : ", cond);
        RSResult result = new RSResult();
        if (StringUtils.isEmpty(cond.getHost())) {
            result.setResult(-1);
            result.setMessage("服务器IP不能为空！");
            return result;
        }
        if (StringUtils.isEmpty(cond.getUsername()) && StringUtils.isEmpty(cond.getPassword())) {
            result.setResult(-1);
            result.setMessage("登录用户名和密码不能为空！");
            return result;
        }
        try {
            result.setData(systemService.getMemInfo(cond));
            result.setMessage("内存列表查询成功");
            result.setResult(0);
        } catch (BizException ex) {
            log.error("{} : {}", ex.getErrorcode(), ex.getDescription());
            result.setResult(ex.getErrorcode(), ex.getDescription(), "获取内存详情失败");
        } catch (Exception ex) {
            result.setResult(-1);
            result.setMessage("内存详情获取失败！");
        }
        log.info("Get memory information success.");
        return result;

    }

    @PostMapping("io/info")
    public @ResponseBody
    RSResult ioInfo(@RequestBody ServerVo cond) {
        return null;
    }

    @PostMapping("user/info")
    public @ResponseBody
    RSResult userInfo(@RequestBody ServerVo cond) {
        return null;
    }

    @PostMapping("network/info")
    public @ResponseBody
    RSResult networkInfo(@RequestBody ServerVo cond) {
        return null;
    }


}
