package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

public class SimpleObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7721429506072611539L;

	
	private String descricao;

	
	public SimpleObject() {}
	
	public SimpleObject(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
