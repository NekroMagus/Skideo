package com.football.manager.model;

import com.football.manager.model.role.RoleInFootball;
import com.football.manager.model.role.RoleOnTheSite;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {
    private User user = new User();
    private User fullUser = new User("username", "password");

    @Test
    public void userTest() {
        user.setId(1);
        assertEquals(1, user.getId());
        user.setCountry("123");
        assertEquals("123", user.getCountry());
        user.setPassword("1");
        assertEquals("1", user.getPassword());
        user.setEmail("1@examplemail.com");
        assertEquals("1@examplemail.com", user.getEmail());
        user.setName("name");
        assertEquals("name", user.getName());
        user.setSurname("surname");
        assertEquals("surname", user.getSurname());
        user.setTelephoneNumber("number");
        assertEquals("number", user.getTelephoneNumber());
        user.setCity("address");
        assertEquals("address", user.getCity());
        user.setRoleOnTheSite(RoleOnTheSite.ADMIN);
        assertEquals(RoleOnTheSite.ADMIN, user.getRoleOnTheSite());
        fullUser.setRoleInFootball(RoleInFootball.CAM);
        assertEquals(RoleInFootball.CAM, fullUser.getRoleInFootball());
        user.setDateOfBirth(new Date(1990, 12, 12));
        assertEquals(new Date(1990, 12, 12), user.getDateOfBirth());
        Timestamp time = new Timestamp(new Date().getTime());
        user.setDateOfRegistration(time);
        assertEquals(time, user.getDateOfRegistration());
        user.setSocialNetwork("vk.com");
        assertEquals("vk.com", user.getSocialNetwork());
    }
}