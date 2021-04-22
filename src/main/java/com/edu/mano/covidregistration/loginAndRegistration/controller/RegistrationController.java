package com.edu.mano.covidregistration.loginAndRegistration.controller;


import com.edu.mano.covidregistration.loginAndRegistration.model.Client;
import com.edu.mano.covidregistration.loginAndRegistration.service.RegistrationService;
import com.edu.mano.covidregistration.loginAndRegistration.model.Client;
import com.edu.mano.covidregistration.loginAndRegistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registeruser")
    public Client registerUser (@RequestBody Client client) throws Exception {
        String tempEmail = client.getEmailId();
        if (tempEmail != null && !"".equals(tempEmail)) {
            Client clientObj  = service.fetchClientByEmailId(tempEmail);
            if (clientObj != null ) {
                throw new Exception("client with" +  tempEmail  + " is already exist ");
            }
        }

        Client clientObj = null;
        clientObj = service.saveClient(client);
        return clientObj;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Client loginUser (@RequestBody Client client) throws Exception {
        String tempEmailId = client.getEmailId();
        String tempPassword = client.getPassword();
        Client clientObj = null;

        if (tempEmailId != null && tempPassword != null) {
            clientObj = service.fetchClientByEmailIdAndPassword(tempEmailId, tempPassword);
        }
        if (clientObj == null) {
            throw new Exception("bad credentials");
        }

        return  clientObj;
    }

}
