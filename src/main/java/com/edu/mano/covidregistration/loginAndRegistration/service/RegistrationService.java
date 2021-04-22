package com.edu.mano.covidregistration.loginAndRegistration.service;

import com.edu.mano.covidregistration.loginAndRegistration.model.Client;
import com.edu.mano.covidregistration.loginAndRegistration.repository.RegistrationRepository;
import com.edu.mano.covidregistration.loginAndRegistration.model.Client;
import com.edu.mano.covidregistration.loginAndRegistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository repo;

    public Client saveClient(Client client) {
        return repo.save(client);
    }

    public Client fetchClientByEmailId (String email) {
        return repo.findByEmailId(email);
    }

    public Client fetchClientByEmailIdAndPassword (String email, String password) {
        return repo.findByEmailIdAndPassword(email, password);
    }
}
