/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class BasicView implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 7105155186361060496L;
	private String text;
 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
