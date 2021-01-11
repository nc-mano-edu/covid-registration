package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.domain.Specialisation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class SpecialisationServiceTest {

    @Autowired
    private SpecialisationService specialisationService;

    final Specialisation s = new Specialisation(1, "MockSpec", "MockDisc");

    @Test
    public void getListSpecialisationTest() {
        Object result = specialisationService.findAll();
        Assertions.assertNotEquals(null, result);
        Assertions.assertEquals(ArrayList.class, result.getClass());

        List<Specialisation> specialisations = new ArrayList<>();
        specialisations.add(s);
        Assertions.assertEquals(specialisations, result);
    }

    @Test
    public void getOneSpecialisationTest() {
        Object result = specialisationService.find(1L);
        Assertions.assertNotEquals(null, result);
        Assertions.assertEquals(Specialisation.class, result.getClass());
        Assertions.assertEquals(s, result);
    }

    @Test
    public void saveSpecialisationTest() {
        Object result = specialisationService.save(s);
        Assertions.assertNotEquals(null, result);
        Assertions.assertEquals(Specialisation.class, result.getClass());
        Assertions.assertEquals(s, result);
    }

    @Test
    public void deleteSpecialisationTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(specialisationService.delete(1L)),
                () -> Assertions.assertFalse(specialisationService.delete(2L))
        );
    }

    @Test
    public void updateSpecialisationTest() {
        Object result = specialisationService.update(1L, s);
        Assertions.assertNotEquals(null, result);
        Assertions.assertEquals(Specialisation.class, result.getClass());
        Assertions.assertEquals(s, result);
    }

}
