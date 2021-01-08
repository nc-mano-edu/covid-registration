package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.service.AttributeTypeService;
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
@RequestMapping(value = "/attributeType")
public class AttributeTypeController {

    private static final Logger log = LoggerFactory.getLogger(AttributeTypeController.class);

    private final Tools tools = new Tools();

    private final AttributeTypeService attributeTypeService;

    @Autowired
    public AttributeTypeController(AttributeTypeService attributeTypeService) {
        this.attributeTypeService = attributeTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttributeType>> findAll() {
        List<AttributeType> attributeTypeList = attributeTypeService.findAll();
        return new ResponseEntity<>(attributeTypeList, attributeTypeList.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeType> find(@PathVariable Long id) {
        AttributeType attributeType = attributeTypeService.find(id);
        return new ResponseEntity<>(attributeType, attributeType == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid AttributeType attributeType, BindingResult bindingResult) {
        log.info("Creating new " + attributeType);
        if (bindingResult.hasErrors()) {
            String message = tools.getErrorMessage(bindingResult);
            log.info("AttributeType not valid (" + message + ")");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(String.valueOf(attributeTypeService.add(attributeType)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (attributeTypeService.delete(id)) {
            log.info("AttributeType removed successfully");
            return ResponseEntity.ok("AttributeType removed successfully");
        } else {
            log.info("AttributeType with id " + id + " not found");
            return new ResponseEntity<>("AttributeType not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid AttributeType attributeType, BindingResult bindingResult) {
        log.info("Updating with " + attributeType);
        if (bindingResult.hasErrors()) {
            String message = tools.getErrorMessage(bindingResult);
            log.info("AttributeType not valid (" + message + ")");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (attributeTypeService.update(id, attributeType)) {
            return ResponseEntity.ok("AttributeType updated successfully");
        } else {
            return new ResponseEntity<>("AttributeType not found", HttpStatus.NOT_FOUND);
        }
    }

}
