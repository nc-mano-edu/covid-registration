package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Task;
import com.edu.mano.covidregistration.service.TaskService;
import com.edu.mano.covidregistration.tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final Tools tools = new Tools();

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, tasks.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> find(@PathVariable Long id) {
        Task task = taskService.find(id);
        return new ResponseEntity<>(task, task == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid Task task, BindingResult bindingResult) {
        log.info("Creating new " + task);
        if (bindingResult.hasErrors()) {
            String message = tools.getErrorMessage(bindingResult);
            log.info("Task not valid (" + message + ")");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return taskService.add(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (taskService.delete(id)) {
            log.info("Task removed successfully");
            return ResponseEntity.ok("Task removed successfully");
        } else {
            log.info("Task with id " + id + " not found");
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid Task task, BindingResult bindingResult) {
        log.info("Updating with " + task);
        if (bindingResult.hasErrors()) {
            String message = tools.getErrorMessage(bindingResult);
            log.info("Task not valid (" + message + ")");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return taskService.update(id, task);
    }

}
