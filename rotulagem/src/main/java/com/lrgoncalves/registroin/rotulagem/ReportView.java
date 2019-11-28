/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.lrgoncalves.registroin.rotulagem.model.Report;

/**
 * @author digitallam
 *
 */
@Named(value ="report_ui")
@SessionScoped
public class ReportView extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6571355873770715074L;
	
	
	private Report report;
	
	
	private String peso;
	
	@PostConstruct
    public void init() {
		
		
		peso = (String) getAttributeInSession("test");
		report = (Report) getAttributeInSession("report");
		
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
}
