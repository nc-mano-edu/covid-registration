package com.edu.mano.covidregistration.service.attributeService;

import com.edu.mano.covidregistration.domain.Attribute;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AttributeService {

    public List<Attribute> findAll();

    public Attribute find(Long id);

    public ResponseEntity<String> add(Attribute attribute);

    public boolean delete(Long id);

    public ResponseEntity<String> update(Long id, Attribute attribute);

}
