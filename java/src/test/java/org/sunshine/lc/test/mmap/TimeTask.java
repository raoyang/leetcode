package org.sunshine.lc.test.mmap;

import java.io.Serializable;

public class TimeTask implements Serializable {

    private String taskId;
    private long startTime;
    private long endTime;

    public TimeTask(String taskId){
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
