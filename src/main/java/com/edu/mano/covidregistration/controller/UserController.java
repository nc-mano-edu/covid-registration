package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.TaskInstance;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_BASE_PREFIX;

@RestController
@RequestMapping(value = USER_BASE_PREFIX)
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable Long id) {
        User user = userService.findUser(id);

        return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@RequestBody @Valid User user, @PathVariable Long id) {
        userService.updateUser(user, id);

        return ResponseEntity.ok("Edited a user with id" + user.getId());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskInstance>> findAllTasks(@PathVariable Long id) {
        List<TaskInstance> tasks = userService.findTasks(id);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}/active-tasks")
    public ResponseEntity<List<TaskInstance>> findActiveTasks(@PathVariable Long id) {
        List<TaskInstance> tasks = userService.findActiveTasks(id);
        return ResponseEntity.ok(tasks);
    }
}
