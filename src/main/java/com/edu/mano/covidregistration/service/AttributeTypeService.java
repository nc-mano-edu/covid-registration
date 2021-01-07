package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.repository.AttributeTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttributeTypeService {

    private static final Logger log = LoggerFactory.getLogger(AttributeTypeService.class);

    private final AttributeTypeRepository attributeTypeRepository;

    @Autowired
    public AttributeTypeService(AttributeTypeRepository attributeTypeRepository) {
        this.attributeTypeRepository = attributeTypeRepository;
    }


    public List<AttributeType> findAll() {
        log.info("GET request for a list of attributeTypes");
        return attributeTypeRepository.findAll();
    }

    public AttributeType find(Long id) {
        log.info("GET request for an attributeType with id " + id);
        try {
            return attributeTypeRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            log.info("AttributeType with id " + id + " not found");
            return null;
        }
    }

    public Long add(AttributeType attributeType) {
        Long attributeTypeId = attributeTypeRepository.save(attributeType).getId();
        log.info("AttributeType created with id " + attributeTypeId);
        return attributeTypeId;
    }

    public boolean delete(Long id) {
        log.info("DELETE request for an attributeType with id " + id);
        try {
            attributeTypeRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean update(Long id, AttributeType attributeType) {
        try {
            Long attributeTypeOldId = attributeTypeRepository.findById(id).get().getId();
            attributeType.setId(attributeTypeOldId);
            Long attributeTypeId = attributeTypeRepository.save(attributeType).getId();
            log.info("AttributeType updated successfully");
            return true;
        } catch (NoSuchElementException e) {
            log.info("AttributeType with id " + id + " not found");
            return false;
        }
    }

}
