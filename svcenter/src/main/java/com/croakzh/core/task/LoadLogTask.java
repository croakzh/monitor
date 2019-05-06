package com.croakzh.core.task;

import com.croakzh.service.IPatchLogsService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * 可以继承QuartzJobBean 或Job 或StatefulJob，有差别
 *
 * @author croakzh
 */
@DisallowConcurrentExecution
@Service
@Slf4j
public class LoadLogTask extends QuartzJobBean {

    /**
     * log表业务层接口
     */
    @Autowired
    @Qualifier("patchLogsService")
    private IPatchLogsService logService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.debug("开始执行错误日志加载任务...");
        logService.loadAllAppsErrorLog();
        log.debug("日志加载完成");
    }
}
