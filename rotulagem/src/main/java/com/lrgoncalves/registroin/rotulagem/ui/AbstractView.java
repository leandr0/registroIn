package com.lrgoncalves.registroin.rotulagem.ui;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.lrgoncalves.registroin.rotulagem.data.RotuloDAO;

public abstract class AbstractView implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7240503216295752718L;

	@Inject
	protected RotuloDAO rotuloDataAccess;

	protected HttpSession session;
	
	protected String nomeUsuario;
	
	protected String chaveServico;

	public AbstractView() {
		createNewSession();
	}

	private void createNewSession(){
		session = (HttpSession) getExternalContext().getSession(true);
	}

	
	protected void setAttributeInSession(String attributeName, Object value){
		session.setAttribute(attributeName, value);
	}

	protected Object getAttributeInSession(String attributeName){
		return session.getAttribute(attributeName);
	}

	protected String getParameterInRequest(String name){
		return getExternalContext().getRequestParameterMap().get(name);
	}
	
	protected ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance()
		.getExternalContext();
	}

	protected Object getAttributeInContext(String attributeName){
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
				new FacesMessage(FacesMessage.SEVERITY_WARN,"Warn",message));
	}

	protected void showMessageInfo(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Info",message));
	}

	protected void showMessageError(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",message));
	}
	
	protected void showMessageFatal(String message){
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_FATAL,"Fatal",message));
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public void setChaveServico(String chaveServico) {
		this.chaveServico = chaveServico;
	}

	public RotuloDAO getRotuloDataAccess() {
		return rotuloDataAccess;
	}

	public void setRotuloDataAccess(RotuloDAO rotuloDataAccess) {
		this.rotuloDataAccess = rotuloDataAccess;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getChaveServico() {
		return chaveServico;
	}
}