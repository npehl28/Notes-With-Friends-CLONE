package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Users.*;

import org.junit.Assert;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@SpringBootTest
@AutoConfigureMockMvc
class BackendTestApplication {
	

	@InjectMocks
	User user;

	@MockBean
	UserRepository repo;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAccountByIdTest() {
		Mockito.when(repo.findById(1)).thenReturn(new User(1, "Cam", "lettieri@iastate.edu", "password"));

		User userOne = repo.findById(1);

		Assert.assertEquals("Cam", userOne.getId());
		Assert.assertEquals("password", userOne.getPassword());
		Assert.assertEquals("lettieri@iastate.edu", userOne.getEmail());
	}


	@Test
	void contextLoads() {
	}

}
