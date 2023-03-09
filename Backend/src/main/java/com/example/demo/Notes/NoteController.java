package com.example.demo.Notes;

import java.util.List;

import com.example.demo.Folders.*;
import com.example.demo.Users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * This class Controls notes in the repository. Has GET, POST, and PUT mappings.
 * @author Nick Pehl, Cameron Lettieri
 *
 *
 * 
 */
@RestController
public class NoteController 
{
    NoteRepository noteRepository;
    FolderRepository folderRepository;
    TextItemRepository textItemRepo;
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    /**
     * Constructor for a NoteController, takes in a NoteRepostiory
     * Automatically done by SpringBoot, so probably don't worry about this one.
     * @param noteRepository Repository for notes
     */
    @Autowired
    public NoteController(NoteRepository noteRepository,  FolderRepository folderRepository, TextItemRepository textItemRepo) {
    	this.noteRepository = noteRepository;
    	this.folderRepository = folderRepository;
    	this.textItemRepo = textItemRepo;
    }
    

    /**
     * GET request to get all of the notes.
     * @return All note objects in a List
     */
    @GetMapping(path = "/notes")
	public
    List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    /**
     * GET request for a Note with a specific id
     * @param id The ID of the note
     * @return Note with that id
     */
    @GetMapping(path = "/notes/{id}")
	public
    Note getNoteById( @PathVariable int id){
        return noteRepository.findById(id);
    }
    
    @GetMapping(path = "/items")
	public
    List<TextItem> getAllItems(){
        return textItemRepo.findAll();
    }
    

    /**
     * POST request to create a new Note in the repository. 
     * @param note Note object to add to repository 
     * @return a success message if successful or a fail message if failed to add to repository
     */
    @PostMapping(path = "/notes")
    String createNote(@RequestBody Note note){
        if (note == null)
            return failure;
        //System.out.println(note.getClass().getName());  //testing purposes
        if(note.getContents() != null)
        {
     	   for(TextItem item: note.getContents())
     	   {
     		   textItemRepo.save(item);
     	   }
        }
       noteRepository.save(note);
       
        return success;
    }
    
    @PutMapping("/note/{noteId}/folder/{folderId}")
    String assignNoteToFolder(@PathVariable int noteId,@PathVariable int folderId){
        Folder folder = folderRepository.findById(folderId);
        Note note = noteRepository.findById(noteId);
        if(folder == null || note == null )
            return failure;
        if(folder.getFolder().contains(note))
        {
        	return "folder and note have been previously linked";
        }
        note.setFolder(folder);
        folder.addNoteIntoFolder(note);
        folderRepository.save(folder);
        noteRepository.save(note);
        return success;
    }
    
    @PutMapping("/note/{noteId}/item/{itemId}")
    String assignNoteToItem(@PathVariable int noteId,@PathVariable int itemId)
    {
    	TextItem item = textItemRepo.findById(itemId);
    	Note note = noteRepository.findById(noteId);
    	 if(item == null || note == null )
             return failure;
    	 if(note.getContents().contains(item))
    	 {
    		 return "item and note have already been linked";
    	 }
    	 note.addItem(item);
    	 item.setNote(note);
    	 textItemRepo.save(item);
    	 noteRepository.save(note);
         return success;
    }
    
    @PostMapping("/item")
    String createItem(@RequestBody TextItem item)
    {
    	if(item == null)
    		return failure;
    	textItemRepo.save(item);
    	return success;
    }

    /**
     * PUT request to update a existing Note in the repository.
     * 
     * @param id the id of the note to be updated
     * @param request Updates to that note
     * @return The updated note
     */
    @PutMapping("/notes/{id}")
    Note updateNote(@PathVariable int id, @RequestBody Note request){
        Note note = noteRepository.findById(id);
        if(note == null)
            return null;
        noteRepository.save(request);
        return noteRepository.findById(id);
    } 
 }

