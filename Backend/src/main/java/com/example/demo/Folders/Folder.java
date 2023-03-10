package com.example.demo.Folders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.example.demo.Users.User;
import com.example.demo.Notes.Note;


/**
 * This class is used to hold data for folders containing any note type and delegates work to those classes, when manipulating data with in the folder.
 * This class holds an ID number, a ArrayList<NoteInterface>(So either DefaultNote, ChecklistNote, or TableNote can be in the folder), and has a User attached to it.
 * The ID number of the note is unchangeable be design
 * 
 * @author Nick Pehl, Cameron Lettieri
 *
 *
 * 
 */
/**
 * @author nicho
 *
 */
@Entity
@Table(name = "folders")
public class Folder {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @OneToMany
	 @Column(name = "Notes", nullable = false)
	 private List<Note> notes;
	
	 @ManyToOne
	 @JoinColumn(name = "Users")
	 @JsonIgnore
	 private User user;
	 
	 
	 public Folder()
	 {
		 
	 }
	 /**
	 * Constucts a Folder Object, takes in an ArrayList<NoteInterface>, and a User.
	 * ID number is auto generated by SpringBoot
	 * @param folder contains DefaultNote, ChecklistNote, or TableNote
	 * @param user the User attached to this Note.
	 */
	 public Folder(List<Note> notes, User user)
	 {
		this.notes = notes;
		this.user = user;
	 }
	 
	 /**
	 * Constructs a Folder Object based off of an id and a User. Only used for testing purposes.
	 * @param id ID number of Test Folder
	 * @param user User for Test Folder
	 */
	 public Folder(int id, User user)
	 {
		this.user = user;
		this.id = id;
	 }
	 
	
	 /**
	 * Gets user for the Folder
	 * @return User attached to the Folder
	 */
	 public User getUser()
	 {
		 return user;
	 }
	 
	 /**
	 * changes the user that is attached to the Folder
	 * @param user new user attached to the Folder
	 */
	 public void setUser(User user)
	 {
		 this.user = user;
	 }
	 
	 /**
	 * Gets the ID number of the Folder
	 * @return
	 */
	 public int getID()
	 {
		 return id;
	 }
	 
	 /**
	 * Gets the ArrayList<Note> and returns everything in it.
	 * either DefaultNote, ChecklistNote, or TableNote class
	 * @return ArrayList<Note> which contains DefaultNote, ChecklistNote, or TableNote 
	 */
	 public List<Note> getFolder()
	 {
		 return notes;
	 }
	 
	 /**
	  * Adds a note to the folder
	  * either DefaultNote, ChecklistNote, or TableNote class
	  * @param note Note to add
	  * @return ArrayList<Note> which contains DefaultNote, ChecklistNote, or TableNote
	  */
	 public void addNoteIntoFolder(Note note)
	 {
		 notes.add(note);
	 }
	 
	 /**
	  * 
	  * Finds and deletes a particular note in a folder.
	 * @param note Note to be deleted, if note is not in folder, does nothing
	 * @return true if successful, false otherwise
	 */
	public boolean deleteNoteInFolder(Note note)
	 {
		 for(int i=0; i<notes.size();i++)
		 {
			 if(notes.get(i).getID() == note.getID())
			 {
				 notes.remove(i);
				 return true;
			 }
		 }
		 
		 return false;
	 }
	 
	  
	 

}
