/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.ui;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;

/**
 * @author digitallam
 *
 */
@Named(value = "initial_ui")
@SessionScoped
public class UIInitialBean extends UIAbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3036319342392818852L;


	private final Logger LOGGER = Logger.getLogger(UIInitialBean.class.getName());

	
	private String searchRotuloQuery;
	
	private List<Rotulo> rotulos;
	
	
	private Rotulo selectedRotulo;
	
	public String save() {

		return "rotulo";
	}

	public String report() {
		
		return "report";
	}

	@PostConstruct
	public void init() {
		rotulos = new LinkedList<Rotulo>();
		searchRotuloQuery = "";
	}
	
	public void searchRotulo() {
		try {
			
			if(!StringUtils.isAllBlank(searchRotuloQuery))
				rotulos = rotuloDataAccess.searchByDescription(searchRotuloQuery);
			
			Thread.sleep(2000);
			
		} catch (Throwable e) {
			LOGGER.severe(e.getMessage());
		}
	}

	public void onRowSelect(SelectEvent event) {
		
        FacesMessage msg = new FacesMessage("Rotulo Selected", ((Rotulo) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        try {
        	
        	Rotulo transferObject = (Rotulo) event.getObject();
        	
        	setSessionAttribute("fromPage", "home");
        	
        	setSessionAttribute("rotulo", transferObject);
        	
			getExternalContext().redirect("report.jsf");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
        
    }
	
	public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Rotulo Unselected", ((Rotulo) event.getObject()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public String getSearchRotuloQuery() {
		return searchRotuloQuery;
	}

	public void setSearchRotuloQuery(String searchRotuloQuery) {
		this.searchRotuloQuery = searchRotuloQuery;
	}

	public List<Rotulo> getRotulos() {
		return rotulos;
	}

	public void setRotulos(List<Rotulo> rotulos) {
		this.rotulos = rotulos;
	}

	public Rotulo getSelectedRotulo() {
		return selectedRotulo;
	}

	public void setSelectedRotulo(Rotulo selectedRotulo) {
		this.selectedRotulo = selectedRotulo;
	}
	
	
}