package gov.ornl.vvuq.model;

import java.io.Serializable;

public class Message implements Serializable {

	private String type;
	private String [] names;// = {"nimfa_nmf","nimfa_icm"};//,"nimfa_icm","nimfa_lsnmf"};
	private int [] ranks;// = {1,2,3,4,5};
	
	
	public Message() {
		this.setType("");
	}
	
	public Message(String type) {
		this.setType(type);
	}
	
	public Message(String type,String [] names,int [] ranks) {
		this.setType(type);
		this.setNames(names);
		this.setRanks(ranks);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String [] getNames() {
		return names;
	}

	public void setNames(String [] names) {
		this.names = names;
	}

	public int [] getRanks() {
		return ranks;
	}

	public void setRanks(int [] ranks) {
		this.ranks = ranks;
	}
	
	
	
}
