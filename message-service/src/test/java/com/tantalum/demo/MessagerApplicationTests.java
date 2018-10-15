package com.tantalum.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tantalum.demo.controller.MessageController;
import com.tantalum.demo.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagerApplicationTests {
	
	@Autowired
	MessageService service;
	
	@Autowired
	MessageController controller;
	
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(service).isNotNull();
        
    }


}
