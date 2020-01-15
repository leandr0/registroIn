package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;


public class SimpleObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6804870503202422654L;

	private String descricao;

	private int indexReport;
		
	public SimpleObject() {}
	
	public SimpleObject(final String descricao) {
		this.descricao = descricao;
	}

	public SimpleObject(final int indexReport,final String descricao) {
		this.indexReport 	= indexReport;
		this.descricao 		= descricao;
	}

	
	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getIndexReport() {
		return indexReport;
	}

	public void setIndexReport(int indexReport) {
		this.indexReport = indexReport;
	}
}
