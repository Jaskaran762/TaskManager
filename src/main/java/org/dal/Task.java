package org.dal;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Model class for task manager app
 * having  attributes and getter- setter
 * methods
 */
public class Task {

    private int taskId;
    private String taskName;
    private int priority;
    private LocalDateTime deadline;
    private String taskType;

    public Task(){}

    public Task(String taskName, int priority, LocalDateTime deadline, String taskType) {
        this.taskName = taskName;
        this.priority = priority;
        this.deadline = deadline;
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId && priority == task.priority && Objects.equals(taskName, task.taskName) && Objects.equals(deadline, task.deadline) && Objects.equals(taskType, task.taskType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, taskName, priority, deadline, taskType);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", priority=" + priority +
                ", deadline=" + deadline +
                ", taskType='" + taskType + '\'' +
                '}';
    }
}
