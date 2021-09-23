package model;

public class AuthModel{
	private String password;
	private String username;

	public AuthModel(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}
