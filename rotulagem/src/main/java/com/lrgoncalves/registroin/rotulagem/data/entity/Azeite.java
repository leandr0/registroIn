/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public class Azeite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1771255288120066153L;

	private SimpleObject denominacao;
	
	private ClassificacaoAzeite classificacao;

	private int indexReport;
	
	public Azeite() {
		classificacao = new ClassificacaoAzeite();
	}
	
	
	public SimpleObject getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(SimpleObject denominacao) {
		this.denominacao = denominacao;
	}

	public int getIndexReport() {
		return indexReport;
	}

	public void setIndexReport(int indexReport) {
		this.indexReport = indexReport;
	}

	public ClassificacaoAzeite getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoAzeite classificacao) {
		this.classificacao = classificacao;
	}
}