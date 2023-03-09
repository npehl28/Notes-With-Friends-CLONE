package com.example.demo.Folders;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.demo.Notes.Note;
import com.example.demo.Notes.NoteController;
import com.example.demo.Notes.NoteRepository;
import com.example.demo.Users.User;

import junit.framework.Assert;

class FolderControllerTest {
	
	FolderRepository mockFolderRepository = Mockito.mock(FolderRepository.class);
	
	/*
	 * Author @Cameron-Lettieri
	 * Created a folder using a folder and user
	 * Then I created a mockFolderRepository to find a folder by ID and return the folder
	 * Then I created a folderController that used the mockFolderRepository
	 * Lastly, it checks to see if the repository matches the controller
	 */
	@Test
	void getFolderById_should_CallGetFolderById() 
	{
		//setup
		final Folder fakeFolder = new Folder(34, new User(32, "cam", "lettieri@gmail.com", "password"));
		
		Mockito.when(this.mockFolderRepository.findById(fakeFolder.getID())).thenReturn(fakeFolder);
		
		//act
		final FolderController folderController = new FolderController(this.mockFolderRepository);
		
		//assert
		Assert.assertEquals(folderController.getFolderById(fakeFolder.getID()), fakeFolder);
	}
	
	/*
	 * Author @Cameron Lettieri
	 * Created 2 fake folders and added them to an ArrayList of folders
	 * Then I created a mockFolderRepository to find all the folders and return the list
	 * Then I created a folderController that used the mockFolderRepository
	 * Lastly, it checks to see if the repository matches the controller
	 */
	@Test
	void getAllFolders_should_callFindAll()
	{
		//setup
		final Folder fakeFolder1 = new Folder(34, new User(28, "Cameron Lettieri", "email@email.com", "password"));
		final Folder fakeFolder2 = new Folder(17, new User(29, "Dan Lettieri", "email2@email.com", "password"));
		ArrayList<Folder> fakeList = new ArrayList<>();	
		fakeList.add(fakeFolder1);		
		fakeList.add(fakeFolder2);

		Mockito.when(this.mockFolderRepository.findAll()).thenReturn(fakeList);	
		
		//act
		final FolderController folderController = new FolderController(this.mockFolderRepository);
		
		//assert
		Assert.assertEquals(folderController.getAllFolders(), fakeList);
	}
	
	//Nicks Test
	@Test
	void updateFolderShouldCallFindById()
	{
		final Folder fakeFolder1 = new Folder(34, new User(28, "Nick Pehl", "email@email.com", "password"));
		final Folder fakeFolder2 = new Folder(17, new User(29, "Greg Pehl", "email2@email.com", "password"));
		
		final FolderController folderController = new FolderController(this.mockFolderRepository);
		
		ArrayList<Folder> fakeList = new ArrayList<>();	
		
		fakeList.add(fakeFolder1);	
		fakeList.add(fakeFolder2);
		
		String success = "{\"message\":\"success\"}";
		
		Mockito.when(this.mockFolderRepository.findById(29)).thenReturn(fakeFolder2);
		
		Assert.assertEquals(folderController.updateFolder(29, fakeFolder2), fakeFolder2);
		
		
	}

}
