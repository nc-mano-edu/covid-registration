package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.service.AttributeService;
import com.edu.mano.covidregistration.tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/attribute")
public class AttributeController {

    private static final Logger log = LoggerFactory.getLogger(AttributeController.class);

    private final Tools tools = new Tools();

    private final AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attribute>> findAll() {
        List<Attribute> attributes = attributeService.findAll();
        return new ResponseEntity<>(attributes, attributes.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> find(@PathVariable Long id) {
        Attribute attribute = attributeService.find(id);
        return new ResponseEntity<>(attribute, attribute == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody @Valid Attribute attribute) {
        log.info("Creating new " + attribute);
        Long attributeId = attributeService.add(attribute);
        log.info("Attribute created with id " + attributeId);
        return ResponseEntity.ok("Attribute created with id " + attributeId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        attributeService.delete(id);
        log.info("Attribute removed successfully");
        return ResponseEntity.ok("Attribute removed successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid Attribute attribute) {
        log.info("Updating with " + attribute);
        attributeService.update(id, attribute);
        log.info("Attribute updated successfully");
        return ResponseEntity.ok("Attribute updated successfully");
    }

}
