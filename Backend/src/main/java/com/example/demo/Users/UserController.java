package com.example.demo.Users;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.Notes.*;
import com.example.demo.Folders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class Controls Users in the repository. Has GET, POST, PUT, and DELETE mappings
 * @author Nick Pehl, Cameron Lettieri
 *
 *
 * .
 */
@RestController
public class UserController {

    UserRepository userRepository;

    NoteRepository noteRepository;
    
    FolderRepository folderRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    /**
     * Constructor for a UserController, takes in a UserRepostiory
     * Automatically done by SpringBoot, so probably don't worry about this one.
     * @param userRepository Repository for users
     */
    @Autowired
    public UserController(UserRepository userRepository,NoteRepository noteRepository, FolderRepository folderRepository) {
    	this.userRepository = userRepository;
    	this.noteRepository = noteRepository;
    	this.folderRepository = folderRepository;
    }

    /**
     * GET request to get all users
     * @return List of all users in repository 
     */
    @GetMapping(path = "/users")
	public
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * GET request for a User with a specific ID number
     * @param id ID number of user
     * @return User with that ID number
     */
    @GetMapping(path = "/users/{id}")
	public
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }
    
    @GetMapping(path = "/notes/user/{id}")
    public
    List<Note> getNotesByUserId(@PathVariable int id){
    	User user = userRepository.findById(id);
    	return user.getNotes();
    }

    /**
     * Makes a new user to add to the repository
     * @param user User to be added
     * @return a success message if successful or a fail message if failed to add to repository
     */
    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        List<User> allUsers = getAllUsers();
        for(User u:allUsers)
        {
        	if(user.getEmail().equals(u.getEmail()))
        		return "Email already in use, please use a different email";
        }
        
        userRepository.save(user);
        return success;
    }

    /**
     * Updates User data with a specific ID number.
     * 
     * @param id ID number of the user to be updated
     * @param request updates to the User object
     * @return the updated User object
     */
    @PutMapping("/users/{id}")
    User updateUser(HttpServletResponse response, @PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        if(user.getId() != request.getId())
			try {
				response.getWriter().println("id of request body and mapping do not match");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        userRepository.save(request);
        return userRepository.findById(id);
    }   
    
    /**
     * Assigns a specific note to a specific user
     * @param userId User's ID number
     * @param noteId Note's ID number
     * @return a success message if successful or a fail message if failed
     */
    @PutMapping("/users/{userId}/notes/{noteId}")
    String assignNoteToUser(@PathVariable int userId,@PathVariable int noteId){
        User user = userRepository.findById(userId);
        Note note = noteRepository.findById(noteId);
        if(user == null || note == null )
            return failure;
        if(user.getNotes().contains(note))
        {
        	return "user and note have been previously linked";
        }
        note.addUser(user);
        user.addNote(note);
        userRepository.save(user);
        noteRepository.save(note);
        return success;
    }
    
    @PutMapping("/users/{userId}/folder/{folderId}")
    String assignFolderToUser(@PathVariable int userId,@PathVariable int folderId){
        User user = userRepository.findById(userId);
        Folder folder = folderRepository.findById(folderId);
        if(user == null || folder == null )
            return failure;
        if(user.getFolders().contains(folder))
        {
        	return "user and note have been previously linked";
        }
        folder.setUser(user);
        user.addFolder(folder);
        userRepository.save(user);
        folderRepository.save(folder);
        return success;
    }

    
    /**
     * Deletes user from repository with an ID number
     * @param id ID number of user to be deleted
     * @return a success message if successful or a fail message if failed
     */
    @DeleteMapping(path = "/users/{id}")
    String deleteUser(@PathVariable int id){
        User user = userRepository.findById(id);
        if(user == null)
        {
        	return "id of user does not exist in database";
        }
    	
    	userRepository.deleteById(id);
        return success;
    }
}
