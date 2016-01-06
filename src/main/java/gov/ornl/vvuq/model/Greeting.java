package gov.ornl.vvuq.model;



public class Greeting {

    private String id;

    private String text;
    private String dir;
    private String [] names;
    private int [] ranks;

    public Greeting() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}