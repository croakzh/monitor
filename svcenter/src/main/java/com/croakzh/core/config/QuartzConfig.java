package com.croakzh.core.config;

import com.croakzh.core.task.MyJobFactory;
import com.croakzh.core.task.MyScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务
 *
 * @author croakzh
 */
@Configuration
public class QuartzConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final MyScheduler myScheduler;
    private final MyJobFactory myJobFactory;

    @Autowired
    public QuartzConfig(MyScheduler myScheduler, MyJobFactory myJobFactory) {
        this.myScheduler = myScheduler;
        this.myJobFactory = myJobFactory;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            myScheduler.scheduleJobs();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



    @Bean(name = "mySchedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //factory.setOverwriteExistingJobs(true);

        // 延时启动
        factory.setStartupDelay(2);

        // 加载quartz数据源配置
        //factory.setQuartzProperties(quartzProperties());

        // 自定义Job Factory，用于Spring注入
        factory.setJobFactory(myJobFactory);

        return factory;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }

}
