package ru.smartel.renderferma.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.smartel.renderferma.common.Task;
import ru.smartel.renderferma.common.TaskSavingException;
import ru.smartel.renderferma.common.TaskService;
import ru.smartel.renderferma.server.security.UserPrincipal;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    public List<Task> getUserTasks(@AuthenticationPrincipal UserPrincipal user) {
        return taskService.getUserTasks(user.getUsername());
    }

    @PostMapping
    public void saveTask(@AuthenticationPrincipal UserPrincipal user) throws TaskSavingException {
        taskService.saveAndHandle(user.getUsername());
    }

}
