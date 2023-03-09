package com.example.demo.Folders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class Controls Folders in the repository. Has GET, POST, PUT, and DELETE mappings
 * @author Nick Pehl, Cameron Lettieri
 *
 *
 * .
 */
@RestController
public class FolderController {
	
	FolderRepository folderRepository;
	
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    /**
     * Constructor for a FolderController, takes in a FolderRepostiory
     * Automatically done by SpringBoot, so probably don't worry about this one.
     * @param folderRepository Repository for folders
     */
    @Autowired
    public FolderController(FolderRepository folderRepository)
    {
    	this.folderRepository = folderRepository;
    }
    
    /**
     * GET request to get the folder.
     * @return All folders from a user
     */
    @GetMapping(path = "/folders")
	public List<Folder> getAllFolders()
    {
        return folderRepository.findAll();
    }
    
    /**
     * GET request for a Folder with a specific id
     * @param id The ID of the Folder
     * @return Folder with that id
     */
    @GetMapping(path = "/folders/{id}")
	public Folder getFolderById( @PathVariable int id)
    {
        return folderRepository.findById(id);
    }
    
    /**
     * POST request to create a new Folder in the repository. 
     * @param Folder folder object to add to repository 
     * @return a success message if successful or a fail message if failed to add to repository
     */
    @PostMapping(path = "/folders")
    String createFolder(@RequestBody Folder folder)
    {
        if (folder == null)
            return failure;
        folderRepository.save(folder);
        return success;
    }   
	 
    /**
     * PUT request to update an existing Folder in the repository.
     * 
     * @param id the id of the folder to be updated
     * @param request Updates to that folder
     * @return The updated folder
     */
    @PutMapping("/folders/{id}")
    Folder updateFolder(@PathVariable int id, @RequestBody Folder request)
    {
    	Folder folder = folderRepository.findById(id);
        if(folder == null)
            return null;
        folderRepository.save(request);
        return folderRepository.findById(id);
    }
    
    /**
     * Deletes user from repository with an ID number
     * @param id ID number of user to be deleted
     * @return a success message if successful or a fail message if failed
     */
    @DeleteMapping(path = "/folders/{id}")
    String deleteFolder(@PathVariable int id)
    {
        folderRepository.deleteById(id);
        return success;
    }

}
