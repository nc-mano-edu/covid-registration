package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.service.AttributeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.ATTR_TYPES_BASE_PREFIX;

@RestController
@RequestMapping(value = ATTR_TYPES_BASE_PREFIX)
public class AttributeTypeController {

    private final AttributeTypeService attributeTypeService;

    @Autowired
    public AttributeTypeController(AttributeTypeService attributeTypeService) {
        this.attributeTypeService = attributeTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttributeType>> findAll() {
        List<AttributeType> attributeTypeList = attributeTypeService.findAll();
        return ResponseEntity.ok(attributeTypeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeType> find(@PathVariable Long id) {
        AttributeType attributeType = attributeTypeService.find(id);
        return new ResponseEntity<>(attributeType, attributeType == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid AttributeType attributeType) {
        return ResponseEntity.ok(attributeTypeService.add(attributeType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        attributeTypeService.delete(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid AttributeType attributeType) {
        attributeTypeService.update(id, attributeType);
        return ResponseEntity.accepted().build();
    }

}
