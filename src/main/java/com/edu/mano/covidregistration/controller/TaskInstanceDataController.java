package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.service.TaskInstanceDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/taskInstanceData")
public class TaskInstanceDataController {

    private static final Logger log = LoggerFactory.getLogger(TaskInstanceDataController.class);

    private final TaskInstanceDataService taskInstanceDataService;

    @Autowired
    public TaskInstanceDataController(TaskInstanceDataService taskInstanceDataService) {
        this.taskInstanceDataService = taskInstanceDataService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskInstanceData>> findAll() {
        List<TaskInstanceData> taskInstances = taskInstanceDataService.findAll();
        return new ResponseEntity<>(taskInstances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInstanceData> find(@PathVariable Long id) {
        TaskInstanceData taskInstanceData = taskInstanceDataService.find(id);
        return new ResponseEntity<>(taskInstanceData, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid TaskInstanceData taskInstanceData) {
        log.info("Creating new " + taskInstanceData);
        return ResponseEntity.ok("TaskInstanceData created with id " + taskInstanceDataService.add(taskInstanceData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        taskInstanceDataService.delete(id);
        log.info("TaskInstanceData removed successfully");
        return ResponseEntity.ok("TaskInstanceData removed successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid TaskInstanceData taskInstanceData) {
        log.info("Updating with " + taskInstanceData);
        taskInstanceDataService.update(id, taskInstanceData);
        return ResponseEntity.ok("TaskInstanceData updated successfully");
    }

}
