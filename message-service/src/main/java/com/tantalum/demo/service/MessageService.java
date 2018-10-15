package com.tantalum.demo.service;

import java.util.List;

import com.tantalum.demo.entities.Message;

/**
 * @author k.konuganti
 *
 */

public interface MessageService {
	
	void deleteAllMessages();

	public List<Message> getAllMessages();
	
	public void deleteExpiredMessages();
	
	public Message saveNewMessage(Message msg);
	
	public List<Message> getAllMessages(int limit);
	
	public boolean updateMessage(Message msg);

	void deleteExpiredMessages(Long time);

	Message getMessage(Long id);

	boolean updateMessage(Long id, String message);
	
	Message updateUUID(Long id, String message);
}
