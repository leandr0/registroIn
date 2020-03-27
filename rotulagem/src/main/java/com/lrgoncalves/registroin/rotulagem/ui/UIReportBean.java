/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.ui;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.resource.spi.IllegalStateException;

import org.apache.commons.lang3.StringUtils;

import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.StatusType;
import com.lrgoncalves.registroin.rotulagem.data.exception.PersistRotuloException;

/**
 * @author digitallam
 *
 */
@Named(value = "report_ui")
@RequestScoped
public class UIReportBean extends UIAbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3318380076303481841L;

	private static final Logger LOGGER = Logger.getLogger(UIReportBean.class.getName());

	private Rotulo rotulo;

	private String fromPage;

	private boolean home;

	private SimpleView httpRequest = new SimpleView();

	public void onLoad() {

		try {

			if (!StringUtils.isBlank(getHttpRequest().getValue())) {
				rotulo = rotuloDataAccess.find(getHttpRequest().getValue());
				getHttpRequest().setCheck(true);
			}
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}
	}

	@PostConstruct
	public void init() {

		rotulo = (Rotulo) getSessionAttribute("rotulo");
		fromPage = (String) getSessionAttribute("fromPage");

		if (StringUtils.equalsIgnoreCase(fromPage, "home")) {
			home = true;
		} else {
			home = false;
		}
	}

	public Rotulo getRotulo() {
		return rotulo;
	}

	public void setRotulo(Rotulo rotulo) {
		this.rotulo = rotulo;
	}

	public String save() {

		try {

			if (rotulo.getDenominacaoProduto().getIndexReport() < 1) {
				rotulo.setDenominacaoProduto(null);
			}
			if (rotulo.getAromatizante().getIndexReport() < 1) {
				rotulo.setAromatizante(null);
			}
			if (rotulo.getPesoLiquido().getIndexReport() < 1) {
				rotulo.setPesoLiquido(null);
			}
			if (rotulo.getGluten().getIndexReport() < 1) {
				rotulo.setGluten(null);
			}
			if (rotulo.getIndustriaOrigem().getIndexReport() < 1) {
				rotulo.setIndustriaOrigem(null);
			}
			if (rotulo.getIngredientes().getIndexReport() < 1) {
				rotulo.setIngredientes(null);
			}
			if (rotulo.getDerivadosLacteos().getIndexReport() < 1) {
				rotulo.setDerivadosLacteos(null);
			}
			if (rotulo.getAlergicos().getIndexReport() < 1) {
				rotulo.setAlergicos(null);
			}
			if (rotulo.getProdutor().getIndexReport() < 1) {
				rotulo.setProdutor(null);
			}
			if (rotulo.getDistribuidor().getIndexReport() < 1) {
				rotulo.setDistribuidor(null);
			}
			if (rotulo.getImportador().getIndexReport() < 1) {
				rotulo.setImportador(null);
			}
			if (rotulo.getDataFabricacao().getIndexReport() < 1) {
				rotulo.setDataFabricacao(null);
			}

			if (rotulo.getPrazoValidade().getIndexReport() < 1) {
				rotulo.setPrazoValidade(null);
			}

			if (rotulo.getLote().getIndexReport() < 1) {
				rotulo.setLote(null);
			}

			if (rotulo.getGlutenAlergenos().getIndexReport() < 1) {
				rotulo.setGlutenAlergenos(null);
			}

			if (rotulo.getOutros().isEmpty()) {
				rotulo.setOutros(null);
			}

			if (rotulo.getInformacaoNutricional().getIndexReport() < 1) {
				rotulo.setInformacaoNutricional(null);
			}

			if (rotulo.getTartrazina().getIndexReport() < 1) {
				rotulo.setTartrazina(null);
			}

			if (rotulo.getAspartameFenilalanina().getIndexReport() < 1) {
				rotulo.setAspartameFenilalanina(null);
			}

			if (rotulo.getSac().getIndexReport() < 1) {
				rotulo.setSac(null);
			}

			if (rotulo.getTransgenico().getIndexReport() < 1) {
				rotulo.setTransgenico(null);
			}

			if (rotulo.getRegistroMAPA().getIndexReport() < 1) {
				rotulo.setRegistroMAPA(null);
			}

			if (rotulo.getConservacaoProduto().getIndexReport() < 1) {
				rotulo.setConservacaoProduto(null);
			}

			if (rotulo.getAzeite().getIndexReport() < 1) {
				rotulo.setAzeite(null);
			} else {

				if (rotulo.getAzeite().getClassificacao().getIndexReport() < 1) {
					rotulo.getAzeite().setClassificacao(null);
				}
				if (rotulo.getAzeite().getDenominacao().getIndexReport() < 1) {
					rotulo.getAzeite().setDenominacao(null);
				}

			}

			rotuloDataAccess.persistRotulo(rotulo);

			return "home";

		}catch (IllegalStateException s) {
			showMessageWarn(s.getMessage());
			LOGGER.severe(s.getMessage()); 
		} 
		catch (Exception e) {
			
			LOGGER.severe(e.getMessage());
		}
		return null;

	}

	public String sendMail() {

		try {

			StatusType updateStatus = null;
			
			if(rotulo.getStatus() == StatusType.EM_ANALISE)
				updateStatus = StatusType.ENVIADO;
			
			else
				updateStatus = StatusType.RE_ENVIADO;
			
			rotulo.setStatus(updateStatus);
			
			Runnable runnable = () -> {
			    try {
			    	reportBean.sendReportByEmail(rotulo);
			    }catch (Throwable e) {
			        LOGGER.severe(e.getMessage());
			    }
			};

			Thread thread = new Thread(runnable);
			thread.start();	
			
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

		return "home";
	}

	public String deleteRotulo() {
		
		
		try {
			rotuloDataAccess.delete(rotulo.getId());		
		} catch (PersistRotuloException e) {
			LOGGER.severe(e.getMessage());
		}
		
		return "home";
	}
	
	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public boolean isHome() {
		return home;
	}

	public void setHome(boolean home) {
		this.home = home;
	}

	public SimpleView getHttpRequest() {
		return httpRequest;
	}

	public void setHttpRequest(SimpleView httpRequest) {
		this.httpRequest = httpRequest;
	}
}