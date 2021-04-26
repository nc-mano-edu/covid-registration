package com.edu.mano.covidregistration.service;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public User saveUser(User user) {
        return registrationRepository.save(user);
    }

    public User fetchUserByEmail (String email) {
        return registrationRepository.findByEmail(email);
    }

    public User fetchUserByEmailAndPassword (String email, String password) {
        return registrationRepository.findByEmailAndPassword(email, password);
    }
}
