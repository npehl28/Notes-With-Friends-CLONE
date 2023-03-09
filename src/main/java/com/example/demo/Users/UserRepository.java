package com.example.demo.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
/**
 * This is an interface to manipulate data in the repository, Used by the controller.
 * Almost all of the work is done by JpaRepostiory, so refer to that for most purposes.
 * @author Nick Pehl, Cam Lettieri
 * 
 * 
 *
 */
public interface UserRepository extends JpaRepository<User, Long> 
{
	    
	   User findById(int id);

	   @Transactional
	    void deleteById(int id);
}

