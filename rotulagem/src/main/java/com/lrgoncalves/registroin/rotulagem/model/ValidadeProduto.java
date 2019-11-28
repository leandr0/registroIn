/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.model;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public class ValidadeProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1834050266138828372L;

	private String validadeFreezer;

	private String validadeCongelador;

	private String validadeRefrigerador;

	public String getValidadeFreezer() {
		return validadeFreezer;
	}

	public void setValidadeFreezer(String validadeFreezer) {
		this.validadeFreezer = validadeFreezer;
	}

	public String getValidadeCongelador() {
		return validadeCongelador;
	}

	public void setValidadeCongelador(String validadeCongelador) {
		this.validadeCongelador = validadeCongelador;
	}

	public String getValidadeRefrigerador() {
		return validadeRefrigerador;
	}

	public void setValidadeRefrigerador(String validadeRefrigerador) {
		this.validadeRefrigerador = validadeRefrigerador;
	}
}
