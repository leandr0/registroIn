package com.lrgoncalves.registroin.rotulagem.model;

import java.io.Serializable;

public class Ingrediente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8071446032701673140L;

	
	private int position;
	
	private String description;


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}
}