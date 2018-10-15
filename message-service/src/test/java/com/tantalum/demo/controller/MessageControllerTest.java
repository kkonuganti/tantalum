package com.tantalum.demo.controller;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.tantalum.demo.entities.Message;
import com.tantalum.demo.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {

	
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    private MessageService service;
    
	@Test
	public void testAll() {
		List<Message> all = service.getAllMessages();
		List<Message> messages = this.restTemplate.getForObject("http://localhost:" + port + "/messages/all",
                List.class);
		assertTrue(messages.size()==all.size());
	}


}
