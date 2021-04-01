package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.ATTRIBUTES_BASE_PREFIX;

@RestController
@RequestMapping(value = ATTRIBUTES_BASE_PREFIX)
public class AttributeController {

    private final AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Attribute>> findAll() {
        List<Attribute> attributes = attributeService.findAll();
        return ResponseEntity.ok(attributes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attribute> find(@PathVariable Long id) {
        Attribute attribute = attributeService.find(id);
        return new ResponseEntity<>(attribute, attribute == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid Attribute attribute) {
        return ResponseEntity.ok(attributeService.add(attribute));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        attributeService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid Attribute attribute) {
        attributeService.update(id, attribute);
        return ResponseEntity.accepted().build();
    }

}
