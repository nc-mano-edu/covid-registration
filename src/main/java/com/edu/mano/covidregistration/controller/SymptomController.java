package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Symptom;
import com.edu.mano.covidregistration.service.SymptomService;
import com.edu.mano.covidregistration.service.SymptomServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/backend/symptom")
public class SymptomController {

    private final SymptomService symptomService;

    private static final Logger log = LoggerFactory.getLogger(SymptomController.class);

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Symptom>> findAll() {
        log.info("GET request for a list of symptom");
        List<Symptom> symptoms = symptomService.findAll();
        return new ResponseEntity<>(symptoms, symptoms.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
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
    public ResponseEntity<Symptom> addSymptom(@RequestBody Symptom symptom, Errors errors) {
        log.info("POST request for creation " + symptom);
        if (errors.hasErrors()) {
            log.info("Symptom not valid");
        }
        Long symptomId= symptomService.saveSymptom(symptom).getSymptomId();
        log.info("Symptom created with id " + symptomId );
        return new ResponseEntity<>(symptom,HttpStatus.OK);
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
    public ResponseEntity<Symptom> updateSymptom (@RequestBody Symptom symptomDetails, @PathVariable Long id) {
        Symptom symptom = symptomService.findSymptomById(id);
        if (symptom == null) {
            return  new ResponseEntity<Symptom>(HttpStatus.NOT_FOUND);
        }

        symptom.setName(symptomDetails.getName());
        symptom.setDescription(symptomDetails.getDescription());

        final Symptom updatedSymptom = symptomService.saveSymptom(symptom);
        return new ResponseEntity<Symptom>(updatedSymptom, HttpStatus.OK);
    }


}
