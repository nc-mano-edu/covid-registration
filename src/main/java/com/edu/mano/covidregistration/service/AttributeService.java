package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.exception.baseExceptions.NotFoundException;
import com.edu.mano.covidregistration.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttributeService {

    private AttributeRepository attributeRepository;

    private AttributeTypeService attributeTypeService;

    @Autowired
    public void AttributeServiceImpl(AttributeRepository attributeRepository, AttributeTypeService attributeTypeService) {
        this.attributeRepository = attributeRepository;
        this.attributeTypeService = attributeTypeService;
    }

    public List<Attribute> findAll() {
        return attributeRepository.findAll();
    }

    public Attribute find(Long id) {
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
        find(id);
        attributeRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Attribute attribute) {
        find(id);
        attribute.setId(id);
        attributeTypeService.find(attribute.getAttributeType().getId());
        attributeRepository.save(attribute);
    }

}
