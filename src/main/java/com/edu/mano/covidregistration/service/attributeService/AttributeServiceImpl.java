package com.edu.mano.covidregistration.service.attributeService;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.repository.AttributeRepository;
import com.edu.mano.covidregistration.service.AttributeTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Profile("!test")
public class AttributeServiceImpl implements AttributeService {

    private static final Logger log = LoggerFactory.getLogger(AttributeServiceImpl.class);

    private final AttributeRepository attributeRepository;

    private final AttributeTypeService attributeTypeService;

    @Autowired
    public AttributeServiceImpl(AttributeRepository attributeRepository, AttributeTypeService attributeTypeService) {
        this.attributeRepository = attributeRepository;
        this.attributeTypeService = attributeTypeService;
    }

    public List<Attribute> findAll() {
        log.info("Retrieving a list of attributes");
        return attributeRepository.findAll();
    }

    public Attribute find(Long id) {
        log.info("Retrieving an attributes with id " + id);
        try {
            return attributeRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            log.info("Attribute with id " + id + " not found");
            return null;
        }
    }

    public ResponseEntity<String> add(Attribute attribute) {
        Long attributeTypeId = attribute.getAttributeType().getId();
        if (attributeTypeService.find(attributeTypeId) == null) {
            return new ResponseEntity<>("AttributeType with id " + attributeTypeId + " does not exist", HttpStatus.NOT_FOUND);
        }

        Long attributeId = attributeRepository.save(attribute).getId();
        log.info("Attribute created with id " + attributeId);
        return ResponseEntity.ok("Attribute created with id " + attributeId);
    }

    public boolean delete(Long id) {
        log.info("Deleting an attributes with id " + id);
        try {
            attributeRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public ResponseEntity<String> update(Long id, Attribute attribute) {
        Long attributeTypeId = attribute.getAttributeType().getId();
        if (attributeTypeService.find(attributeTypeId) == null) {
            return new ResponseEntity<>("AttributeType with id " + attributeTypeId + " does not exist", HttpStatus.NOT_FOUND);
        }

        try {
            attribute.setId(attributeRepository.findById(id).get().getId());
            Long attributeId = attributeRepository.save(attribute).getId();
            log.info("Attribute updated successfully");
            return ResponseEntity.ok("Attribute updated successfully");
        } catch (NoSuchElementException e) {
            log.info("Attribute with id " + id + " not found");
            return new ResponseEntity<>("Attribute with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
