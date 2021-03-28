package com.edu.mano.covidregistration.rest;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Roles;
import com.edu.mano.covidregistration.domain.User;
import com.edu.mano.covidregistration.domain.UserRoles;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.relation.Role;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRolesApiTest extends SpringBootTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    List<UserRoles> listOfUserRoles = Arrays.asList(new UserRoles(1L, (new User(1L, "John", "Jo", "Jameson", null, new SimpleDateFormat("dd.MM.yyyy").parse("28.04.2001"), null, null)), Collections.singletonList(new Roles(30L, "Liquid snake", "Not a Solid Ocelot")))) ;

    UserRoles newUserRole = new UserRoles(2L, (new User(2L, "Peter", "Jo", "Jameson", null, new SimpleDateFormat("dd.MM.yyyy").parse("27.04.2001"), null, null)),Collections.singletonList(new Roles(30L, "Liquid snake", "Not a Solid Ocelot")));

    public UserRolesApiTest() throws ParseException {
    }

    @Test
    public void checkFindAll() throws Exception {
        mockMvc.perform(get("/UserRoles/getUserRoles"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfUserRoles)));
    }


    @Test
    public void checkFind() throws Exception {
        mockMvc.perform(get("/UserRoles/getUserRoleById/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfUserRoles.get(0))));    }

    @Test
    public void checkLifeCycle() throws Exception {

        Assertions.assertAll(
                () -> checkAdd(),
                () -> checkUpdate(),
                () -> checkDelete()
        );
    }

    private void checkAdd() throws Exception {

        mockMvc.perform(
                post("/UserRoles/makeUserRole")
                        .content(objectMapper.writeValueAsString(newUserRole))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

    }

    public void checkUpdate() throws Exception {

        UserRoles editedUserRole = new UserRoles(1L, (new User(3L, "Peter", "Jo", "Jameson", null, new SimpleDateFormat("dd.MM.yyyy").parse("27.04.2001"), null, null)),Collections.singletonList(new Roles(30L, "Liquid snake", "Not a Solid Ocelot")));

        mockMvc.perform(
                put("/UserRoles/editUserRole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(editedUserRole)))
                .andExpect(status().isOk()
                );
    }

    public void checkDelete() throws Exception {

        mockMvc.perform(
                delete("/UserRoles/deleteUserRole/" + 1L))
                .andExpect(status().isOk()
                );
    }


}
