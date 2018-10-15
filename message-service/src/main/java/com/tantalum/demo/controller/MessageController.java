package com.tantalum.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tantalum.demo.entities.Message;
import com.tantalum.demo.service.MessageService;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private MessageService service;
	
	@Autowired
	private RestTemplate templete;
	
	private static final String UUID_URL = "http://localhost:8081/message-uuid";
	
	@GetMapping("/all")
	List<Message> all() {
		return service.getAllMessages();
	}
	
	@GetMapping("/get:{count}")
	List<Message> all(@PathVariable int count) {
		return service.getAllMessages(count);
	}

	@RequestMapping("/new/{message}")
	Message newEmployee(@PathVariable String message) {
		return service.saveNewMessage(new Message(message));
	}

	@GetMapping("/getmessage/{id}")
	Message one(@PathVariable Long id) {
		return service.getMessage(id);
	}

	@RequestMapping("/edit/{id}/{message}")
	ResponseEntity<String> update(@PathVariable Long id,@PathVariable String message) {
		boolean isUpdated = service.updateMessage(id,message);
		if(isUpdated) {
			return new ResponseEntity<String>("Message updated",HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Message NOT updated",HttpStatus.ACCEPTED);

	}

	@RequestMapping("/clean")
	HttpStatus deleteMessages() {
		service.deleteExpiredMessages();
		return HttpStatus.OK;
	}
	
	@RequestMapping("/gen-uuid/{id}")
	HttpStatus generateUUID(@PathVariable Long id) throws ObjectNotFoundException {
		
		ResponseEntity<String> resp = templete.getForEntity(UUID_URL, String.class);
		String uid = resp.getBody();
		service.updateUUID(id, uid);
		return HttpStatus.OK;
	}
	
}
