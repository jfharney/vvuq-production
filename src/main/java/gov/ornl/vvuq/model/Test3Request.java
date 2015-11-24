package gov.ornl.vvuq.model;

public class Test3Request {

	private String name;
	private String [] selected_keywords;
	private String [] all_keywords;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String [] getAll_keywords() {
		return all_keywords;
	}
	public void setAll_keywords(String [] all_keywords) {
		this.all_keywords = all_keywords;
	}
	public String [] getSelected_keywords() {
		return selected_keywords;
	}
	public void setSelected_keywords(String [] selected_keywords) {
		this.selected_keywords = selected_keywords;
	}
	
	
	
}
