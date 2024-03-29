package ru.shum.taskapp.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.shum.taskapp.models.Task;
import ru.shum.taskapp.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Task API")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public List<Task> tasks() {
        return taskService.findAll();
    }

    @PostMapping("/create")
    public boolean createTask(Task task) {
        return taskService.createTask(task);
    }

    @GetMapping("/search")
    public List<Task> findTasks(@RequestParam(name = "stat", required = false) String status) {
        return taskService.findByStatus(status);
    }

    @PostMapping("task_table/delete/{id}")
    public boolean dellTasks(@PathVariable Long id) {
        return  taskService.dellTask(id);
    }

    @PostMapping("task_table/find/{id}")
    public Task findTasksById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("update/{id}")
    public boolean updateTasksById(@PathVariable Long id, @RequestParam(required = false) String nameTask,
                                   @RequestParam(required = false) String taskBody,
                                   @RequestParam(required = false) String status) {
        return taskService.updateTask(id, nameTask, taskBody, status);
    }

    @PostMapping("/assignExecutors/{id}")
    public boolean assignExecutors(@PathVariable Long id, @RequestParam List<String> executorUsernames) {
        return taskService.assignExecutors(id, executorUsernames);
    }
}