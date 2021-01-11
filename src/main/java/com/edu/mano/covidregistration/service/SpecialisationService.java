package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Specialisation;
import com.edu.mano.covidregistration.repository.SpecialisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialisationService {

    private final SpecialisationRepository specialisationRepository;

    @Autowired
    SpecialisationService(SpecialisationRepository specialisationRepository) {
        this.specialisationRepository = specialisationRepository;
    }

    public List<Specialisation> findAll() {
        return specialisationRepository.findAll();
    }

    public Specialisation find(Long id) {
        return specialisationRepository.findById(id).orElse(null);
    }

    public Specialisation save(Specialisation specialisation) {
        return specialisationRepository.save(specialisation);
    }

    public boolean delete(Long id) {
        if (specialisationRepository.existsById(id)) {
            specialisationRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public Specialisation update(Long id, Specialisation specialisation) {
        if (specialisationRepository.existsById(id)) {
            specialisation.setId(id);
            return save(specialisation);
        }
        else {
            return  null;
        }
    }

}
