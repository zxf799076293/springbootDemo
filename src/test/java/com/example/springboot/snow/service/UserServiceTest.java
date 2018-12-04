package com.example.springboot.snow.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void createUser() {
        userService.createUser("13311111111","000000");
        userService.createUser("13322222222","111111");
    }

    @Test
    public void getUser() {
        userService.getUser(238);
        System.out.print(userService.getUser(238).getTel());
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUserByUserId() {
    }
}