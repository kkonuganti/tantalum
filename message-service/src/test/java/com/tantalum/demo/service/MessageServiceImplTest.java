package com.tantalum.demo.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.tantalum.demo.entities.Message;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({MessageServiceImpl.class})
public class MessageServiceImplTest {
	
	@MockBean
	private RestTemplate templete;
	
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    MessageService service ;
    
	
	Message msg = new Message("hi"),
			msg2 = new Message("msg2"),
			msg3=new Message("msg3"),
			msg4=new Message("mesg4");
    
    @Before
    public void setup() {
    	entityManager.persist(msg);
    	entityManager.persist(msg2);
    	entityManager.persist(msg3);
    	service.getAllMessages().iterator().forEachRemaining(mess->{System.out.println(mess);});
    }
    
    @After
    public void cleanup() {
    	service.deleteAllMessages();
    }


	@Test
	public void testGetAllMessages() {
		Assert.assertTrue(service.getAllMessages().size()==3);
	}

	@Test
	public void testDeleteExpiredMessages() throws InterruptedException {
		Assert.assertTrue(service.getAllMessages().size()==3);
		Thread.sleep(1000L);
		service.deleteExpiredMessages(1000L);
		Assert.assertTrue(service.getAllMessages().size()==0);
	}
	
	@Test
	//this test creates 3 messages, waits for 1000ms 
	//creates  a new message and calls delete messages where older than 1000ms
	public void testDelete2expiredMessages() throws InterruptedException {
		Assert.assertTrue(service.getAllMessages().size()==3);
		Thread.sleep(1000L);
		entityManager.persist(msg4);
		service.deleteExpiredMessages(1000L);
		Assert.assertTrue(service.getAllMessages().size()==1);
	}

	@Test
	public void testSaveNewMessgae() {
		Assert.assertTrue(service.getAllMessages().size()==3);
		entityManager.persist(msg4);
		Assert.assertTrue(service.getAllMessages().size()==4);
	}

	@Test
	//this tests gets only the limited number of messages
	public void testGetAllMessagesInt() {
		Assert.assertTrue(service.getAllMessages().size()==3);
		Assert.assertTrue(service.getAllMessages(2).size()==2);
		Assert.assertTrue(service.getAllMessages(1).size()==1);
		Assert.assertTrue(service.getAllMessages(0).size()==0);
		Assert.assertTrue(service.getAllMessages(5).size()==3);
	}

	@Test
	//This test checks if the update has happen
	//with in 10 secs, if not returns false
	public void testUpdateMessage() throws InterruptedException {
		Message msgL = service.getMessage(msg3.getId());
		msgL.setMessage("Updated");
		Thread.sleep(5000);
		Assert.assertTrue(service.updateMessage(msgL));
		Message msgL1 = service.getMessage(msg3.getId());
		Assert.assertTrue(msgL1.getMessage().equals("Updated"));
	}
	
	@Test
	//This test checks if the update has happen
	//with in 10 secs, if not returns false
	public void testUpdateMessageFail() throws InterruptedException {
		Message msgL = service.getMessage(msg3.getId());
		msgL.setMessage("Updated");
		Thread.sleep(11000);
		Assert.assertFalse(service.updateMessage(msgL));
	}

}
