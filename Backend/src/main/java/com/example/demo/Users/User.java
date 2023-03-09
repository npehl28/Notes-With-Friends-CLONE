package com.example.demo.Users;

import com.example.demo.Folders.Folder;
import com.example.demo.Notes.Note;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


/**
 * Holds data about Users. Each users has a ID number, a name, an email, a password, and a list of notes they are apart of.
 * @author Nick Pehl, Cam Lettieri
 * 
 * 
 */
/**
 * @author Nick Pehl and Cameron Lettieri
 *
 */
@Entity
@Table(name = "users")
public class User {

     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "Email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "Password", nullable = false, unique = true)
    private String password;
    
    @ManyToMany
    private List<Note> noteList;
    
    @OneToMany
    private List<Folder> folderList;


    /**
     * Constructor for a User object. Each users has a ID number, a name, an email, a password, and a list of notes they are apart of.
     * List is empty to begin with
     * @param id ID number of User
     * @param name Users name
     * @param email Users email
     * @param password Users password
     */
    public User(int id, String name, String email, String password) 
    {
    	this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        noteList = new ArrayList<Note>();
        //folderList = new ArrayList<Folder>();
    }

    /**
     * Makes a user with no name, an auto generated id number, an empty list of notes, no email and no password
     */
    public User() 
    {
    	noteList = new ArrayList<Note>();
    }

    //  Getters and Setters for each field

    /**
     * Gets the Id number for the User
     * @return
     */
    public int getId(){
        return id;
    }

    /**
     * Changes the ID number of the user
     * @param id new ID number
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the Name of the user
     * @return name of user as a String
     */
    public String getName(){
        return name;
    }

    /**
     * Changes name of user
     * @param name new name of user
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets users email
     * @return User email as a String
     */
    public String getEmail(){
        return email;
    }

    /**
     * Changes user email
     * @param email new User email
     */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * Gets user password
     * @return password of user
     */
    public String getPassword()
    {
    	return this.password;
    }
    /**
     * Changes the User password
     * @param password new password
     */
    public void changePassword(String password)
    {
    	this.password= password;
    }
    /**
     * Gets the full list of notes the user is apart of
     * @return List of Notes
     */
    public List<Note> getNotes()
    {
    	return this.noteList;
    }
    /**
     * Changes the User Note list to a new list
     * @param noteList new List of notes the user is apart of
     */
    public void setNotes(List<Note> noteList) {
        this.noteList = noteList;
    }

    /**
     * Adds a new note to the list of notes the user is apart of
     * @param note Note to be added
     */
    public void addNote(Note note){
        this.noteList.add(note);
    }
    
    /**
     * Gets the list of Folders associated with this user
     * @return the list of folders
     */
    public List<Folder> getFolders()
    {
    	return folderList;
    }
    
    /**
     * Adds a folder to the list of folders
     * @param folder folder to be added
     */
    public void addFolder(Folder folder)
    {
    	folderList.add(folder);
    }

}
