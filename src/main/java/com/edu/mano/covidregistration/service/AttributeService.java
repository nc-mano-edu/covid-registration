package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.AttributeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttributeService {

    private static final Logger log = LoggerFactory.getLogger(AttributeService.class);

    private AttributeRepository attributeRepository;

    private AttributeTypeService attributeTypeService;

    @Autowired
    public void AttributeServiceImpl(AttributeRepository attributeRepository, AttributeTypeService attributeTypeService) {
        this.attributeRepository = attributeRepository;
        this.attributeTypeService = attributeTypeService;
    }

    public List<Attribute> findAll() {
        log.info("Retrieving a list of attributes");
        return attributeRepository.findAll();
    }

    public Attribute find(Long id) {
        log.info("Retrieving an attribute with id " + id);
        try {
            return attributeRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(Attribute.class, id);
        }
    }

    public Long add(Attribute attribute) {
        attributeTypeService.find(attribute.getAttributeType().getId());
        return attributeRepository.save(attribute).getId();
    }

    public void delete(Long id) {
        log.info("Deleting an attribute with id " + id);
        try {
            attributeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(Attribute.class, id);
        }
    }

    public void update(Long id, Attribute attribute) {
        attributeTypeService.find(attribute.getAttributeType().getId());
        try {
            attribute.setId(attributeRepository.findById(id).get().getId());
            attributeRepository.save(attribute).getId();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(Attribute.class, id);
        }
    }

}
