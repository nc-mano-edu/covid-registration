package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.service.SymptomServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.SYMPTOMS_BASE_PREFIX;

@RestController
@RequestMapping(value = SYMPTOMS_BASE_PREFIX)

public class SymptomController {

    private final SymptomServiceImpl symptomService;

    private static final Logger log = LoggerFactory.getLogger(SymptomController.class);

    @Autowired
    public SymptomController(SymptomServiceImpl symptomService) {
        this.symptomService = symptomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Symptom>> findAll() {
        log.info("GET request for a list of symptom");
        List<Symptom> symptoms = symptomService.findAll();
        return new ResponseEntity<>(symptoms, HttpStatus.OK);
    }

    @GetMapping("/{symptomId}")
    public ResponseEntity<Symptom> findById(@PathVariable Long symptomId) {
        if (symptomId == null) {
            log.info(" not a proper id ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Symptom symptom = symptomService.findSymptomById(symptomId);

        if (symptom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info(symptom.toString());
        return new ResponseEntity<Symptom>(symptom, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Symptom> addSymptom(@RequestBody Symptom symptom) {
        log.info("POST request for creation " + symptom);
        Symptom savedSymptom = symptomService.saveSymptom(symptom);
        return new ResponseEntity<>(savedSymptom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSymptom(@PathVariable Long id) {
        Symptom symptom = this.symptomService.findSymptomById(id);

        if (symptom == null) {
            return ResponseEntity.ok("symptom with id + " + id + " couldn't be found");
        }
        this.symptomService.deleteSymptom(id);
        return ResponseEntity.ok("Symptom was deleted with id = " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Symptom> updateSymptom(@RequestBody Symptom symptomDetails, @PathVariable Long id) {
        Symptom symptom = symptomService.findSymptomById(id);
        if (symptom == null) {
            return new ResponseEntity<Symptom>(HttpStatus.NOT_FOUND);
        }

        symptom.setName(symptomDetails.getName());
        symptom.setDescription(symptomDetails.getDescription());

        final Symptom updatedSymptom = symptomService.saveSymptom(symptom);
        return new ResponseEntity<Symptom>(updatedSymptom, HttpStatus.OK);
    }


}
