package com.example.demo.Users;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Notes.*;
import com.example.demo.Users.*;
import junit.framework.Assert;

class UserControllerTest {
	UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

	//Author @Cameron-Lettieri
	@Test
	void getUserById_should_CallGetUserById()
	{
		//setup
		final User fakeUser = new User(32, "cam", "lettieri@gmail.com", "password");
		
		Mockito.when(this.mockUserRepository.findById(fakeUser.getId())).thenReturn(fakeUser);
		
		//act
		final UserController userController = new UserController(this.mockUserRepository, null, null);
		
		//assert
		Assert.assertEquals(userController.getUserById(fakeUser.getId()), fakeUser);
		
	}
	
	//Author @Nick Pehl
	@Test
	void getAllUsers_should_callFindAll()
	{
		final User fakeUser1 = new User(28, "nick", "email@email.com", "pass");
		
		final User fakeUser2 = new User(29, "joe", "email2@email.com", "pass");
		
		ArrayList<User> fakeList = new ArrayList<>();
		
		final UserController userCon = new UserController(this.mockUserRepository, null, null);
		
		Mockito.when(this.mockUserRepository.findAll()).thenReturn(fakeList);
		
		Assert.assertEquals(userCon.getAllUsers(), fakeList);
	}

}
