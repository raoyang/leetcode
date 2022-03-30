package org.sunshine.lc.test.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloQuartz implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail detail = context.getJobDetail();
        for(String key : detail.getJobDataMap().keySet()) {
            System.out.println("say hello to " + detail.getJobDataMap().getString(key) + " at " + new Date());
        }
        System.out.println(this);
        //String name = detail.getJobDataMap().getString("name");
        //System.out.println("say hello to " + name + " at " + new Date());
    }
}
