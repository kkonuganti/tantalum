/**
 * 
 */
package com.tantalum.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tantalum.demo.entities.Message;
import com.tantalum.demo.repo.MessageRepo;


/**
 * @author k.konuganti
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	/* (non-Javadoc)
	 * @see com.tantalum.demo.service.MessageService#getAllMessages()
	 */
	
	@Autowired
	private MessageRepo repo;
	
	private static final Long EXPIRY_TIME = 120000L;
	private static final Long EDIT_TIME = 10000L;

	
	@Override
	public List<Message> getAllMessages() {
		ArrayList<Message> messages = new ArrayList<>();
		repo.findAll().iterator().forEachRemaining(message ->{
			messages.add(message);
		});
		return messages;
	}

	/* (non-Javadoc)
	 * @see com.tantalum.demo.service.MessageService#deleteExpiredMessages()
	 */
	@Override
	public void deleteExpiredMessages() {
		repo.findAll().iterator().forEachRemaining(message ->{
			if(isExpired(message,MessageServiceImpl.EXPIRY_TIME)) {
				repo.delete(message);
			}
		});

	}
	
	@Override
	public void deleteExpiredMessages(Long time) {
		repo.findAll().iterator().forEachRemaining(message ->{
			if(isExpired(message,time)) {
				repo.delete(message);
			}
		});

	}

	protected boolean isExpired(Message msg,Long time) {
		long currentTimeStamp = new Date().getTime();
		long messageTimeStamp = msg.getTimeStamp().getTime();
		long diffInTimeStamp = currentTimeStamp-messageTimeStamp;
		if(diffInTimeStamp>time) {
			return true;
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see com.tantalum.demo.service.MessageService#saveNewMessgae(com.tantalum.demo.entities.Message)
	 */
	@Override
	@Async
	public Message saveNewMessage(Message msg) {
		
		return repo.save(msg);
	}

	/* (non-Javadoc)
	 * @see com.tantalum.demo.service.MessageService#getAllMessages(int)
	 */
	@Override
	public List<Message> getAllMessages(int limit) {
		List<Message> msglist = getAllMessages();
		if(limit > msglist.size()) {
			return msglist;
		}
		return msglist.subList(0, limit);
	}

	/* (non-Javadoc)
	 * @see com.tantalum.demo.service.MessageService#updateMessage(com.tantalum.demo.entities.Message)
	 */
	@Override
	@Async
	public boolean updateMessage(Message msg) {
		long currentTimeStamp = new Date().getTime();
		if((currentTimeStamp-msg.getTimeStamp().getTime())>MessageServiceImpl.EDIT_TIME) {
			return false;
		}
		repo.save(msg);
		return true;
	}
	
	@Override
	public Message getMessage(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void deleteAllMessages() {
		repo.deleteAll();
		
	}

	@Override
	public boolean updateMessage(Long id, String message) {
		if(repo.existsById(id)) {
			Message msg = repo.findById(id).get();
			msg.setMessage(message);
			return updateMessage(msg);
		}
			
		return false;
	}

	@Override
	public Message updateUUID(Long id, String uid){
		if(repo.existsById(id)) {
			Message msg = repo.findById(id).get();
			msg.setUuid(uid);
			return repo.save(msg);
		}
		throw new IllegalArgumentException("Message with" +id+ "not found");
	}

}
