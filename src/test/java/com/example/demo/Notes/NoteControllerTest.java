package com.example.demo.Notes;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Users.*;
import com.example.demo.Notes.*;
import junit.framework.Assert;

class NoteControllerTest {
	NoteRepository mockNoteRepository = Mockito.mock(NoteRepository.class);

	//Author @Cameron Lettieri
	@Test
	void getNoteById_should_CallGetNoteById() {
		//setup
		final Note fakeNote = new Note(34, new User(32, "cam", "lettieri@gmail.com", "password"));
		
		Mockito.when(this.mockNoteRepository.findById(fakeNote.getID())).thenReturn(fakeNote);
		
		//act
		final NoteController noteController = new NoteController(this.mockNoteRepository, null, null);
		
		//assert
		Assert.assertEquals(noteController.getNoteById(fakeNote.getID()), fakeNote);
	}
	
	//Author @Nick Pehl
	@Test
	void getAllNotes_should_callFindAll()
	{
		final Note fakeNote1 = new Note(28, new User(28, "nick", "email@email.com", "pass"));
		
		final Note fakeNote2 = new Note(29, new User(29, "joe", "email2@email.com", "pass"));
		
		Note[] temp = {fakeNote1, fakeNote2};
		
		ArrayList<Note> fakeList = new ArrayList<>();
		
		fakeList.add(fakeNote1);
		
		fakeList.add(fakeNote2);
		
		Mockito.when(this.mockNoteRepository.findAll()).thenReturn(fakeList);
		
		final NoteController noteCon = new NoteController(this.mockNoteRepository, null, null);
		
		Assert.assertEquals(noteCon.getAllNotes(), fakeList);
	}
	//Nicks Test
	@Test
	void updateNoteShouldCallFindById()
	{
		final Note fakeNote1 = new Note(28, new User(28, "nick", "email@email.com", "pass"));
		
		final Note fakeNote2 = new Note(29, new User(29, "joe", "email2@email.com", "pass"));
		
		ArrayList<Note> fakeList = new ArrayList<>();
		
		fakeList.add(fakeNote1);
		
		fakeList.add(fakeNote2);
		
		Mockito.when(this.mockNoteRepository.findById(29)).thenReturn(fakeNote2);
		
		final NoteController noteCon = new NoteController(this.mockNoteRepository, null, null);
		
		Assert.assertEquals(noteCon.updateNote(29, fakeNote2), fakeNote2);

	}

}
