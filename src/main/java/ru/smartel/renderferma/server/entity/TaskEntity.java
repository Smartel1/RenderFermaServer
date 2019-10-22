package ru.smartel.renderferma.server.entity;

import ru.smartel.renderferma.server.common.Task;
import ru.smartel.renderferma.server.common.TaskStatus;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class TaskEntity implements Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @Column(name = "status")
    private TaskStatus status = TaskStatus.RENDERING;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
