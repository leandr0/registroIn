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

import org.primefaces.event.UnselectEvent;

import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.StatusType;
import com.lrgoncalves.registroin.rotulagem.data.exception.PersistRotuloException;

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

			rotulos = rotuloDataAccess.searchByDescription(searchRotuloQuery);

			Thread.sleep(1000);

		} catch (Throwable e) {
			LOGGER.severe(e.getMessage());
		}
	}

	public void onRowSelect() {
		/**
		 * FacesMessage msg = new FacesMessage("Rotulo Selected", ((Rotulo)
		 * event.getObject()).getId());
		 * FacesContext.getCurrentInstance().addMessage(null, msg);
		 **/

		try {

			Rotulo transferObject = selectedRotulo;

			setSessionAttribute("fromPage", "home");

			setSessionAttribute("rotulo", transferObject);

			getExternalContext().redirect("report.jsf");
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}

	}

	public void sendByEmailChangingStatus() {

		try {

			if(selectedRotulo.getClient().getContactList() == null || selectedRotulo.getClient().getContactList().isEmpty()) {
				
				showMessageError("Não foi possível enviar o email. O Cliente não tem lista de contatos.");
				return;
			}
				
			switch (selectedRotulo.getStatus()) {
			case EM_ANALISE:
				selectedRotulo.setStatus(StatusType.ENVIADO);
				rotuloDataAccess.updateStatus(selectedRotulo.getId(), StatusType.ENVIADO);
				break;

			case REVISAO:
				selectedRotulo.setStatus(StatusType.RE_ENVIADO);
				rotuloDataAccess.updateStatus(selectedRotulo.getId(), StatusType.RE_ENVIADO);
				break;
			default:
				break;
			}
		
			for (Rotulo rtl : rotulos) {
			
				if(rtl.getId().equalsIgnoreCase(selectedRotulo.getId())) {
					rtl.setStatus(selectedRotulo.getStatus());
					break;
				}
			}
			
			Runnable runnable = () -> {
			    try {
			    	reportBean.sendReportByEmail(selectedRotulo);
			    }catch (Throwable e) {
			        LOGGER.severe(e.getMessage());
			    }
			};

			Thread thread = new Thread(runnable);
			thread.start();
			
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

	}
	
	public void updateStatus() {

		try {

			if(selectedRotulo.getStatus() != StatusType.REVISAO) {
				selectedRotulo.setStatus(StatusType.REVISAO);
				rotuloDataAccess.updateStatus(selectedRotulo.getId(), StatusType.REVISAO);
			}
			
			
			for (Rotulo rtl : rotulos) {
				
				if(rtl.getId().equalsIgnoreCase(selectedRotulo.getId())) {
					rtl.setStatus(selectedRotulo.getStatus());
					break;
				}
			}
			
			
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

	}
	
	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Rotulo Unselected", ((Rotulo) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	
	public String deleteRotulo() {
		
		
		try {
			rotuloDataAccess.delete(selectedRotulo.getId());
			
			for (Rotulo rtl : rotulos) {
				
				if(rtl.getId().equalsIgnoreCase(selectedRotulo.getId())) {
					rotulos.remove(rtl);
					break;
				}
			}
			
		} catch (PersistRotuloException e) {
			LOGGER.severe(e.getMessage());
		}
		
		return null;
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