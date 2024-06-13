package model;

public class LoginInfo  {

	private String username;
	private String password;

	public LoginInfo() {
		this.username = "";
		this.password = "";
	}
	
	public LoginInfo(String username, String password) throws IllegalArgumentException {
		try {
		this.username = username;
		this.password = password;
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid Parameters");
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	@Override
	public String toString() {
		return "LoginInfo [username=" + username + ", password=" + password + "]";
	}

	
	
}
