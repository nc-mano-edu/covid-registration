package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.service.TaskInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.TASKS_INSTANCE_BASE_PREFIX;

@RestController
@RequestMapping(value = TASKS_INSTANCE_BASE_PREFIX)
public class TaskInstanceController {

    private final TaskInstanceService taskInstanceService;

    @Autowired
    public TaskInstanceController(TaskInstanceService taskInstanceService) {
        this.taskInstanceService = taskInstanceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskInstance>> findAll() {
        List<TaskInstance> taskInstances = taskInstanceService.findAll();
        return ResponseEntity.ok(taskInstances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInstance> find(@PathVariable Long id) {
        TaskInstance taskInstance = taskInstanceService.find(id);
        return new ResponseEntity<>(taskInstance, taskInstance == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid TaskInstance taskInstance) {
        return ResponseEntity.ok(taskInstanceService.add(taskInstance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        taskInstanceService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid TaskInstance taskInstance) {
        taskInstanceService.update(id, taskInstance);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<TaskInstance>> findActive() {
        List<TaskInstance> taskInstances = taskInstanceService.findActive();
        return ResponseEntity.ok(taskInstances);
    }

}
