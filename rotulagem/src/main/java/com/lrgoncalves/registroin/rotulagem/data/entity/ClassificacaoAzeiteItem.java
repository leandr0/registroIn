/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public class ClassificacaoAzeiteItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4604761555656938850L;

	private int index;
	
	private String item;
	
	private String extraVirgem;
	
	private String virgem;

	public ClassificacaoAzeiteItem() {}
	
	
	public ClassificacaoAzeiteItem(final String item, final String extraVirgem, final String virgem) {
		this.item 			= item;
		this.extraVirgem 	= extraVirgem;
		this.virgem 		= virgem;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getExtraVirgem() {
		return extraVirgem;
	}

	public void setExtraVirgem(String extraVirgem) {
		this.extraVirgem = extraVirgem;
	}

	public String getVirgem() {
		return virgem;
	}

	public void setVirgem(String virgem) {
		this.virgem = virgem;
	}
}
