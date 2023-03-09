package com.example.demo.Notes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.example.demo.Folders.Folder;
import com.example.demo.Users.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class is used to hold data for any note type and deligates work to those classes, when manipulating data with in the note.
 * This class holds an ID number, a NoteInterface class(So either DefaultNote, ChecklistNote, or TableNote), and has a User attached to it.
 * The ID number of the note is unchangeable be design
 * 
 * @author Nick Pehl, Cameron Lettieri
 *
 *
 * 
 */

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "noteType", visible = true)
//@JsonSubTypes({
//	@JsonSubTypes.Type(value = TableNote.class, name = "TABLE"),
//	@JsonSubTypes.Type(value = ChecklistNote.class, name = "CHECK"),	
//	@JsonSubTypes.Type(value = Note.class, name = "DEFAULT")	
//})

//@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "note")
public class Note 
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;

	 @ManyToMany
	 @JoinColumn(name = "Users")
	 @JsonIgnore
	 private List<User> userList;


	 private NoteType noteType;
	 
	 @OneToMany
	 private List<TextItem> contents;
	 
	 private String name;
	 
	 @JsonIgnore
	 @ManyToOne
	 private Folder folder;
	 
	 private int xDim;
	 
	 private int yDim;
	 
	 
	 
	 public Note()
	 {
		 
	 }
	 public Note(int id, User user)
	 {
		userList = new ArrayList<User>();
		 userList.add(user);
		this.id = id;
	 }
	 /**
	 * Gets user for the note
	 * @return User attached to the note
	 */
	public List<User> getUserList()
	 {
		 return userList;
	 }
	
	/**
	 * Adds a User to the list of users associated with the note, unless the user is already on the note
	 * @param user User to add to the note
	 */
	public void addUser(User user)
	{
		if(!userList.contains(user))
		{
			userList.add(user);
		}
	}
	
	 /**
	 * Removes a User from the note
	 * @param user User to be removed from the note
	 * @return true if removal successful, false if not successful
	 */
	public boolean removeUser(User user)
	{
		for(int i=0; i<userList.size(); i++)
		{
			if(userList.get(i).getId() == user.getId())
			{
				userList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	 /**
	 * Gets the ID number of the note.
	 * @return
	 */
	public int getID()
	 {
		 return id;
	 }
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	

	 /**
	  * Gets the note type
	  * either DEFAULT, CHECK, or TABLE
	 * @return NoteType enum of the NoteInterface class within this note
	 */
	public NoteType getNoteType()
	 {
		 return noteType;
	 }
	 
	/**
	 * Gets the contents of the Note as a String. The String will be in different formats based on what type of note it is.
	 * DEFAULT: it is just a regular string, nothing fancy (no format)
	 * CHECK: String format is an item followed by its checked status separated by commas (e.g. "pizza, false, pasta, true")
	 * TABLE: String format is two numbers separated by commas to represent the dimensions of the table, followed by the items in the table form left to right (like a book) 
	 * (e.g. 2, 1, pizza, pasta)
	 * @return String representing the contents of the Note
	 */
	public List<TextItem> getContents()
	 {
		 return contents;
	 }
	
	
	public void addItem(TextItem item)
	{
		contents.add(item);
	}
	/**
	 * Updates the String representing the Note to be the String contents
	 * @param conents updated contents of the Note
	 */
	public void updateContents(List<TextItem> contents)
	{
		this.contents = contents;
	}
	
	public void setFolder(Folder folder)
	{
		this.folder = folder;
	}
	public Folder getFolder()
	{
		return folder;
	}
	
	public int getXDim()
	{
		return xDim;
	}
	
	public int getYDim()
	{
		return yDim;
	}
	
	public void setXDim(int xDim)
	{
		this.xDim = xDim;
	}
	public void setYDim(int yDim)
	{
		this.yDim = yDim;
	}
}

