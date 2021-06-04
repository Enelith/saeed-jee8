package academy.learnprogramming.entity;

import java.time.LocalDate;

public class Todo {
    private String task;
    private LocalDate dueDate;
    private boolean isCompleted;
    private LocalDate completedDate;
    private LocalDate createDate;
    
    
    
    
    
    
    
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public LocalDate getCompletedDate() {
        return completedDate;
    }
    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }
    public LocalDate getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
