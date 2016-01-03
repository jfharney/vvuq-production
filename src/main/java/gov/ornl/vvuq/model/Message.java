package gov.ornl.vvuq.model;

import java.io.Serializable;

public class Message implements Serializable {

	private String type;
	
	public Message() {
		this.setType("");
	}
	
	public Message(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
