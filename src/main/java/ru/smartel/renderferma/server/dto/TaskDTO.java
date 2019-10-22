package ru.smartel.renderferma.server.dto;

import ru.smartel.renderferma.server.common.Task;
import ru.smartel.renderferma.server.common.TaskStatus;
import ru.smartel.renderferma.server.entity.TaskEntity;

public class TaskDTO implements Task {
    private Integer id;
    private TaskStatus status;

    public static TaskDTO of(TaskEntity entity) {
        return new TaskDTO(entity.getId(), entity.getStatus());
    }

    public TaskDTO(Integer id, TaskStatus status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
