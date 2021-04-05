package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.AttributeType;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.AttributeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttributeTypeService {

    private final AttributeTypeRepository attributeTypeRepository;

    @Autowired
    public AttributeTypeService(AttributeTypeRepository attributeTypeRepository) {
        this.attributeTypeRepository = attributeTypeRepository;
    }

    public List<AttributeType> findAll() {
        return attributeTypeRepository.findAll();
    }

    public AttributeType find(Long id) {
        try {
            return attributeTypeRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundException(AttributeType.class, id);
        }
    }

    public Long add(AttributeType attributeType) {
        return attributeTypeRepository.save(attributeType).getId();
    }

    public void delete(Long id) {
        find(id);
        attributeTypeRepository.deleteById(id);
    }

    public void update(Long id, AttributeType attributeType) {
        find(id);
        attributeType.setId(id);
        attributeTypeRepository.save(attributeType);
    }

}
