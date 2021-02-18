package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.exception.baseExceptions.SymptomNotFoundByIdException;
import com.edu.mano.covidregistration.repository.SymptomRepository;
import com.edu.mano.covidregistration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
//@Slf4j
@Profile("!test")
public class SymptomServiceImpl implements SymptomService {

    private static final Logger log = LoggerFactory.getLogger(AttributeService.class);

    @Autowired
    private SymptomRepository symptomRepository;

    public SymptomServiceImpl(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public List<Symptom> findAll() {
        log.info("Try to find all symptoms");
        return symptomRepository.findAll();
    }

    public Symptom findSymptomById(Long id) {
        log.info("try to find a symptom with id = "+ id);
        try {
            return symptomRepository.findSymptomBySymptomId(id);
        }
        catch (NoSuchElementException e){
            log.info("Symptom with id " + id +" haven't been found");
            throw new SymptomNotFoundByIdException(e);
        }
    }

    public Symptom saveSymptom(Symptom symptom) {
        return symptomRepository.save(symptom);
    }

    public void  deleteSymptom(Long id) {
        symptomRepository.deleteById(id);
    }


}
