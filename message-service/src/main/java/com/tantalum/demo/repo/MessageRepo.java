package com.tantalum.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.tantalum.demo.entities.Message;

/**
 * @author k.konuganti
 * @version 1.0
 * This Class acts as a repo which will provide CRUD operations
 *
 */
public interface MessageRepo extends CrudRepository<Message, Long>{

	
}
