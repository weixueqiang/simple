package com.jo.dy.ot.entity;

import java.io.Serializable;

public class User implements Serializable{
    private Integer id;

    private String username;

    private String password;

    private String salt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {
    	
    }
    public User(Integer id, String username, String password, String salt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + "]";
	}
    
    
}