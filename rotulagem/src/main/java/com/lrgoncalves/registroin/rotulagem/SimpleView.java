/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.io.Serializable;

/**
 * @author digitallam
 *
 */
public class SimpleView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4495790378995152630L;

	
	private boolean check;
	
	private int index;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
