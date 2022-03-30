package org.sunshine.lc.test.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.util.*;

public class QuartzTest {

    public static void main(String args[]){
        try {

            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            long cur = System.currentTimeMillis();



//            Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger-1", "group111")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("*/1 * * * * ?"))
//                    .build();
//            JobDetail job = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
//                    .withIdentity("job-2-3", "group") //定义name/group
//                    .usingJobData("name-1-2", "quartz-1:2") //定义属性
//                    .build();
//
//            scheduler.scheduleJob(job, trigger1);


            for(int i = 0 ; i < 10 ; i ++) {
                Map<JobDetail, Set<? extends Trigger>> map = new HashMap<>();
                for(int j = 0 ; j < 100 ; j ++){
                    Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger-" + i + ":" + j, "group111")
                            .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                            .startAt(new Date(cur + 1000))
                            .build();
                    //定义一个JobDetail
                    JobDetail job = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                            .withIdentity("job-" + i + ":" + j, "group") //定义name/group
                            .usingJobData("name-" + i + ":" + j, "quartz-" + i + ":" + j) //定义属性
                            .build();
                    Set<Trigger> set = new HashSet<>();
                    set.add(trigger1);
                    map.put(job, set);
                }
                //加入这个调度
                scheduler.scheduleJobs(map, true);
                System.out.println("加入第:" + i + "批定时器");
            }
            //System.out.println("插入100万耗时:" + (System.currentTimeMillis() - cur));


            /*
            for (int i = 1000000; i < 2000000; i++) {
                Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger" + i, "group")
                        .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                        .startAt(new Date(cur + 100000))
                        .build();
                //定义一个JobDetail
                JobDetail job = JobBuilder.newJob(HelloQuartz.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                        .withIdentity("job" + i, "group") //定义name/group
                        .usingJobData("name" + i, "quartz" + i) //定义属性
                        .build();
                //加入这个调度
                scheduler.scheduleJob(job, trigger1);
                System.out.println("加入第:" + i + "个定时器");
            }*/
            Thread.sleep(5000);
            //启动之
            System.out.println("启动调度器");
            scheduler.start();
            scheduler.unscheduleJob(new TriggerKey("a", "n"));
            //运行一段时间后关闭
            Thread.sleep(20000l);
            scheduler.unscheduleJob(new TriggerKey("trigger-1", "group111"));
            Thread.sleep(20000l);
            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
