package com.edu.mano.covidregistration.service;

import com.edu.mano.covidregistration.SpringBootTests;
import com.edu.mano.covidregistration.domain.Attribute;
import com.edu.mano.covidregistration.service.attributeService.AttributeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AttributeServiceTests extends SpringBootTests {

    @Autowired
    AttributeService attributeService;

    @Test
    @Order(1)
    public void checkFindAll() {
        Assertions.assertFalse(attributeService.findAll().isEmpty());
    }

    @Test
    @Order(2)
    public void checkFind() {
        Attribute res = attributeService.find(1L);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1L, res.getId()),
                () -> Assertions.assertEquals("User age", res.getName()),
                () -> Assertions.assertNotEquals(null, res.getAttributeType())
        );
    }

    @Test
    @Order(3)
    public void checkAdd() {
        Assertions.assertEquals(HttpStatus.OK, attributeService.add(new Attribute()).getStatusCode());
    }

    @Test
    @Order(4)
    public void checkUpdate() {
        Attribute attr = new Attribute();
        attr.setId(2L);
        attr.setName("Name");
        Assertions.assertEquals(HttpStatus.OK, attributeService.update(2L, attr).getStatusCode());
    }

    @Test
    @Order(5)
    public void checkDelete() {
        Assertions.assertTrue(attributeService.delete(2L));
    }
}
