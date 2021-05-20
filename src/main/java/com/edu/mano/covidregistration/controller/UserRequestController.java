package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.UserRequest;
import com.edu.mano.covidregistration.service.UserRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.USER_REQUEST_BASE_PREFIX;

@RestController
@RequestMapping(value = USER_REQUEST_BASE_PREFIX)
public class UserRequestController {

    private static Logger log = LoggerFactory.getLogger(UserRequestController.class);

    @Autowired
    private UserRequestService userRequestService;

    @PostMapping
    public ResponseEntity<UserRequest> addRequest(@RequestBody UserRequest userRequest) {
        log.info("into: {}", userRequest);
        UserRequest savedUserRequest = userRequestService.saveUserRequest(userRequest);
        log.info(savedUserRequest.toString());
        log.info("the user_request was created");
        return new ResponseEntity<>(savedUserRequest, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> findRequest(@PathVariable Long id) {
        log.info("GET  user_request, id =  " + id);
        UserRequest userRequest = userRequestService.findRequestByRequestId(id);
        if (userRequest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userRequest, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserRequest> findRequestsByUserId(@RequestParam Long userId) {
        List<UserRequest> userRequests = userRequestService.findRequestByUserId(userId);

        return new ResponseEntity(userRequests, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRequest> deleteUserRequest(@PathVariable Long id) {
        UserRequest userRequest = this.userRequestService.findRequestByRequestId(id);

        if (userRequest == null) {
            return new ResponseEntity<UserRequest>(HttpStatus.NOT_FOUND);
        }
        this.userRequestService.deleteUserRequest(id);
        return new ResponseEntity<>(userRequest, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> updateUserRequest(@PathVariable Long id, @RequestBody UserRequest userRequestDetails) {
        return ResponseEntity.ok().body(userRequestService.updateUserRequest(id, userRequestDetails));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRequest>> findAll() {
        List<UserRequest> userRequests = userRequestService.findAll();
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserRequest>> findActive() {
        List<UserRequest> userRequests = userRequestService.findActive();
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }

    @PostMapping("/{id}/recommendations")
    public ResponseEntity<UserRequest> fillDoctorRecommendations(@PathVariable Long id, @RequestBody String rec) {
        return new ResponseEntity<>(userRequestService.fillDoctorRecommendations(id, rec), HttpStatus.OK);
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<UserRequest> closeUserRequest(@PathVariable Long id, @RequestBody(required = false) String rec) {
        return new ResponseEntity<>(userRequestService.closeUserRequest(id, rec), HttpStatus.OK);
    }

}
