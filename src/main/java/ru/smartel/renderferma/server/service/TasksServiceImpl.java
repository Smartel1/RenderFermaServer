package ru.smartel.renderferma.server.service;

import org.springframework.stereotype.Service;
import ru.smartel.renderferma.common.Task;
import ru.smartel.renderferma.common.TaskSavingException;
import ru.smartel.renderferma.common.TaskService;
import ru.smartel.renderferma.common.TaskStatus;
import ru.smartel.renderferma.server.dto.TaskDTO;
import ru.smartel.renderferma.server.entity.TaskEntity;
import ru.smartel.renderferma.server.entity.UserEntity;
import ru.smartel.renderferma.server.repository.TaskRepository;
import ru.smartel.renderferma.server.repository.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class TasksServiceImpl implements TaskService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private Executor executor;

    public TasksServiceImpl(UserRepository userRepository, TaskRepository taskRepository, Executor executor) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    @Override
    public List<Task> getUserTasks(String userEmail) {
        return taskRepository.getAllByUserEmail(userEmail)
                .stream()
                .map(TaskDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAndHandle(String userEmail) throws TaskSavingException {
        UserEntity user = userRepository.findByEmail(userEmail);
        if (null == user) throw new TaskSavingException("User with email " + userEmail + " doesn't exist");

        TaskEntity entity = new TaskEntity();
        entity.setUser(user);

        taskRepository.save(entity);

        //imitate task handling in another thread
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                entity.setStatus(TaskStatus.COMPLETE);
                taskRepository.save(entity);
            }
        }, executor);
    }
}
