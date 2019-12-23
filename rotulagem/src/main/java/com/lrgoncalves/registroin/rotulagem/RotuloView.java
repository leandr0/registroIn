/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.lrgoncalves.registroin.rotulagem.data.RotuloDAO;
import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.SimpleObject;
import com.lrgoncalves.registroin.rotulagem.model.Report;

/**
 * @author digitallam
 *
 */
@Named(value = "rotulo_ui")
@SessionScoped
public class RotuloView extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -775136288232260398L;

	private static final Logger LOGGER = Logger.getLogger(RotuloView.class.getName());

	@Inject
	RotuloDAO rotulos;

	private Rotulo rotulo;

	private CalculoNutricional calculoNutricional;

	private List<String> aromatizantes;

	private List<String> glutens;

	private SimpleView denominacaoProduto;

	private SimpleView aromatizante;

	private SimpleView pesoLiquido;

	private SimpleView gluten;

	private SimpleView industriaOrigem;

	private SimpleView ingredientes;

	private SimpleView lactose;

	private SimpleView validadeProduto;

	private SimpleView alergico;

	private SimpleView glutenAlergenos;

	private SimpleView usoProduto;

	private SimpleView produtor;

	private SimpleView distribuidor;

	private SimpleView importador;

	private SimpleView dataFabricacao;

	private SimpleView prazoValidade;

	private SimpleView lote;

	private SimpleView informacaoNutricional;

	private SimpleView tartrazina;

	private SimpleView fenilalanina;

	private SimpleView sac;

	private SimpleView transgenico;

	private SimpleView registroMAPA;

	private String emailPattern = "/^(([^<>()\\[\\]\\.,;:\\s@\\\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@(([^<>()[\\]\\.,;:\\s@\\\"]+\\.)+[^<>()[\\]\\.,;:\\s@\\\"]{2,})$/i";

	private boolean selectAll;

	@PostConstruct
	public void init() {
		
		resetSimpleObjects();
		
		rotulo = new Rotulo();

		aromatizantes = new ArrayList<String>();
		aromatizantes.add("Inserir o termo \"CONTÉM AROMATIZANTE\" no painel principal. Em caixa alta.");
		aromatizantes
				.add("Inserir o termo \"AROMATIZADO ARTIFICIALMENTE\" no painel principal. Em caixa alta e negrito.");
		aromatizantes.add(
				"Inserir o termo \"Contém aromatizante sintético idêntico ao natural\" no painel principal. Em negrito.");

		rotulo.setDenominacaoProduto(new SimpleObject("Sugerimos adequar a denominação do produto para ...."));
		rotulo.setIndustriaOrigem(new SimpleObject("Acrescentar os termos : - \"Indústria <País de Origem>\""));

		glutens = new ArrayList<String>();
		glutens.add(
				"Inserir a inscrição \"NÃO CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito.");
		glutens.add(
				"Inserir a inscrição \"CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito.");

		rotulo.setDerivadosLacteos(new SimpleObject(
				"Inserir a inscrição \"CONTÉM LACTOSE\" logo após a inscrição \"NÃO CONTÉM GLÚTEN\". Em negrito e caixa alta."));
		rotulo.setAlergicos(new SimpleObject(
				"Inserir a inscrição \"ALÉRGICOS: CONTÉM XXXXX E DERIVADOS DE XXXXX\", em caixa alta e negrito, logo após a inscrição \"NÃO CONTÉM GLÚTEN\""));

		rotulo.setProdutor(new SimpleObject("Inserir informações completas do produtor (nome e endereço)"));

		rotulo.setImportador(new SimpleObject("Inserir informações do Importador"));

		rotulo.setDistribuidor(new SimpleObject("Inserir informações do Distribuídor"));

		rotulo.setUsoProduto(new SimpleObject("Texto Descritivo"));

		final String validade = "DD/MM/AA";

		Map<String, String> conservacaoProduto = new LinkedHashMap<String, String>();

		conservacaoProduto.put("Validade a -18˚C(freezer):", validade);
		conservacaoProduto.put("Validade a -4˚C (congelador):", validade);
		conservacaoProduto.put("Validade a 4˚C (refrigerador):", validade);

		rotulo.getConservacaoProduto().setValidadeProduto(conservacaoProduto);
		rotulo.setAlergicos(new SimpleObject(
				"Inserir a inscrição \"ALÉRGICOS: CONTÉM XXXXX E DERIVADOS DE XXXXX\", em caixa alta e negrito, logo após a inscrição \"NÃO CONTÉM GLÚTEN\""));
		rotulo.setGlutenAlergenos(new SimpleObject(
				"Todos os caracteres devem apresentar altura mínima de 1mm, com exceção da informação de GLÚTEN e ALERGENOS, que devem ter altura mínima de 2mm, e do peso líquido que deve ter a altura mínima indicada anteriormente. "));

		rotulo.setPrazoValidade(new SimpleObject("Inserir Data de Validade no formato DD/MM/AA"));
		rotulo.setDataFabricacao(new SimpleObject("Inserir Data de Fabricação no formato DD/MM/AA"));
		rotulo.setLote(new SimpleObject("Inserir número do Lote."));
		rotulo.getInformacaoNutricional().setDescricao(
				"A Informação Nutricional deve seguir EXATAMENTE o modelo permitido pela ANVISA, Com Trans em itálico, e seus valores devem ser ajustados para:");
		rotulo.setTartrazina(new SimpleObject(
				"A lista de ingredientes deve apresentar o nome do corante tartrazina INS 102 por extenso, em caixa alta e negrito."));
		rotulo.setAspartameFenilalanina(
				new SimpleObject("Inserir a informação: \"CONTÉM FENILALANINA\", em caixa alta e negrito"));
		rotulo.setSac(new SimpleObject("Inserir informação para SAC."));
		rotulo.setTransgenico(
				new SimpleObject("Se presente em quantidade superior a 1%, inserir o logo de TRANSGENICO no rótulo."));
		rotulo.setRegistroMAPA(
				new SimpleObject("Inserir a informação: \"Registro no Ministério da Agricultura sob nº XXXX/XXXXX.\""));

		rotulo.setIngredientes(new SimpleObject("Corrigir os ingredientes para ....."));

		rotulo.getInformacaoNutricional().setLegislacao(
				"(*) Valores Diários de referência com base em uma dieta de 2000kcal ou 8400kJ. Seus valores diários podem ser maiores ou menores dependendo das necessidades energéticas.(**) VD não estabelecido.");

		calculoNutricional = new CalculoNutricional();

		String dataPattern = "dd/MM/yyyy";

		DateTimeFormatter dTF = DateTimeFormatter.ofPattern(dataPattern);

		rotulo.setData(dTF.format(LocalDateTime.now()));

		setSelectAll(false);
		handleBooleanValues(isSelectAll());
	}

	public void calcularPorcao() {

		calculoNutricional.calcularPorcao(rotulo.getInformacaoNutricional());

		final double carboidratos = Double.valueOf(rotulo.getInformacaoNutricional().getQtdPorcao().getCarboidratos())
				.doubleValue();
		final double proteinas = Double.valueOf(rotulo.getInformacaoNutricional().getQtdPorcao().getProteinas())
				.doubleValue();
		final double gordurasTotais = Double
				.valueOf(rotulo.getInformacaoNutricional().getQtdPorcao().getGordurasTotais()).doubleValue();

		final double vlrEnergeticoKj = calculoNutricional.calculoValoEnergeticoKj(carboidratos, proteinas,
				gordurasTotais);
		final double vlrEnergeticoKcal = calculoNutricional.calculoValoEnergeticoKcal(carboidratos, proteinas,
				gordurasTotais);

		rotulo.getInformacaoNutricional().getQtdPorcao().setVlrEnergeticoKcal(vlrEnergeticoKcal);
		rotulo.getInformacaoNutricional().getQtdPorcao().setVlrEnergeticoKj(vlrEnergeticoKj);

		rotulo.getInformacaoNutricional().calculoInformacaoNutricionalPorcao();

		calculoNutricional.calculoPercentualDiario(rotulo.getInformacaoNutricional());
	}

	public void calculoPesoLiquido() {

		final int pesoLiquido = Integer.valueOf(rotulo.getPesoLiquido().getPeso());

		if (pesoLiquido <= 50) {
			rotulo.getPesoLiquido().setDescricao(
					"Os números devem ter altura mínima de 2 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");
		} else if (pesoLiquido > 50 && pesoLiquido <= 200) {
			rotulo.getPesoLiquido().setDescricao(
					"Os números devem ter altura mínima de 3 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");
		} else if (pesoLiquido > 200 && pesoLiquido <= 1000) {
			rotulo.getPesoLiquido().setDescricao(
					"Os números devem ter altura mínima de 4 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral.");
		} else if (pesoLiquido > 1000) {
			rotulo.getPesoLiquido().setDescricao(
					"Os números devem ter altura mínima de 6 mm, e a unidade \"kg\", altura mínima de 2/3 o tamanho do numeral.");
		}
	}

	public String report() {

		Report report = new Report();

		int index = 1;

		if (getDenominacaoProduto().isCheck()) {
			report.setItem(index, rotulo.getDenominacaoProduto().getDescricao());
			getDenominacaoProduto().setIndex(index);
			index++;
		}
		if (getAromatizante().isCheck()) {
			report.setItem(index, rotulo.getAromatizante().getDescricao());
			getAromatizante().setIndex(index);
			index++;
		}

		if (getPesoLiquido().isCheck()) {
			report.setItem(index, rotulo.getPesoLiquido().getDescricao());
			getPesoLiquido().setIndex(index);
			index++;
		}

		if (getGluten().isCheck()) {
			report.setItem(index, rotulo.getGluten().getDescricao());
			getGluten().setIndex(index);
			index++;
		}

		if (getIndustriaOrigem().isCheck()) {
			report.setItem(index, rotulo.getIndustriaOrigem().getDescricao());
			getIndustriaOrigem().setIndex(index);
			index++;
		}

		if (getIngredientes().isCheck()) {
			report.setItem(index, rotulo.getIngredientes().getDescricao());
			getIngredientes().setIndex(index);
			index++;
		}

		if (getLactose().isCheck()) {
			report.setItem(index, rotulo.getDerivadosLacteos().getDescricao());
			getLactose().setIndex(index);
			index++;
		}

		if (getAlergico().isCheck()) {
			report.setItem(index, rotulo.getAlergicos().getDescricao());
			getAlergico().setIndex(index);
			index++;
		}

		if (getProdutor().isCheck()) {
			report.setItem(index, rotulo.getProdutor().getDescricao());
			getProdutor().setIndex(index);
			index++;
		}

		if (getDistribuidor().isCheck()) {
			report.setItem(index, rotulo.getDistribuidor().getDescricao());
			getDistribuidor().setIndex(index);
			index++;
		}

		if (getImportador().isCheck()) {
			report.setItem(index, rotulo.getImportador().getDescricao());
			getImportador().setIndex(index);
			index++;
		}

		if (getDataFabricacao().isCheck()) {
			report.setItem(index, rotulo.getDataFabricacao().getDescricao());
			getDataFabricacao().setIndex(index);
			index++;
		}

		if (getPrazoValidade().isCheck()) {
			report.setItem(index, rotulo.getPrazoValidade().getDescricao());
			getPrazoValidade().setIndex(index);
			index++;
		}

		if (getLote().isCheck()) {
			report.setItem(index, rotulo.getLote().getDescricao());
			getLote().setIndex(index);
			index++;
		}

		if (getGlutenAlergenos().isCheck()) {
			report.setItem(index, rotulo.getGlutenAlergenos().getDescricao());
			getGlutenAlergenos().setIndex(index);
			index++;
		}

		if (getUsoProduto().isCheck()) {
			report.setItem(index, rotulo.getUsoProduto().getDescricao());
			getUsoProduto().setIndex(index);
			index++;
		}

		if (getValidadeProduto().isCheck()) {
			report.setItem(index, rotulo.getConservacaoProduto().getValidadeProduto());
			getValidadeProduto().setIndex(index);
			index++;
		}

		if (getInformacaoNutricional().isCheck()) {
			report.setItem(index, rotulo.getInformacaoNutricional().getDescricao());
			getInformacaoNutricional().setIndex(index);
			index++;
		}

		if (getTartrazina().isCheck()) {
			report.setItem(index, rotulo.getTartrazina().getDescricao());
			getTartrazina().setIndex(index);
			index++;
		}

		if (getFenilalanina().isCheck()) {
			report.setItem(index, rotulo.getAspartameFenilalanina().getDescricao());
			getFenilalanina().setIndex(index);
			index++;
		}

		if (getSac().isCheck()) {
			report.setItem(index, rotulo.getSac().getDescricao());
			getSac().setIndex(index);
			index++;
		}

		if (getTransgenico().isCheck()) {
			report.setItem(index, rotulo.getTransgenico().getDescricao());
			getTransgenico().setIndex(index);
			index++;
		}

		if (getRegistroMAPA().isCheck()) {
			report.setItem(index, rotulo.getRegistroMAPA().getDescricao());
			getRegistroMAPA().setIndex(index);
			index++;
		}

		setAttributeInSession("report", report);

		return "report";
	}

	public String save() {

		try {

			rotulos.persistRotulo(rotulo);
			init();

			return "home";

		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

		return null;

	}

	public void checkBoxHandle() {

		if (isSelectAll()) {

			handleBooleanValues(true);

		} else {
			handleBooleanValues(false);

		}

	}

	public void clearData() {
		setAromatizantes(null);
		setCalculoNutricional(null);
		setChaveServico(null);
		setGlutens(null);
		setNomeUsuario(null);
		init();

	}

	private void handleBooleanValues(final boolean value) {

		getAlergico().setCheck(value);
		getAromatizante().setCheck(value);
		getDataFabricacao().setCheck(value);
		getDenominacaoProduto().setCheck(value);
		getDistribuidor().setCheck(value);
		getFenilalanina().setCheck(value);
		getGluten().setCheck(value);
		getGlutenAlergenos().setCheck(value);
		getImportador().setCheck(value);
		getIndustriaOrigem().setCheck(value);
		getInformacaoNutricional().setCheck(value);
		getIngredientes().setCheck(value);
		getLactose().setCheck(value);
		getLote().setCheck(value);
		getPesoLiquido().setCheck(value);
		getPrazoValidade().setCheck(value);
		getProdutor().setCheck(value);
		getRegistroMAPA().setCheck(value);
		getSac().setCheck(value);
		getTartrazina().setCheck(value);
		getTransgenico().setCheck(value);
		getUsoProduto().setCheck(value);
		getValidadeProduto().setCheck(value);
	}

	private void resetSimpleObjects() {

		denominacaoProduto 		= new SimpleView();
		aromatizante 			= new SimpleView();
		pesoLiquido 			= new SimpleView();
		gluten 					= new SimpleView();
		industriaOrigem 		= new SimpleView();
		ingredientes 			= new SimpleView();
		lactose					= new SimpleView();
		validadeProduto 		= new SimpleView();
		alergico 				= new SimpleView();
		glutenAlergenos 		= new SimpleView();
		usoProduto 				= new SimpleView();
		produtor 				= new SimpleView();
		distribuidor 			= new SimpleView();
		importador 				= new SimpleView();
		dataFabricacao 			= new SimpleView();
		prazoValidade 			= new SimpleView();
		lote 					= new SimpleView();
		informacaoNutricional 	= new SimpleView();
		tartrazina 				= new SimpleView();
		fenilalanina 			= new SimpleView();
		sac 					= new SimpleView();
		transgenico 			= new SimpleView();
		registroMAPA 			= new SimpleView();
	}

	public List<String> getAromatizantes() {
		return aromatizantes;
	}

	public void setAromatizantes(List<String> aromatizantes) {
		this.aromatizantes = aromatizantes;
	}

	public List<String> getGlutens() {
		return glutens;
	}

	public void setGlutens(List<String> glutens) {
		this.glutens = glutens;
	}

	public CalculoNutricional getCalculoNutricional() {
		return calculoNutricional;
	}

	public void setCalculoNutricional(CalculoNutricional calculoNutricional) {
		this.calculoNutricional = calculoNutricional;
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

	public Rotulo getRotulo() {
		return rotulo;
	}

	public void setRotulo(Rotulo rotulo) {
		this.rotulo = rotulo;
	}

	public RotuloDAO getRotulos() {
		return rotulos;
	}

	public void setRotulos(RotuloDAO rotulos) {
		this.rotulos = rotulos;
	}

	public SimpleView getDenominacaoProduto() {
		return denominacaoProduto;
	}

	public void setDenominacaoProduto(SimpleView denominacaoProduto) {
		this.denominacaoProduto = denominacaoProduto;
	}

	public SimpleView getAromatizante() {
		return aromatizante;
	}

	public void setAromatizante(SimpleView aromatizante) {
		this.aromatizante = aromatizante;
	}

	public SimpleView getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(SimpleView pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public SimpleView getGluten() {
		return gluten;
	}

	public void setGluten(SimpleView gluten) {
		this.gluten = gluten;
	}

	public SimpleView getIndustriaOrigem() {
		return industriaOrigem;
	}

	public void setIndustriaOrigem(SimpleView industriaOrigem) {
		this.industriaOrigem = industriaOrigem;
	}

	public SimpleView getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(SimpleView ingredientes) {
		this.ingredientes = ingredientes;
	}

	public SimpleView getLactose() {
		return lactose;
	}

	public void setLactose(SimpleView lactose) {
		this.lactose = lactose;
	}

	public SimpleView getValidadeProduto() {
		return validadeProduto;
	}

	public void setValidadeProduto(SimpleView validadeProduto) {
		this.validadeProduto = validadeProduto;
	}

	public SimpleView getAlergico() {
		return alergico;
	}

	public void setAlergico(SimpleView alergico) {
		this.alergico = alergico;
	}

	public SimpleView getGlutenAlergenos() {
		return glutenAlergenos;
	}

	public void setGlutenAlergenos(SimpleView glutenAlergenos) {
		this.glutenAlergenos = glutenAlergenos;
	}

	public SimpleView getUsoProduto() {
		return usoProduto;
	}

	public void setUsoProduto(SimpleView usoProduto) {
		this.usoProduto = usoProduto;
	}

	public SimpleView getProdutor() {
		return produtor;
	}

	public void setProdutor(SimpleView produtor) {
		this.produtor = produtor;
	}

	public SimpleView getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(SimpleView distribuidor) {
		this.distribuidor = distribuidor;
	}

	public SimpleView getImportador() {
		return importador;
	}

	public void setImportador(SimpleView importador) {
		this.importador = importador;
	}

	public SimpleView getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(SimpleView dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public SimpleView getPrazoValidade() {
		return prazoValidade;
	}

	public void setPrazoValidade(SimpleView prazoValidade) {
		this.prazoValidade = prazoValidade;
	}

	public SimpleView getLote() {
		return lote;
	}

	public void setLote(SimpleView lote) {
		this.lote = lote;
	}

	public SimpleView getInformacaoNutricional() {
		return informacaoNutricional;
	}

	public void setInformacaoNutricional(SimpleView informacaoNutricional) {
		this.informacaoNutricional = informacaoNutricional;
	}

	public SimpleView getTartrazina() {
		return tartrazina;
	}

	public void setTartrazina(SimpleView tartrazina) {
		this.tartrazina = tartrazina;
	}

	public SimpleView getFenilalanina() {
		return fenilalanina;
	}

	public void setFenilalanina(SimpleView fenilalanina) {
		this.fenilalanina = fenilalanina;
	}

	public SimpleView getSac() {
		return sac;
	}

	public void setSac(SimpleView sac) {
		this.sac = sac;
	}

	public SimpleView getTransgenico() {
		return transgenico;
	}

	public void setTransgenico(SimpleView transgenico) {
		this.transgenico = transgenico;
	}

	public SimpleView getRegistroMAPA() {
		return registroMAPA;
	}

	public void setRegistroMAPA(SimpleView registroMAPA) {
		this.registroMAPA = registroMAPA;
	}
}