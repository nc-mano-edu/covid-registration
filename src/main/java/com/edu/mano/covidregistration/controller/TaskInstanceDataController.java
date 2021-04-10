package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.TaskInstanceData;
import com.edu.mano.covidregistration.service.TaskInstanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.TASKS_INSTANCE_DATA_BASE_PREFIX;

@RestController
@RequestMapping(value = TASKS_INSTANCE_DATA_BASE_PREFIX)
public class TaskInstanceDataController {

    private final TaskInstanceDataService taskInstanceDataService;

    @Autowired
    public TaskInstanceDataController(TaskInstanceDataService taskInstanceDataService) {
        this.taskInstanceDataService = taskInstanceDataService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskInstanceData>> findAll() {
        List<TaskInstanceData> taskInstances = taskInstanceDataService.findAll();
        return ResponseEntity.ok(taskInstances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInstanceData> find(@PathVariable Long id) {
        TaskInstanceData taskInstanceData = taskInstanceDataService.find(id);
        return new ResponseEntity<>(taskInstanceData, taskInstanceData == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid TaskInstanceData taskInstanceData) {
        return ResponseEntity.ok(taskInstanceDataService.add(taskInstanceData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        taskInstanceDataService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid TaskInstanceData taskInstanceData) {
        taskInstanceDataService.update(id, taskInstanceData);
        return ResponseEntity.accepted().build();
    }

}
