package com.croakzh.core.task;

import com.croakzh.service.IServerCheckService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * @author croakzh
 */
@Slf4j
@Service
@DisallowConcurrentExecution
public class ServerCheckTask extends QuartzJobBean {

    @Autowired
    @Qualifier("serverCheckService")
    private IServerCheckService serverCheckService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.debug("开始执行服务器检查任务...");
        serverCheckService.updateServersStatus(null);
        serverCheckService.updateApplicationsStatus(null);
        log.debug("服务器状态检查结束");
    }
}
