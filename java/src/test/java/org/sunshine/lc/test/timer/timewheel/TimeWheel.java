package org.sunshine.lc.test.timer.timewheel;

import java.util.LinkedList;

public class TimeWheel {

    private int wheelSlotSize;
    //当前下标
    private int currentIndex;

    private LinkedList<TimeTask>[] tasks;

    public TimeWheel(int wheelSlotSize){
        this.wheelSlotSize = wheelSlotSize;
        this.tasks = new LinkedList[this.wheelSlotSize];
    }

    public void addTask(TimeTask task){
        int level = (int)(task.getScheduleTimeSeconds() / wheelSlotSize);
        int slot = (int)(task.getScheduleTimeSeconds() % wheelSlotSize);
        task.setLevel(level);
        LinkedList<TimeTask> linkedList = tasks[slot];
        linkedList.add(task);
    }

    public void cancelTask(String taskId){

    }

    public void update(){

    }
}
