package com.tutorial.jwt.token;

public class LoginResponse {

	private String token;

    long expiresIn;

    public LoginResponse setToken(String token) {
    	this.token = token;
    	return this;
    }
    
    public long getExpiresIn() {
		return expiresIn;
	}

	public LoginResponse setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}

	public String getToken() {
        return token;
    }
}
