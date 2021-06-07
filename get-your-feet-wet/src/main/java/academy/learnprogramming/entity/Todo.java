package academy.learnprogramming.entity;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Task must be set")
    @Size(min = 10, message = "Task must be at least 10 characters long")
    private String task;

    @NotNull(message = "Due date must be set")
    @FutureOrPresent(message = "Due date must be in present or future")
    @JsonbDateFormat(value = "yyyy-MM-dd")
    private LocalDate dueDate;

    private boolean isCompleted;
    private LocalDate completedDate;
    private LocalDate createDate;

    // Example of Entity Lifecycle Callback :
    // This method will be called just before being inserted into DB
    @PrePersist
    private void init() {
	setCreateDate(LocalDate.now());
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

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
