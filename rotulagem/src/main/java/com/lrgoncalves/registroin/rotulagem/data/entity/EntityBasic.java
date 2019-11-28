/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

/**
 * @author lrgoncalves
 *
 */
public interface EntityBasic extends Serializable {
	
	/**
	 * 
	 * @param id
	 */
	public void setId(Long id);
	
	/**
	 * 
	 * @return
	 */
	public Long getId();

}