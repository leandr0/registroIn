package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConservacaoProduto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9177992958642932156L;
	private Map<String, String> validadeProduto;

	public ConservacaoProduto() {
		validadeProduto = new LinkedHashMap<String, String>();
	}
	
	public Map<String, String> getValidadeProduto() {
		return validadeProduto;
	}

	public void setValidadeProduto(Map<String, String> validadeProduto) {
		this.validadeProduto = validadeProduto;
	}
	
}
