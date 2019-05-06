package com.croakzh.core.task;

import com.croakzh.core.config.SpringContextUtil;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author croakzh
 */
@Component
public class MyScheduler {

    public void scheduleJobs() throws SchedulerException {
        ApplicationContext ctx = SpringContextUtil.getApplicationContext();
        Scheduler scheduler = (Scheduler) ctx.getBean("mySchedulerFactoryBean");
        startCheckJob(scheduler);
        startTestJob(scheduler);
    }

    /**
     * 注册检查任务
     *
     * @param scheduler 定时任务
     */
    private void startCheckJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(ServerCheckTask.class).withIdentity("serverCheckTask").storeDurably().build();

        // 每3分钟检查一次服务器连接
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/3 * * * ?");
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withDescription("serverCheckTrigger").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(job, cronTrigger);
    }

    /**
     * 注册 job
     *
     * @param scheduler 定时任务
     */
    private void startTestJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(LoadLogTask.class).withIdentity("loadLogTask").storeDurably().build();

        // 每10分钟捞一次错误日志
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/15 * * * ?");
        CronTrigger cronTrigger =
                TriggerBuilder.newTrigger().withDescription("loadLogTrigger").withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(job, cronTrigger);
    }

}
