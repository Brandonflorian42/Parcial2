package edu.listas;

public class ClsTaskList {
    private String taskName;
    private String taskDescription;
    private boolean isDone;


    public String getTaskName() {
        return taskName;
    }




    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


}
