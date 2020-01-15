/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public class ClassificacaoAzeite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7832263486231637332L;
	
	private String descricao;
	
	private ClassificacaoAzeiteItem acidezLivre;
	
	private ClassificacaoAzeiteItem indicesPeroxidos;
	
	private ClassificacaoAzeiteItem extEspecUltravioleta270;
	
	private ClassificacaoAzeiteItem extEspecUltravioleta232;
	
	private ClassificacaoAzeiteItem extEspecUltravioletaDelta;

	private int indexReport;
	
	public ClassificacaoAzeite() {
		acidezLivre 				= new ClassificacaoAzeiteItem();
		indicesPeroxidos 			= new ClassificacaoAzeiteItem();
		extEspecUltravioleta232 	= new ClassificacaoAzeiteItem();
		extEspecUltravioleta270 	= new ClassificacaoAzeiteItem();
		extEspecUltravioletaDelta 	= new ClassificacaoAzeiteItem();
	}
	
	public ClassificacaoAzeite(final String descricao) {
		this();
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ClassificacaoAzeiteItem getAcidezLivre() {
		return acidezLivre;
	}

	public void setAcidezLivre(ClassificacaoAzeiteItem acidezLivre) {
		this.acidezLivre = acidezLivre;
	}

	public ClassificacaoAzeiteItem getIndicesPeroxidos() {
		return indicesPeroxidos;
	}

	public void setIndicesPeroxidos(ClassificacaoAzeiteItem indicesPeroxidos) {
		this.indicesPeroxidos = indicesPeroxidos;
	}

	public ClassificacaoAzeiteItem getExtEspecUltravioleta270() {
		return extEspecUltravioleta270;
	}

	public void setExtEspecUltravioleta270(ClassificacaoAzeiteItem extEspecUltravioleta270) {
		this.extEspecUltravioleta270 = extEspecUltravioleta270;
	}

	public ClassificacaoAzeiteItem getExtEspecUltravioleta232() {
		return extEspecUltravioleta232;
	}

	public void setExtEspecUltravioleta232(ClassificacaoAzeiteItem extEspecUltravioleta232) {
		this.extEspecUltravioleta232 = extEspecUltravioleta232;
	}

	public ClassificacaoAzeiteItem getExtEspecUltravioletaDelta() {
		return extEspecUltravioletaDelta;
	}

	public void setExtEspecUltravioletaDelta(ClassificacaoAzeiteItem extEspecUltravioletaDelta) {
		this.extEspecUltravioletaDelta = extEspecUltravioletaDelta;
	}

	public int getIndexReport() {
		return indexReport;
	}

	public void setIndexReport(int indexReport) {
		this.indexReport = indexReport;
	}
}