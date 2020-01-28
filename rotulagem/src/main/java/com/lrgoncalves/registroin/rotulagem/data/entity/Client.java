package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;
import java.util.List;

public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7046963146664746014L;

	private String nome;

	private String id;
	
	private String identificationNumber;
	
	private List<Contact> contactList;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificatioNumber) {
		this.identificationNumber = identificatioNumber;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}
	
}
