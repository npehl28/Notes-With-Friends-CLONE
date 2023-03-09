package com.example.demo.Notes;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


	/**
	 * This is an interface to manipulate data in the repository, Used by the controller.
	 * Almost all of the work is done by JpaRepostiory, so refer to that for most purposes.

	 * @author Nick Pehl, Cam Lettieri
	 * 
	 * 	 *
	 */
	public interface NoteRepository extends JpaRepository<Note, Long> 
	{
	    
	    Note findById(int id);

	   @Transactional
	    void deleteById(int id);
	}

