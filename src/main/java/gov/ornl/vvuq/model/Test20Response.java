package gov.ornl.vvuq.model;

public class Test20Response {

    private String inputFile;
	private String numRows;
	private String [] keywordSets;
    private String queryTime;
    private String etlTime;

    public Test20Response() {

    }

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getNumRows() {
		return numRows;
	}

	public void setNumRows(String numRows) {
		this.numRows = numRows;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public String [] getKeywordSets() {
		return keywordSets;
	}

	public void setKeywordSets(String [] keywordSets) {
		this.keywordSets = keywordSets;
	}

	public String getEtlTime() {
		return etlTime;
	}

	public void setEtlTime(String etlTime) {
		this.etlTime = etlTime;
	}

    

}