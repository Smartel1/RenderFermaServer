package ru.smartel.renderferma.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.smartel.renderferma.server.entity.TaskEntity;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {
    List<TaskEntity> getAllByUserEmail(String userEmail);
}
