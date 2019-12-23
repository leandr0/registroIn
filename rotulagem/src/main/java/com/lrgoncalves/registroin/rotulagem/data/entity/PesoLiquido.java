package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

public class PesoLiquido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5479571587607275834L;

	private String descricao;
	
	private String peso;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
}
