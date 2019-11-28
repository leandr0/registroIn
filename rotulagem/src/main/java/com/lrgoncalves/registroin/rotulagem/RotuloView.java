/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.lrgoncalves.registroin.rotulagem.model.Report;

/**
 * @author digitallam
 *
 */
@Named(value ="rotulo_ui")
@SessionScoped
public class RotuloView extends AbstractBean{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8080469063655272782L;

	private String empresa;
	
	private String produto;
	
	private String data;
	
	private String aromatizante;
	
	private List<String> aromatizantes;
	
	private String denominacaoDoProduto;
	
	private boolean showDenominacaoDoProduto;
	
	private int indexDenominacaoDoProduto;
	
	private boolean showAromatizante;
	
	private int indexAromatizante;
	
	private int pesoLiquido;
	
	private String fontePesoLiquido;
	
	private boolean showPesoLiquido;
	
	private int indexPesoLiquido;
	
	private boolean showGluten;
	
	private int indexGluten;
	
	private String gluten;
	
	private List<String> glutens;
	
	private boolean showIndustriaDeOrigem;
	
	private int indexIndustriaDeOrigem;
	
	private String industriaDeOrigem;
	
	private boolean showIngrediente;
	
	private int indexIngrediente; 
	
	private String ingrediente;
	
	private boolean showLactose;
	
	private int indexLactose;
	
	private String lactose;
	
	private String alergicos;
	
	private String produzido;
	
	private String distribuido;
	
	private boolean showValidadeProduto;
	
	private int indexValidadeProduto;
	
	private String validadeFreezer;
	
	private String validadeCongelador;
	
	private String validadeRefrigerador;
	
	private boolean showAlergico;
	
	private int indexAlergico;
	
	private String descricaoAlergico;
	
	private boolean showGlutenAlergenos;
	
	private int indexGlutenAlergenos;
	
	private String descricaoGlutenAlergenos;
	
	private boolean showUsoProduto;
	
	private int indexUsoProduto;
	
	private String descricaoUsoProduto;
	
	private boolean showProduzido;
	
	private int indexProduzido; 
	
	private boolean showDistribuido;
	
	private int indexDistribuido;
	
	private boolean showImportado;
	
	private int indexImportado;
	
	private String descricaoProduzido;
	
	private String descricaoDistribuido;
	
	private String descricaoImportado;
	
	private boolean showDataFabricacao;
	
	private int indexDataFabricacao;
	
	private boolean showPrazoValidade;
	
	private int indexPrazoValidade;
	
	private String dataFabricacao;
	
	private String prazoValidade;
	
	private boolean showLote;
	
	private int indexLote;
	
	private String lote;
	
	private boolean showInformacaoNutricional;
	
	private int indexInformacaoNutricional;
	
	private String informacaoNutricional;
	
	private boolean showTartrazina;
	
	private int indexTartrazina;
	
	private String tartrazina;
	
	private boolean showFenilalanina;
	
	private int indexFenilalanina;
	
	private String fenilalanina;
	
	private boolean showSac;
	
	private int indexSac;
	
	private String sac;
	
	private boolean showTransgenico;
	
	private int indexTransgenico;
	
	private String transgenico;
	
	private boolean showRegistro;
	
	private int indexRegistro;
	
	private String registro;
	
	private String codidoRegistro;
	
	private String email;
	
	private String emailPattern = "/^(([^<>()\\[\\]\\.,;:\\s@\\\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@(([^<>()[\\]\\.,;:\\s@\\\"]+\\.)+[^<>()[\\]\\.,;:\\s@\\\"]{2,})$/i";
	
	private CalculoNutricional calculoNutricional;
	
	private boolean selectAll;
	
	@PostConstruct
    public void init() {
        
		aromatizantes = new ArrayList<String>();
		aromatizantes.add("Inserir o termo \"CONTÉM AROMATIZANTE\" no painel principal. Em caixa alta.");
		aromatizantes.add("Inserir o termo \"AROMATIZADO ARTIFICIALMENTE\" no painel principal. Em caixa alta e negrito.");
		aromatizantes.add("Inserir o termo \"Contém aromatizante sintético idêntico ao natural\" no painel principal. Em negrito.");
   
		denominacaoDoProduto = "Sugerimos adequar a denominação do produto para ....";
		industriaDeOrigem = "Acrescentar os termos : - \"Indústria <País de Origem>\"";
		
		glutens = new ArrayList<String>();
		glutens.add("Inserir a inscrição \"NÃO CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito.");
		glutens.add("Inserir a inscrição \"CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito.");
		
		lactose = "Inserir a inscrição \"CONTÉM LACTOSE\" logo após a inscrição \"NÃO CONTÉM GLÚTEN\". Em negrito e caixa alta.";
		
		alergicos = "Inserir a inscrição \"ALÉRGICOS: CONTÉM XXXXX E DERIVADOS DE XXXXX\", em caixa alta e negrito, logo após a inscrição \"NÃO CONTÉM GLÚTEN\"";
		
		produzido = "Inserir informações completas do produtor (nome e endereço)";
		
		distribuido = "";
		
		final String validade ="DD/MM/AA";
		
		validadeCongelador 		= validade;
		validadeFreezer 		= validade;
		validadeRefrigerador 	= validade;
		
		descricaoAlergico = "Inserir a inscrição \"ALÉRGICOS: CONTÉM XXXXX E DERIVADOS DE XXXXX\", em caixa alta e negrito, logo após a inscrição \"NÃO CONTÉM GLÚTEN\"";
		
		descricaoGlutenAlergenos = "Todos os caracteres devem apresentar altura mínima de 1mm, com exceção da informação de GLÚTEN e ALERGENOS, que devem ter altura mínima de 2mm, e do peso líquido que deve ter a altura mínima indicada anteriormente. ";
		
		
		prazoValidade = "Inserir Data de Validade no formato DD/MM/AA";
		dataFabricacao = "Inserir Data de Fabricação no formato DD/MM/AA";
		
		lote = "Inserir número do Lote.";
		
		informacaoNutricional = "A Informação Nutricional deve seguir EXATAMENTE o modelo permitido pela ANVISA, Com Trans em itálico, e seus valores devem ser ajustados para:";
		
		tartrazina = "A lista de ingredientes deve apresentar o nome do corante tartrazina INS 102 por extenso, em caixa alta e negrito.";
		
		fenilalanina = "Inserir a informação: \"CONTÉM FENILALANINA\", em caixa alta e negrito";
		
		sac = "Inserir informação para SAC.";
		
		transgenico = "Se presente em quantidade superior a 1%, inserir o logo de TRANSGENICO no rótulo.";
		
		registro = "Inserir a informação: \"Registro no Ministério da Agricultura sob nº XXXX/XXXXX.\"";
		
		ingrediente = "Corrigir os ingredientes para .....";
		
		calculoNutricional = new CalculoNutricional();
		
		String dataPattern = "dd/MM/yyyy";
		
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern(dataPattern);
		
		data = dTF.format(LocalDateTime.now());
    }
	
	
	public void calcularPorcao() {
		
		calculoNutricional.calcularPorcao(calculoNutricional);
		
		calculoNutricional.dadosPorcao.setValoEnergeticoKj(calculoNutricional.calculoValoEnergeticoKj(calculoNutricional.dadosPorcao));
		calculoNutricional.dadosPorcao.setValorEnergeticoKcal(calculoNutricional.calculoValoEnergeticoKcal(calculoNutricional.dadosPorcao));
		
		System.out.println(calculoNutricional.getDadosPorcao().getValorEnergeticoDescritivo());
		
		
		calculoNutricional.calculopercentualDiario(calculoNutricional.dadosPorcao, calculoNutricional.dadosPorcaoVD);
		
		
		//calculoNutricional.dadosPorcao.getValorEnergeticoDescritivo()
	}

	public void calculoPesoLiquido() {
		
		if(pesoLiquido <= 50) {
			setFontePesoLiquido("Os números devem ter altura mínima de 2 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");
		}else if (pesoLiquido > 50 && pesoLiquido <= 200) {
			setFontePesoLiquido("Os números devem ter altura mínima de 3 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");
		}else if (pesoLiquido > 200 && pesoLiquido <= 1000) {
			setFontePesoLiquido("Os números devem ter altura mínima de 4 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");
		}else if(pesoLiquido > 1000) {
			setFontePesoLiquido("Os números devem ter altura mínima de 6 mm, e a unidade \"kg\", altura mínima de 2/3 o tamanho do numeral.");
		}
	}
	
	
	public String report() {		
		
		Report report = new Report();
		
		int index = 1;
		
		if(showDenominacaoDoProduto) {
			report.setItem(index, getDenominacaoDoProduto());
			setIndexDenominacaoDoProduto(index);
			index++;
		}
		if(showAromatizante) {
			report.setItem(index, getAromatizante());
			setIndexAromatizante(index);
			index++;
		}
		
		if(showPesoLiquido) {
			report.setItem(index, getFontePesoLiquido());
			setIndexPesoLiquido(index);
			index++;
		}
		
		if(showGluten) {
			report.setItem(index, getGluten());
			setIndexGluten(index);
			index++;
		}
		
		if(showIndustriaDeOrigem) {
			report.setItem(index, getIndustriaDeOrigem());
			setIndexIndustriaDeOrigem(index);
			index++;
		}
		
		if(showIngrediente){
			report.setItem(index, getIngrediente());
			setIndexIngrediente(index);
			index++;
		}
		
		
		if(showLactose) {
			report.setItem(index, getLactose());
			setIndexLactose(index);
			index++;
		}
		
		if(showAlergico) {
			report.setItem(index, getAlergicos());
			setIndexAlergico(index);
			index++;
		}
		
		
		if(showProduzido) {
			report.setItem(index, getDescricaoProduzido());
			setIndexProduzido(index);
			index++;
		}
		
		if(showDistribuido) {
			report.setItem(index, getDescricaoDistribuido());
			setIndexDistribuido(index);
			index++;
		}
		
		if(showImportado) {
			report.setItem(index, getDescricaoImportado());
			setIndexImportado(index);
			index++;
		}
		
		if(showDataFabricacao) {
			report.setItem(index, getDataFabricacao());
			setIndexDataFabricacao(index);
			index++;
		}
		
		if(showPrazoValidade) {
			report.setItem(index, getPrazoValidade());
			setIndexPrazoValidade(index);
			index++;
		}
		
		if(showLote) {
			report.setItem(index, getLote());
			setIndexLote(index);
			index++;
		}
		
		if(showGlutenAlergenos) {
			report.setItem(index, getDescricaoGlutenAlergenos());
			setIndexGlutenAlergenos(index);
			index++;
		}
		
		if(showUsoProduto) {
			report.setItem(index, getDescricaoUsoProduto());
			setIndexUsoProduto(index);
			index++;
		}
		
		if(showValidadeProduto) {
			report.setItem(index, getValidadeCongelador());
			setIndexValidadeProduto(index);
			index++;
		}
		
		if(showInformacaoNutricional) {
			report.setItem(index, getInformacaoNutricional());
			setIndexInformacaoNutricional(index);
			index++;
		}
		
		if(showTartrazina) {
			report.setItem(index, getTartrazina());
			setIndexTartrazina(index);
			index++;
		}
		
		if(showFenilalanina) {
			report.setItem(index, getFenilalanina());
			setIndexFenilalanina(index);
			index++;
		}
		
		if(showSac) {
			report.setItem(index, getSac());
			setIndexSac(index);
			index++;
		}
		
		if(showTransgenico) {
			report.setItem(index, getTransgenico());
			setIndexTransgenico(index);
			index++;
		}
		
		if(showRegistro) {
			report.setItem(index, getRegistro());
			setIndexRegistro(index);
			index++;
		}
		
		setAttributeInSession("test", getPesoLiquido()+"");
		
		setAttributeInSession("report", report);
		
		return "report";
	}
	
	public void checkBoxHandle() {
		
		if(isSelectAll()) {
			
			handleBooleanValues(true);
			
		}else {
			handleBooleanValues(false);
			
		}
		
	}
	
	public void clearData() {
		setAlergicos(null);
		setAromatizante(null);
		setAromatizantes(null);
		setCalculoNutricional(null);
		setChaveServico(null);
		setCodidoRegistro(null);
		setData(null);
		setDataFabricacao(null);
		setDenominacaoDoProduto(null);
		setDescricaoAlergico(null);
		setDescricaoDistribuido(null);
		setDescricaoGlutenAlergenos(null);
		setDescricaoImportado(null);
		setDescricaoProduzido(null);
		setDescricaoUsoProduto(null);
		setDistribuido(null);
		setEmail(null);
		setEmpresa(null);
		setFenilalanina(null);
		setFontePesoLiquido(null);
		setGluten(null);
		setGlutens(null);
		setIndustriaDeOrigem(null);
		setInformacaoNutricional(null);
		setIngrediente(null);
		setLactose(null);
		setLote(null);
		setNomeUsuario(null);
		setPesoLiquido(0);
		setPrazoValidade(null);
		setProduto(null);
		setProduzido(null);
		setRegistro(null);
		setSac(null);
		init();
		
	}
	
	private void handleBooleanValues(final boolean value) {
		setShowAlergico(value);
		setShowAromatizante(value);
		setShowDataFabricacao(value);
		setShowDenominacaoDoProduto(value);
		setShowDistribuido(value);
		setShowFenilalanina(value);
		setShowGluten(value);
		setShowGlutenAlergenos(value);
		setShowImportado(value);
		setShowIndustriaDeOrigem(value);
		setShowInformacaoNutricional(value);
		setShowIngrediente(value);
		setShowLactose(value);
		setShowLote(value);
		setShowPesoLiquido(value);
		setShowPrazoValidade(value);
		setShowProduzido(value);
		setShowRegistro(value);
		setShowSac(value);
		setShowTartrazina(value);
		setShowTransgenico(value);
		setShowUsoProduto(value);
		setShowValidadeProduto(value);
	}
	
	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public String getProduto() {
		return produto;
	}


	public void setProduto(String produto) {
		this.produto = produto;
	}


	public String getAromatizante() {
		return aromatizante;
	}


	public void setAromatizante(String aromatizante) {
		this.aromatizante = aromatizante;
	}


	public List<String> getAromatizantes() {
		return aromatizantes;
	}


	public void setAromatizantes(List<String> aromatizantes) {
		this.aromatizantes = aromatizantes;
	}


	public String getDenominacaoDoProduto() {
		return denominacaoDoProduto;
	}


	public void setDenominacaoDoProduto(String denominacaoDoProduto) {
		this.denominacaoDoProduto = denominacaoDoProduto;
	}


	public boolean isShowDenominacaoDoProduto() {
		return showDenominacaoDoProduto;
	}


	public void setShowDenominacaoDoProduto(boolean showDenominacaoDoProduto) {
		this.showDenominacaoDoProduto = showDenominacaoDoProduto;
	}


	public boolean isShowAromatizante() {
		return showAromatizante;
	}


	public void setShowAromatizante(boolean showAromatizante) {
		this.showAromatizante = showAromatizante;
	}



	public int getPesoLiquido() {
		return pesoLiquido;
	}



	public void setPesoLiquido(int pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}



	public String getFontePesoLiquido() {
		return fontePesoLiquido;
	}



	public void setFontePesoLiquido(String fontePesoLiquido) {
		this.fontePesoLiquido = fontePesoLiquido;
	}

	public boolean isShowPesoLiquido() {
		return showPesoLiquido;
	}

	public void setShowPesoLiquido(boolean showPesoLiquido) {
		this.showPesoLiquido = showPesoLiquido;
	}

	public boolean isShowGluten() {
		return showGluten;
	}

	public void setShowGluten(boolean showGluten) {
		this.showGluten = showGluten;
	}

	public String getGluten() {
		return gluten;
	}

	public void setGluten(String gluten) {
		this.gluten = gluten;
	}

	public List<String> getGlutens() {
		return glutens;
	}

	public void setGlutens(List<String> glutens) {
		this.glutens = glutens;
	}

	public String getIndustriaDeOrigem() {
		return industriaDeOrigem;
	}

	public void setIndustriaDeOrigem(String industriaDeOrigem) {
		this.industriaDeOrigem = industriaDeOrigem;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public boolean isShowLactose() {
		return showLactose;
	}

	public void setShowLactose(boolean showLactose) {
		this.showLactose = showLactose;
	}

	

	public String getLactose() {
		return lactose;
	}

	public void setLactose(String lactose) {
		this.lactose = lactose;
	}

	public String getAlergicos() {
		return alergicos;
	}

	public void setAlergicos(String alergicos) {
		this.alergicos = alergicos;
	}

	public String getProduzido() {
		return produzido;
	}

	public void setProduzido(String produzido) {
		this.produzido = produzido;
	}

	public String getDistribuido() {
		return distribuido;
	}

	public void setDistribuido(String distribuido) {
		this.distribuido = distribuido;
	}

	public boolean isShowIndustriaDeOrigem() {
		return showIndustriaDeOrigem;
	}

	public void setShowIndustriaDeOrigem(boolean showIndustriaDeOrigem) {
		this.showIndustriaDeOrigem = showIndustriaDeOrigem;
	}

	public boolean isShowIngrediente() {
		return showIngrediente;
	}

	public void setShowIngrediente(boolean showIngrediente) {
		this.showIngrediente = showIngrediente;
	}

	public String getValidadeFreezer() {
		return validadeFreezer;
	}

	public void setValidadeFreezer(String validadeFreezer) {
		this.validadeFreezer = validadeFreezer;
	}

	public String getValidadeCongelador() {
		return validadeCongelador;
	}

	public void setValidadeCongelador(String validadeCongelador) {
		this.validadeCongelador = validadeCongelador;
	}

	public String getValidadeRefrigerador() {
		return validadeRefrigerador;
	}

	public void setValidadeRefrigerador(String validadeRefrigerador) {
		this.validadeRefrigerador = validadeRefrigerador;
	}

	public boolean isShowValidadeProduto() {
		return showValidadeProduto;
	}

	public void setShowValidadeProduto(boolean showValidadeProduto) {
		this.showValidadeProduto = showValidadeProduto;
	}

	public boolean isShowAlergico() {
		return showAlergico;
	}

	public void setShowAlergico(boolean showAlergico) {
		this.showAlergico = showAlergico;
	}

	public String getDescricaoAlergico() {
		return descricaoAlergico;
	}

	public void setDescricaoAlergico(String descricaoAlergico) {
		this.descricaoAlergico = descricaoAlergico;
	}

	public boolean isShowGlutenAlergenos() {
		return showGlutenAlergenos;
	}

	public void setShowGlutenAlergenos(boolean showGlutenAlergenos) {
		this.showGlutenAlergenos = showGlutenAlergenos;
	}

	public String getDescricaoGlutenAlergenos() {
		return descricaoGlutenAlergenos;
	}

	public void setDescricaoGlutenAlergenos(String descricaoGlutenAlergenos) {
		this.descricaoGlutenAlergenos = descricaoGlutenAlergenos;
	}

	public boolean isShowUsoProduto() {
		return showUsoProduto;
	}

	public void setShowUsoProduto(boolean showUsoProduto) {
		this.showUsoProduto = showUsoProduto;
	}

	public String getDescricaoUsoProduto() {
		return descricaoUsoProduto;
	}

	public void setDescricaoUsoProduto(String descricaoUsoProduto) {
		this.descricaoUsoProduto = descricaoUsoProduto;
	}

	public boolean isShowProduzido() {
		return showProduzido;
	}

	public void setShowProduzido(boolean showProduzido) {
		this.showProduzido = showProduzido;
	}

	public boolean isShowDistribuido() {
		return showDistribuido;
	}

	public void setShowDistribuido(boolean showDistribuido) {
		this.showDistribuido = showDistribuido;
	}

	public boolean isShowImportado() {
		return showImportado;
	}

	public void setShowImportado(boolean showImportado) {
		this.showImportado = showImportado;
	}

	public String getDescricaoProduzido() {
		return descricaoProduzido;
	}

	public void setDescricaoProduzido(String descricaoProduzido) {
		this.descricaoProduzido = descricaoProduzido;
	}

	public String getDescricaoDistribuido() {
		return descricaoDistribuido;
	}

	public void setDescricaoDistribuido(String descricaoDistribuido) {
		this.descricaoDistribuido = descricaoDistribuido;
	}

	public String getDescricaoImportado() {
		return descricaoImportado;
	}

	public void setDescricaoImportado(String descricaoImportado) {
		this.descricaoImportado = descricaoImportado;
	}

	public boolean isShowDataFabricacao() {
		return showDataFabricacao;
	}

	public void setShowDataFabricacao(boolean showDataFabricacao) {
		this.showDataFabricacao = showDataFabricacao;
	}

	public boolean isShowPrazoValidade() {
		return showPrazoValidade;
	}

	public void setShowPrazoValidade(boolean showPrazoValidade) {
		this.showPrazoValidade = showPrazoValidade;
	}

	public String getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(String dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public String getPrazoValidade() {
		return prazoValidade;
	}

	public void setPrazoValidade(String prazoValidade) {
		this.prazoValidade = prazoValidade;
	}

	public boolean isShowLote() {
		return showLote;
	}

	public void setShowLote(boolean showLote) {
		this.showLote = showLote;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public boolean isShowInformacaoNutricional() {
		return showInformacaoNutricional;
	}

	public void setShowInformacaoNutricional(boolean showInformacaoNutricional) {
		this.showInformacaoNutricional = showInformacaoNutricional;
	}

	public String getInformacaoNutricional() {
		return informacaoNutricional;
	}

	public void setInformacaoNutricional(String informacaoNutricional) {
		this.informacaoNutricional = informacaoNutricional;
	}

	public boolean isShowTartrazina() {
		return showTartrazina;
	}

	public void setShowTartrazina(boolean showTartrazina) {
		this.showTartrazina = showTartrazina;
	}

	public String getTartrazina() {
		return tartrazina;
	}

	public void setTartrazina(String tartazina) {
		this.tartrazina = tartazina;
	}

	public boolean isShowFenilalanina() {
		return showFenilalanina;
	}

	public void setShowFenilalanina(boolean showFenilalanina) {
		this.showFenilalanina = showFenilalanina;
	}

	public String getFenilalanina() {
		return fenilalanina;
	}

	public void setFenilalanina(String fenilalanina) {
		this.fenilalanina = fenilalanina;
	}

	public boolean isShowSac() {
		return showSac;
	}

	public void setShowSac(boolean showSac) {
		this.showSac = showSac;
	}

	public String getSac() {
		return sac;
	}

	public void setSac(String sac) {
		this.sac = sac;
	}

	public boolean isShowTransgenico() {
		return showTransgenico;
	}

	public void setShowTransgenico(boolean showTransgenico) {
		this.showTransgenico = showTransgenico;
	}

	public String getTransgenico() {
		return transgenico;
	}

	public void setTransgenico(String transgenico) {
		this.transgenico = transgenico;
	}

	public boolean isShowRegistro() {
		return showRegistro;
	}

	public void setShowRegistro(boolean showRegistro) {
		this.showRegistro = showRegistro;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public int getIndexDenominacaoDoProduto() {
		return indexDenominacaoDoProduto;
	}

	public void setIndexDenominacaoDoProduto(int indexDenominacaoDoProduto) {
		this.indexDenominacaoDoProduto = indexDenominacaoDoProduto;
	}

	public int getIndexAromatizante() {
		return indexAromatizante;
	}

	public void setIndexAromatizante(int indexAromatizante) {
		this.indexAromatizante = indexAromatizante;
	}

	public int getIndexPesoLiquido() {
		return indexPesoLiquido;
	}

	public void setIndexPesoLiquido(int indexPesoLiquido) {
		this.indexPesoLiquido = indexPesoLiquido;
	}

	public int getIndexGluten() {
		return indexGluten;
	}

	public void setIndexGluten(int indexGluten) {
		this.indexGluten = indexGluten;
	}

	public int getIndexIndustriaDeOrigem() {
		return indexIndustriaDeOrigem;
	}

	public void setIndexIndustriaDeOrigem(int indexIndustriaDeOrigem) {
		this.indexIndustriaDeOrigem = indexIndustriaDeOrigem;
	}

	public int getIndexIngrediente() {
		return indexIngrediente;
	}

	public void setIndexIngrediente(int indexIngrediente) {
		this.indexIngrediente = indexIngrediente;
	}

	public int getIndexLactose() {
		return indexLactose;
	}

	public void setIndexLactose(int indexLactose) {
		this.indexLactose = indexLactose;
	}

	public int getIndexValidadeProduto() {
		return indexValidadeProduto;
	}

	public void setIndexValidadeProduto(int indexValidadeProduto) {
		this.indexValidadeProduto = indexValidadeProduto;
	}

	public int getIndexAlergico() {
		return indexAlergico;
	}

	public void setIndexAlergico(int indexAlergico) {
		this.indexAlergico = indexAlergico;
	}

	public int getIndexGlutenAlergenos() {
		return indexGlutenAlergenos;
	}

	public void setIndexGlutenAlergenos(int indexGlutenAlergenos) {
		this.indexGlutenAlergenos = indexGlutenAlergenos;
	}

	public int getIndexUsoProduto() {
		return indexUsoProduto;
	}

	public void setIndexUsoProduto(int indexUsoProduto) {
		this.indexUsoProduto = indexUsoProduto;
	}

	public int getIndexProduzido() {
		return indexProduzido;
	}

	public void setIndexProduzido(int indexProduzido) {
		this.indexProduzido = indexProduzido;
	}

	public int getIndexDistribuido() {
		return indexDistribuido;
	}

	public void setIndexDistribuido(int indexDistribuido) {
		this.indexDistribuido = indexDistribuido;
	}

	public int getIndexImportado() {
		return indexImportado;
	}

	public void setIndexImportado(int indexImportado) {
		this.indexImportado = indexImportado;
	}

	public int getIndexDataFabricacao() {
		return indexDataFabricacao;
	}

	public void setIndexDataFabricacao(int indexDataFabricacao) {
		this.indexDataFabricacao = indexDataFabricacao;
	}

	public int getIndexPrazoValidade() {
		return indexPrazoValidade;
	}

	public void setIndexPrazoValidade(int indexPrazoValidade) {
		this.indexPrazoValidade = indexPrazoValidade;
	}

	public int getIndexLote() {
		return indexLote;
	}

	public void setIndexLote(int indexLote) {
		this.indexLote = indexLote;
	}

	public int getIndexInformacaoNutricional() {
		return indexInformacaoNutricional;
	}

	public void setIndexInformacaoNutricional(int indexInformacaoNutricional) {
		this.indexInformacaoNutricional = indexInformacaoNutricional;
	}

	public int getIndexTartrazina() {
		return indexTartrazina;
	}

	public void setIndexTartrazina(int indexTartrazina) {
		this.indexTartrazina = indexTartrazina;
	}

	public int getIndexFenilalanina() {
		return indexFenilalanina;
	}

	public void setIndexFenilalanina(int indexFenilalanina) {
		this.indexFenilalanina = indexFenilalanina;
	}

	public int getIndexSac() {
		return indexSac;
	}

	public void setIndexSac(int indexSac) {
		this.indexSac = indexSac;
	}

	public int getIndexTransgenico() {
		return indexTransgenico;
	}

	public void setIndexTransgenico(int indexTransgenico) {
		this.indexTransgenico = indexTransgenico;
	}

	public int getIndexRegistro() {
		return indexRegistro;
	}

	public void setIndexRegistro(int indexRegistro) {
		this.indexRegistro = indexRegistro;
	}

	public CalculoNutricional getCalculoNutricional() {
		return calculoNutricional;
	}

	public void setCalculoNutricional(CalculoNutricional calculoNutricional) {
		this.calculoNutricional = calculoNutricional;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getCodidoRegistro() {
		this.codidoRegistro = java.util.UUID.randomUUID().toString();
		return codidoRegistro;
	}


	public void setCodidoRegistro(String codidoRegistro) {
		this.codidoRegistro = codidoRegistro;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getEmailPattern() {
		return emailPattern;
	}


	public void setEmailPattern(String emailPattern) {
		this.emailPattern = emailPattern;
	}


	public boolean isSelectAll() {
		return selectAll;
	}


	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}
	
	
}