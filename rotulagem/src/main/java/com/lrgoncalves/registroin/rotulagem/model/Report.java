/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author digitallam
 *
 */
public class Report implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2706967713626349826L;
	
	
	private Map<Integer,Object> items;
	
	
	public Report() {
		items = new LinkedHashMap<Integer, Object>();
	}

	
	public Report setItem(Integer key ,Object value) {
		
		this.items.put(key, value);
		
		return this;
	}

	public Map<Integer, Object> getItems() {
		return items;
	}


	public void setItems(Map<Integer, Object> items) {
		this.items = items;
	}
	
	
	

}
