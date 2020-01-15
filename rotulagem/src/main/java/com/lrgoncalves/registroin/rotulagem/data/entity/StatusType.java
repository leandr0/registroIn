/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public enum StatusType implements Serializable {

	EM_ANALISE("Em análise"),
	ENVIADO("Enviado"),
	REVISAO("Revisão"),
	RE_ENVIADO("Re-enviado");
	
	private String value;
	
	private StatusType(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
