/**
 * 
 */
package com.example.demo.Notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nicho
 *
 */
public interface TextItemRepository extends JpaRepository<TextItem, Long>
{
	TextItem findById(int id);

	   @Transactional
	    void deleteById(int id);
}
