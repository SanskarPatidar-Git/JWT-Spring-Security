package com.tutorial.jwt.token;

public class LoginUserDto {
    private String email;
    
    private String password;
    
    public LoginUserDto() {
    	
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    // getters and setters here...
}
