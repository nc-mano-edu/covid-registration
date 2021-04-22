package com.edu.mano.covidregistration.loginAndRegistration.repository;

import com.edu.mano.covidregistration.loginAndRegistration.model.Client;
import com.edu.mano.covidregistration.loginAndRegistration.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository <Client, Integer>{

    public Client findByEmailId(String emailId);
    public Client findByEmailIdAndPassword(String emailId, String password);

}
