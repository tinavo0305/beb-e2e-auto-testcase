package objectts;

public enum Sessions {
	TEST_SCREENSHOT_1("Test screenshot 1", "", ""),
	SESSION_PERMISSION("Session Permission", "", ""),
	TITLE_AND_DESCRIPTION("Edit title & description", "User is able to edit title and description of a session", "");
	
	private String title;
	private String description;
	private String status;
	
	Sessions(String title, String description, String status) {
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
