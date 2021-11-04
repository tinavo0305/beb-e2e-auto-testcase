package objectts;

public enum Accounts {
	FREE_ADMIN("birdeatsbugtester1", "birdeatsbugtester+1@gmail.com" , "birdeatsbug123", "Free Test WS", "Birdeatsbugtester 1"),
	FREE_REPORTER("","" , "", "", ""),
	PAID_ADMIN("birdeatsbugtester", "birdeatsbugtester@gmail.com", "birdeatsbug123", "My Workspace", "Paid Account"),
	PAID_MEMBER("birdeatsbugtester2","birdeatsbugtester+2@gmail.com" , "birdeatsbug123", "Full member", "Birdeatsbugtester 2"),
	PAID_REPORTER("", "", "", "", "");
	
	private String username;
	private String email;
	private String password;
	private String workspaceName;
	private String displayName;
	
	Accounts(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	Accounts(String username, String email, String password, String workspace, String displayName) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.workspaceName = workspace;
		this.displayName = displayName;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getworkspaceName() {
		return this.workspaceName;
	}
	
	public String getDisplayName() {
		return this .displayName;
	}
	
	
	

}
