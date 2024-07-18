package com.tutorial.jwt.token.dto;

public class LoginResponse {

	private String token;
    private long expiresIn;

    public LoginResponse setToken(String token) {
    	this.token = token;
    	return this;
    }
    
    public String getToken() {
        return token;
    }
    
    public long getExpiresIn() {
		return expiresIn;
	}

	public LoginResponse setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
		return this;
	}
}
