package org.sunshine.lc.test.timer.timewheel;

public class TimeTask {

    private String taskId;
    private long scheduleTimeSeconds;
    private int level;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getScheduleTimeSeconds() {
        return scheduleTimeSeconds;
    }

    public void setScheduleTimeSeconds(long scheduleTimeSeconds) {
        this.scheduleTimeSeconds = scheduleTimeSeconds;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
