package com.edu.mano.covidregistration.controller;

import com.edu.mano.covidregistration.domain.Specialisation;
import com.edu.mano.covidregistration.service.SpecialisationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.edu.mano.covidregistration.CovidRegistrationApplication.SPECIALIZATION_BASE_PREFIX;

@RestController
@RequestMapping(SPECIALIZATION_BASE_PREFIX)
public class SpecialisationController {

    private static final Logger log = LoggerFactory.getLogger(SpecialisationController.class);
    private final SpecialisationService specialisationService;

    @Autowired
    public SpecialisationController(SpecialisationService specialisationService) {
        this.specialisationService = specialisationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Specialisation>> findAll() {
        log.info("Retrieving a list of specialisations");
        List<Specialisation> specialisations = specialisationService.findAll();
        return new ResponseEntity<>(specialisations, specialisations.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialisation> find(@PathVariable Long id) {
        log.info("Retrieving a specialisation with id: " + id);
        Specialisation specialisation = specialisationService.find(id);
        return new ResponseEntity<>(specialisation, specialisation == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Specialisation> save(@RequestBody @Valid Specialisation specialisation) {
        log.info("Saving specialisation: " + specialisation.toString());
        Specialisation out = specialisationService.save(specialisation);
        log.info("Saved with id: " + out.getId());
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        log.info("Delete specialisation with id: " + id);
        if (specialisationService.delete(id)) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialisation> update(@PathVariable Long id, @RequestBody @Valid Specialisation specialisation) {
        log.info("Updating specialisation with id " + id + " to: " + specialisation.toString());
        Specialisation out = specialisationService.update(id, specialisation);
        return new ResponseEntity<>(out, out == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
