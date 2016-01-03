package gov.ornl.vvuq.model;


import gov.ornl.vvuq.nmf.Driver;

import java.util.concurrent.CountDownLatch;

public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);
	
	public void receiveMessage(Message message) throws InterruptedException {
		

		Thread.sleep(5000);
		if(message.getType().equals("All")) {
			System.out.println("Do everything");
			Thread.sleep(5000);
		} else {
			System.out.println("Received <" + message.getType() + ">");
			System.out.println("----Test nmf----\n\n");
			//Driver.testNmf();
			System.out.println("\n\n----End Test nmf----");
			Thread.sleep(5000);
		}
		
		System.out.println("At this point, the email should be sent out");
		latch.countDown();
	}
	
	public void receiveMessage(String message) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("Received <" + message + ">");
		latch.countDown();
		//Thread.sleep(5000);
		//System.out.println("Still in received message");
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
}
