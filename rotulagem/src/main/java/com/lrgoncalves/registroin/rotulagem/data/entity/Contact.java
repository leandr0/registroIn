/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4751865110171654346L;

	private String name;
	
	private String email;

	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	public Contact(final String name, final String email) {
		this.name 	= name;
		this.email 	= email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
