package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.service.TaskInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/taskInstance")
public class TaskInstanceController {

    private static final Logger log = LoggerFactory.getLogger(TaskInstanceController.class);

    private final TaskInstanceService taskInstanceService;

    @Autowired
    public TaskInstanceController(TaskInstanceService taskInstanceService) {
        this.taskInstanceService = taskInstanceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskInstance>> findAll() {
        List<TaskInstance> taskInstances = taskInstanceService.findAll();
        return new ResponseEntity<>(taskInstances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInstance> find(@PathVariable Long id) {
        TaskInstance taskInstance = taskInstanceService.find(id);
        return new ResponseEntity<>(taskInstance, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid TaskInstance taskInstance) {
        log.info("Creating new " + taskInstance);
        return ResponseEntity.ok("TaskInstance created with id " + taskInstanceService.add(taskInstance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        taskInstanceService.delete(id);
        log.info("TaskInstance removed successfully");
        return ResponseEntity.ok("TaskInstance removed successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid TaskInstance taskInstance) {
        log.info("Updating with " + taskInstance);
        taskInstanceService.update(id, taskInstance);
        return ResponseEntity.ok("TaskInstance updated successfully");
    }

}
