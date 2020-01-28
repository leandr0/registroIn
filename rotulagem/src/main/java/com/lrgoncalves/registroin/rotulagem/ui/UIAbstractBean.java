package com.lrgoncalves.registroin.rotulagem.ui;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.lrgoncalves.registroin.rotulagem.ManagerReportBean;
import com.lrgoncalves.registroin.rotulagem.data.ManagerClientBean;
import com.lrgoncalves.registroin.rotulagem.data.ManagerRotuloBean;

public abstract class UIAbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8504937041613479990L;

	@Inject
	protected ManagerRotuloBean rotuloDataAccess;

	@Inject
	protected ManagerClientBean clientDataAccess;
	
	@Inject
	ManagerReportBean reportBean;
	
	protected HttpSession session;
	
	protected String nomeUsuario;
	
	protected String chaveServico;

	public UIAbstractBean() {
		createNewSession();
	}

	private void createNewSession(){
		session = (HttpSession) getExternalContext().getSession(true);
	}

	protected void setSessionAttribute(String attributeName, Object value){
		session.setAttribute(attributeName, value);
	}

	protected Object getSessionAttribute(String attributeName){
		return session.getAttribute(attributeName);
	}

	protected String getRequestParameter(String name){
		return getExternalContext().getRequestParameterMap().get(name);
	}
	
	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	protected Object getContextAttribute(String attributeName){
		return getExternalContext().getRequestMap().get(attributeName);
	}

	public HttpSession getSession() {

		if(session == null)
			createNewSession();

		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	protected void showMessageWarn(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN,"",message));
	}

	protected void showMessageInfo(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,"",message));
	}

	protected void showMessageError(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"",message));
	}
	
	protected void showMessageFatal(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_FATAL,"",message));
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void setChaveServico(String chaveServico) {
		this.chaveServico = chaveServico;
	}

	public ManagerRotuloBean getRotuloDataAccess() {
		return rotuloDataAccess;
	}

	public void setRotuloDataAccess(ManagerRotuloBean rotuloDataAccess) {
		this.rotuloDataAccess = rotuloDataAccess;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getChaveServico() {
		return chaveServico;
	}
}