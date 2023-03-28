package com.sangmin.portfolio.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	
	private final String name;
	private final String email;
	private final String picture;
	
	public SessionUser(Users user) {
		this.name =user.getName();
		this.email =user.getEmail();
		this.picture =user.getProvider();
	}

}
