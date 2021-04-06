package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.TASKS_BASE_PREFIX;


@RestController
@RequestMapping(value = TASKS_BASE_PREFIX)
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable Long id) {
        Task task = taskService.find(id);
        return new ResponseEntity<>(task, task == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid Task task) {
        return ResponseEntity.ok(taskService.add(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Task task) {
        taskService.update(id, task);
        return ResponseEntity.accepted().build();
    }

}
