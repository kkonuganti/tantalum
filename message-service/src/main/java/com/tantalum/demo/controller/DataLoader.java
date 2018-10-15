package com.tantalum.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tantalum.demo.entities.Message;
import com.tantalum.demo.service.MessageService;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private MessageService service;
	
	@Override
	public void run(String... args) throws Exception {
		service.saveNewMessage(new Message("msg1"));
		service.saveNewMessage(new Message("msg2"));
		service.saveNewMessage(new Message("msg3"));
		service.saveNewMessage(new Message("msg4"));
		service.saveNewMessage(new Message("msg5"));
		
	}

}
