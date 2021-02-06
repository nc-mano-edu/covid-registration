package com.edu.mano.covidregistration.service.attributeService;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.domain.AttributeType;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Profile("test")
public class MockAttributeServiceImpl implements AttributeService {

    private List<Attribute> attributes;

    MockAttributeServiceImpl() {
        this.attributes = new ArrayList<>(Collections.singletonList(
                new Attribute(1L, "User age",
                        new AttributeType(1L, "Numeric value", "\\d+(\\.\\d+)?"))
        ));
    }

    public List<Attribute> findAll() {
        return attributes;
    }

    public Attribute find(Long id) {
        return attributes.stream()
                .filter(attr -> id.equals(attr.getId()))
                .findAny().orElse(null);
    }

    public ResponseEntity<String> add(Attribute attribute) {
        long id = attributes.get(attributes.size() - 1).getId() + 1;
        attribute.setId(id);
        attributes.add(attribute);
        return ResponseEntity.ok("Attribute created with id " + id);
    }

    public boolean delete(Long id) {
        Attribute res = this.find(id);
        if (res == null) {
            return false;
        }
        attributes.remove(res);
        return true;
    }

    public ResponseEntity<String> update(Long id, Attribute attribute) {
        int resId = attributes.indexOf(this.find(id));
        if (resId == -1) {
            return new ResponseEntity<>("Attribute with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        attributes.set(resId, attribute);
        return ResponseEntity.ok("Attribute updated successfully");
    }

}
