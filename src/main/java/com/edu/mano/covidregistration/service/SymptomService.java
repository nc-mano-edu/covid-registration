package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Symptom;

import java.util.List;

public interface SymptomService {

    public List<Symptom>findAll();

    public Symptom findSymptomById(Long id);

    public Symptom saveSymptom(Symptom symptom);

    public void  deleteSymptom(Long id);

}
