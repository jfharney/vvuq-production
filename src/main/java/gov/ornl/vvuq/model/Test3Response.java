package gov.ornl.vvuq.model;

public class Test3Response {

    private String name;
    
    private String [] r_selected_list;
    private String [] r_unselected_list;
    
    private String r_selected;
    private String r_unselected;
    private String r_all;
    
    private boolean test3;

    public Test3Response() {

    }

	public String getR_selected() {
		return r_selected;
	}

	public void setR_selected(String r_selected) {
		this.r_selected = r_selected;
	}

	public String getR_unselected() {
		return r_unselected;
	}

	public void setR_unselected(String r_unselected) {
		this.r_unselected = r_unselected;
	}

	public String getR_all() {
		return r_all;
	}

	public void setR_all(String r_all) {
		this.r_all = r_all;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String [] getR_selected_list() {
		return r_selected_list;
	}

	public void setR_selected_list(String [] r_selected_list) {
		this.r_selected_list = r_selected_list;
	}

	public String [] getR_unselected_list() {
		return r_unselected_list;
	}

	public void setR_unselected_list(String [] r_unselected_list) {
		this.r_unselected_list = r_unselected_list;
	}

	public boolean isTest3() {
		return test3;
	}

	public void setTest3(boolean test3) {
		this.test3 = test3;
	}

   

}