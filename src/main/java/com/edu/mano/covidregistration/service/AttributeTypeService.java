package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
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
        log.info("Retrieving a list of attributeTypes");
        return attributeTypeRepository.findAll();
    }

    public AttributeType find(Long id) {
        log.info("Retrieving an attributeType with id " + id);
        try {
            return attributeTypeRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(AttributeType.class, id);
        }
    }

    public Long add(AttributeType attributeType) {
        Long attributeTypeId = attributeTypeRepository.save(attributeType).getId();
        log.info("AttributeType created with id " + attributeTypeId);
        return attributeTypeId;
    }

    public void delete(Long id) {
        log.info("Deleting an attributeType with id " + id);
        try {
            attributeTypeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(AttributeType.class, id);
        }
    }

    public void update(Long id, AttributeType attributeType) {
        try {
            attributeType.setId(attributeTypeRepository.findById(id).get().getId());
            Long attributeTypeId = attributeTypeRepository.save(attributeType).getId();
            log.info("AttributeType updated successfully");
        } catch (NoSuchElementException e) {
            throw new NotFoundException(AttributeType.class, id);
        }
    }

}
