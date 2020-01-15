/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.lrgoncalves.registroin.rotulagem.CalculoNutricional;
import com.lrgoncalves.registroin.rotulagem.data.entity.ClassificacaoAzeite;
import com.lrgoncalves.registroin.rotulagem.data.entity.ClassificacaoAzeiteItem;
import com.lrgoncalves.registroin.rotulagem.data.entity.Rotulo;
import com.lrgoncalves.registroin.rotulagem.data.entity.SimpleObject;

/**
 * @author digitallam
 *
 */
@Named(value = "rotulo_ui")
@SessionScoped
public class UIRotuloBean extends UIAbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -345039370128406195L;

	private static final Logger LOGGER = Logger.getLogger(UIRotuloBean.class);

	private static final String DESCRICAO_ALTURA_MINIMA = "Este ítem deve apresentar altura mínima de 2mm.";
	
	private static final String DERIVADOS_LACTEOS_LEGISLACAO = "De acordo com a RDC 136/17 ANVISA, ";
	
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

	private SimpleView outros;

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
	
	private SimpleView azeite;
	
	private SimpleView denominacaoAzeite;
	
	private SimpleView classificacaoAzeite;

	private String emailPattern = "/^(([^<>()\\[\\]\\.,;:\\s@\\\"]+(\\.[^<>()\\[\\]\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@(([^<>()[\\]\\.,;:\\s@\\\"]+\\.)+[^<>()[\\]\\.,;:\\s@\\\"]{2,})$/i";

	private boolean selectAll;
	
	private SimpleObject selectedItemOutros;

	@PostConstruct
	public void init() {
		
		resetSimpleObjects();
		
		resetListBoxes();
		
		resetRotulo();
		
		setSelectAll(false);
		
		handleBooleanValues(isSelectAll());
	}
	
	private void resetRotulo() {
		
		rotulo = new Rotulo();

		calculoNutricional = new CalculoNutricional();

		String dataPattern = "dd/MM/yyyy";

		DateTimeFormatter dTF = DateTimeFormatter.ofPattern(dataPattern);

		rotulo.setData(dTF.format(LocalDateTime.now()));

		resetAzeite();
		
		resetDenominacaoProduto();
		
		resetIndustriaOrigem();
		
		resetDerivadosLacteos();
		
		resetAlergicos();
		
		resetProdutor();
		
		resetImportador();
		
		resetDistribuidor();
		
		resetOutros();

		resetConservacaoProdutos();
				
		resetGlutenAlergenos();
		
		resetPrazoValidade();
		
		resetDataFrabricacao();
		
		resetLote();
		
		resetInformacaoNutricional();
		
		resetTartrazina();
		
		resetAspartameFenilalanina();
		
		resetSac();
		
		resetTransgenico();
		
		resetRegistroMAPA();
		
		resetIngredientes();

	}

	private void resetIngredientes() {
		rotulo.setIngredientes(new SimpleObject("Corrigir os ingredientes para ....."));
		
	}

	private void resetRegistroMAPA() {
		rotulo.setRegistroMAPA(
				new SimpleObject("Inserir a informação: \"Registro no Ministério da Agricultura sob nº XXXX/XXXXX.\""));
	}

	private void resetTransgenico() {
		rotulo.setTransgenico(
				new SimpleObject("Se presente em quantidade superior a 1%, inserir o logo de TRANSGENICO no rótulo."));
		
	}

	private void resetSac() {
		rotulo.setSac(new SimpleObject("Inserir informação para SAC."));
		
	}

	private void resetAspartameFenilalanina() {
		rotulo.setAspartameFenilalanina(
				new SimpleObject("Inserir a informação: \"CONTÉM FENILALANINA\", em caixa alta e negrito"));
		
	}

	private void resetTartrazina() {
		rotulo.setTartrazina(new SimpleObject(
				"A lista de ingredientes deve apresentar o nome do corante tartrazina INS 102 por extenso, em caixa alta e negrito."));
		
	}

	private void resetInformacaoNutricional() {
		rotulo.getInformacaoNutricional().setDescricao(
				"A Informação Nutricional deve seguir EXATAMENTE o modelo permitido pela ANVISA, Com Trans em itálico, e seus valores devem ser ajustados para:");
		
		rotulo.getInformacaoNutricional().setLegislacao(
				"(*) Valores Diários de referência com base em uma dieta de 2000kcal ou 8400kJ. Seus valores diários podem ser maiores ou menores dependendo das necessidades energéticas.(**) VD não estabelecido.");

		
	}

	private void resetLote() {
		rotulo.setLote(new SimpleObject("Inserir número do Lote."));
		
	}

	private void resetDataFrabricacao() {
		rotulo.setDataFabricacao(new SimpleObject("Inserir Data de Fabricação no formato DD/MM/AA"));
		
	}

	private void resetPrazoValidade() {
		rotulo.setPrazoValidade(new SimpleObject("De acordo com a RDC 259/02, inserir Data de Validade no formato DD/MM/AA"));
		
	}

	private void resetGlutenAlergenos() {
		rotulo.setGlutenAlergenos(new SimpleObject(
				"Todos os caracteres devem apresentar altura mínima de 1mm, com exceção da informação de GLÚTEN e ALERGENOS, que devem ter altura mínima de 2mm, e do peso líquido que deve ter a altura mínima indicada anteriormente. "));
	}

	private void resetConservacaoProdutos() {

		Map<String, String> conservacaoProduto = new LinkedHashMap<String, String>();

		conservacaoProduto.put("Validade a -18˚C(freezer):"		, "Conforme prazo de validade.");
		conservacaoProduto.put("Validade a -4˚C (congelador):"	, "XX meses.");
		conservacaoProduto.put("Validade a 4˚C (refrigerador):"	, "XX dias.");

		rotulo.getConservacaoProduto().setValidadeProduto(conservacaoProduto);
		
	}

	private void resetOutros() {
		rotulo.setOutros(new LinkedList<SimpleObject>());
	}

	private void resetDistribuidor() {
		rotulo.setDistribuidor(new SimpleObject("Inserir informações do Distribuídor (nome e endereço)."));
		
	}

	private void resetImportador() {
		rotulo.setImportador(new SimpleObject("Inserir informações do Importador (nome e endereço)."));
		
	}

	private void resetProdutor() {
		rotulo.setProdutor(new SimpleObject("Inserir informações completas do produtor (nome e endereço)"));
		
	}

	private void resetAlergicos() {
		rotulo.setAlergicos(new SimpleObject(
				DERIVADOS_LACTEOS_LEGISLACAO+"inserir a inscrição \"ALÉRGICOS: CONTÉM XXXXX E DERIVADOS DE XXXXX\", em caixa alta e negrito, logo após a inscrição \"NÃO CONTÉM GLÚTEN\" "+DESCRICAO_ALTURA_MINIMA));
	}

	private void resetDerivadosLacteos() {
		rotulo.setDerivadosLacteos(new SimpleObject(
				DERIVADOS_LACTEOS_LEGISLACAO+"inserir a inscrição \"CONTÉM LACTOSE\" logo após a inscrição \"NÃO CONTÉM GLÚTEN\". Em negrito e caixa alta. "+DESCRICAO_ALTURA_MINIMA ));
		
	}

	private void resetIndustriaOrigem() {
		rotulo.setIndustriaOrigem(new SimpleObject("Acrescentar os termos : - \"Indústria <País de Origem>\""));
		
	}

	private void resetDenominacaoProduto() {
		rotulo.setDenominacaoProduto(new SimpleObject("A denominação do produto deve estar no painel principal do rótulo. Sugerimos adequar a denominação do produto para ...."));
	}
	
	private void resetListBoxes() {

		final String aromatizanteLegislacao = "Conforme o informe técnico 26/07 ANVISA, ";
		
		aromatizantes = new ArrayList<String>();
		aromatizantes.add(aromatizanteLegislacao+"nserir o termo \"CONTÉM AROMATIZANTE\" no painel principal. Em caixa alta.");
		aromatizantes.add(aromatizanteLegislacao+"inserir o termo \"AROMATIZADO ARTIFICIALMENTE\" no painel principal. Em caixa alta e negrito.");
		aromatizantes.add(aromatizanteLegislacao+"inserir o termo \"Contém aromatizante sintético idêntico ao natural\" no painel principal. Em negrito.");
		
		
		final String glutenLegislacao = "De acordo com a Lei 10674/03, ";
		
		glutens = new ArrayList<String>();
		glutens.add(glutenLegislacao+"inserir a inscrição \"NÃO CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito. ");
		glutens.add(glutenLegislacao+"inserir a inscrição \"CONTÉM GLÚTEN\" logo após a lista de ingredientes. Em caixa alta e negrito. ");
		
	}
	
	private void resetAzeite() {
		
		final String denominacaoAzeiteDesc = "Certifique-se que as letras do tipo de azeite possuem a mesma dimensão dos caractéres indicativos do peso, conforme IN 01/2012: \n" + 
				"A informação relativa ao tipo de azeite oliva constantes na marcação ou rotulagem deve ser grafada na principal e em carácteres do mesmo tamanho que as dimensões especificadas para o conteúdo líquido previstas em legislação específica.";
		
		final String classificacaoAzeiteDesc = "O Azeite de Oliva Extra Virgem, deve seguir os limites de tolerância estabelecidos pelo MAPA, conforme valores definidos na tabela abaixo:";
		
		
		rotulo.getAzeite().setDenominacao(new SimpleObject(denominacaoAzeiteDesc));
		
		rotulo.getAzeite().setClassificacao(new ClassificacaoAzeite(classificacaoAzeiteDesc));
		
		rotulo.getAzeite().getClassificacao().setAcidezLivre(new ClassificacaoAzeiteItem("Acidez Livre (%)","≤ 0,80","≤ 2,0"));
		rotulo.getAzeite().getClassificacao().setIndicesPeroxidos(new ClassificacaoAzeiteItem("Índices de Peróxidos (meq/kg)","≤ 20,0","≤ 20,0"));
		rotulo.getAzeite().getClassificacao().setExtEspecUltravioleta232(new ClassificacaoAzeiteItem("Extinção específica no ultravioleta 232nm","≤ 2,50","≤ 2,6"));
		rotulo.getAzeite().getClassificacao().setExtEspecUltravioleta270(new ClassificacaoAzeiteItem("Extinção específica no ultravioleta 270nm","≤ 0,22","≤ 0,25"));
		rotulo.getAzeite().getClassificacao().setExtEspecUltravioletaDelta(new ClassificacaoAzeiteItem("Extinção específica no ultravioleta Delta K","≤ 0,01","≤ 0,01"));
		
	}
	
	public void calcularPorcao() {

		calculoNutricional.calcularPorcao(rotulo.getInformacaoNutricional());

		final double carboidratos = Double.valueOf(rotulo.getInformacaoNutricional().getQtdPorcao().getCarboidratos())
				.doubleValue();
		final double proteinas = Double.valueOf(rotulo.getInformacaoNutricional().getQtdPorcao().getProteinas())
				.doubleValue();
		final double gordurasTotais = Double
				.valueOf(rotulo.getInformacaoNutricional().getQtdPorcao().getGordurasTotais()).doubleValue();

		final double vlrEnergeticoKj = calculoNutricional.
				calculoValoEnergeticoKj(carboidratos, proteinas,
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

		
		final String pesoLiquidoLegislacao = "De acordo com a portaria INMETRO 157/02, ";
		final String indicacaoPesoLiquido  = " A indicação do peso líquido deve estar no painel frontal do rótulo.";
		
		if (pesoLiquido <= 50) {
			rotulo.getPesoLiquido().setDescricao(
					pesoLiquidoLegislacao+"os números devem ter altura mínima de 2 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral."+indicacaoPesoLiquido);
		} else if (pesoLiquido > 50 && pesoLiquido <= 200) {
			rotulo.getPesoLiquido().setDescricao(
					pesoLiquidoLegislacao+"os números devem ter altura mínima de 3 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral."+indicacaoPesoLiquido);
		} else if (pesoLiquido > 200 && pesoLiquido <= 1000) {
			rotulo.getPesoLiquido().setDescricao(
					pesoLiquidoLegislacao+"os números devem ter altura mínima de 4 mm, e a unidade \"g\", altura mínima de 2/3 o tamanho do numeral."+indicacaoPesoLiquido);
		} else if (pesoLiquido > 1000) {
			rotulo.getPesoLiquido().setDescricao(
					pesoLiquidoLegislacao+"os números devem ter altura mínima de 6 mm, e a unidade \"kg\", altura mínima de 2/3 o tamanho do numeral."+indicacaoPesoLiquido);
		}
	}

	
	public String edit() {
		
		rotulo = (Rotulo) getSessionAttribute("rotulo");
		
		buildRotuloFromReport();
		
		return "rotulo";
	}
	
	public String novoRotulo() {
		
		clearData();
		
		return "rotulo";
	}
	
	
	public String report() {

		int index = 1;

		if (getDenominacaoProduto().isCheck()) {
			rotulo.getDenominacaoProduto().setIndexReport(index);
			index++;
		}else {
			rotulo.getDenominacaoProduto().setIndexReport(0);
		}
		if (getAromatizante().isCheck()) {
			rotulo.getAromatizante().setIndexReport(index);
			index++;
		}else {
			rotulo.getAromatizante().setIndexReport(0);
		}

		if (getPesoLiquido().isCheck()) {
			rotulo.getPesoLiquido().setIndexReport(index);
			index++;
		}else {
			rotulo.getPesoLiquido().setIndexReport(0);
		}

		if (getGluten().isCheck()) {
			rotulo.getGluten().setDescricao(rotulo.getGluten().getDescricao()+DESCRICAO_ALTURA_MINIMA);
			rotulo.getGluten().setIndexReport(index);
			index++;
		}else {
			rotulo.getGluten().setIndexReport(0);
		}

		if (getIndustriaOrigem().isCheck()) {
			rotulo.getIndustriaOrigem().setIndexReport(index);
			index++;
		}else {
			rotulo.getIndustriaOrigem().setIndexReport(0);
		}

		if (getIngredientes().isCheck()) {
			rotulo.getIngredientes().setIndexReport(index);
			index++;
		}else {
			rotulo.getIngredientes().setIndexReport(0);
		}

		if (getLactose().isCheck()) {
			rotulo.getDerivadosLacteos().setIndexReport(index);
			index++;
		}else {
			rotulo.getDerivadosLacteos().setIndexReport(0);
		}

		if (getAlergico().isCheck()) {
			rotulo.getAlergicos().setIndexReport(index);
			index++;
		}else {
			rotulo.getAlergicos().setIndexReport(0);
		}

		if (getProdutor().isCheck()) {
			rotulo.getProdutor().setIndexReport(index);
			index++;
		}else {
			rotulo.getProdutor().setIndexReport(0);
		}

		if (getDistribuidor().isCheck()) {
			rotulo.getDistribuidor().setIndexReport(index);
			index++;
		}else {
			rotulo.getDistribuidor().setIndexReport(0);
		}

		if (getImportador().isCheck()) {
			rotulo.getImportador().setIndexReport(index);
			index++;
		}else {
			rotulo.getImportador().setIndexReport(0);
		}

		if (getDataFabricacao().isCheck()) {
			rotulo.getDataFabricacao().setIndexReport(index);
			index++;
		}else {
			rotulo.getDataFabricacao().setIndexReport(0);
		}

		if (getPrazoValidade().isCheck()) {
			rotulo.getPrazoValidade().setIndexReport(index);
			index++;
		}else {
			rotulo.getPrazoValidade().setIndexReport(0);
		}

		if (getLote().isCheck()) {
			rotulo.getLote().setIndexReport(index);
			index++;
		}else {
			rotulo.getLote().setIndexReport(0);
		}

		if (getGlutenAlergenos().isCheck()) {
			rotulo.getGlutenAlergenos().setIndexReport(index);
			index++;
		}else {
			rotulo.getGlutenAlergenos().setIndexReport(0);
		}

		if (getValidadeProduto().isCheck()) {
			rotulo.getConservacaoProduto().setIndexReport(index);
			index++;
		}else {
			rotulo.getConservacaoProduto().setIndexReport(0);
		}

		if (getInformacaoNutricional().isCheck()) {
			rotulo.getInformacaoNutricional().setIndexReport(index);
			index++;
		}else {
			rotulo.getInformacaoNutricional().setIndexReport(0);
		}

		if (getTartrazina().isCheck()) {
			rotulo.getTartrazina().setIndexReport(index);
			index++;
		}else {
			rotulo.getTartrazina().setIndexReport(0);
		}

		if (getFenilalanina().isCheck()) {
			rotulo.getAspartameFenilalanina().setIndexReport(index);
			index++;
		}else {
			rotulo.getAspartameFenilalanina().setIndexReport(0);
		}

		if (getSac().isCheck()) {
			rotulo.getSac().setIndexReport(index);
			index++;
		}else {
			rotulo.getSac().setIndexReport(0);
		}

		if (getTransgenico().isCheck()) {
			rotulo.getTransgenico().setIndexReport(index);
			index++;
		}else {
			rotulo.getTransgenico().setIndexReport(0);
		}

		if (getRegistroMAPA().isCheck()) {
			rotulo.getRegistroMAPA().setIndexReport(index);
			index++;
		}else {
			rotulo.getRegistroMAPA().setIndexReport(0);
		}

		if(getAzeite().isCheck()) {
			
			if(getDenominacaoAzeite().isCheck()) {
				rotulo.getAzeite().getDenominacao().setIndexReport(index);
				index++;
			}else {
				rotulo.getAzeite().getDenominacao().setIndexReport(0);
			}
			
			if(getClassificacaoAzeite().isCheck()) {
				rotulo.getAzeite().getClassificacao().setIndexReport(index);
				index++;
			}else {
				rotulo.getAzeite().getClassificacao().setIndexReport(0);
			}
			
			if(getClassificacaoAzeite().isCheck() || getDenominacaoAzeite().isCheck()) {
				rotulo.getAzeite().setIndexReport(1);
			}
		}else {
			rotulo.getAzeite().getDenominacao().setIndexReport(0);
			rotulo.getAzeite().getClassificacao().setIndexReport(0);
			rotulo.getAzeite().setIndexReport(0);
		}
		
		if (getOutros().isCheck()) {
			
			for(SimpleObject outro : rotulo.getOutros()) {
				outro.setIndexReport(index);
				index++;
			}
			
			
		} /*
			 * else { rotulo.getOutros().setIndexReport(0); }
			 */
		
		setSessionAttribute("rotulo", rotulo);

		setSessionAttribute("fromPage", "rotulo");
		
		return "report";
	}

	public String buildRotuloFromReport() {


		if (rotulo.getDenominacaoProduto().getIndexReport() > 0) {
			getDenominacaoProduto().setCheck(true);
		}else {
			resetDenominacaoProduto();
		}
		
		if (rotulo.getAromatizante().getIndexReport() > 0) {
			getAromatizante().setCheck(true);
		}		//Listbox

		
		if (rotulo.getPesoLiquido().getIndexReport() > 0) {
			getPesoLiquido().setCheck(true);
		}//Valor calculado
		
		if (rotulo.getGluten().getIndexReport() > 0) {
			getGluten().setCheck(true);
		}//Listbox
		
		if (rotulo.getIndustriaOrigem().getIndexReport() > 0) {
			getIndustriaOrigem().setCheck(true);
		}else {
			resetIndustriaOrigem();
		}
		
		if (rotulo.getIngredientes().getIndexReport() > 0) {
			getIngredientes().setCheck(true);
		}else {
			resetIngredientes();
		}
		
		if (rotulo.getDerivadosLacteos().getIndexReport() > 0) {
			getLactose().setCheck(true);
		}else {
			resetDerivadosLacteos();
		}
		
		if (rotulo.getAlergicos().getIndexReport() > 0) {
			getAlergico().setCheck(true);
		}else {
			resetAlergicos();
		}
		
		if (rotulo.getProdutor().getIndexReport() > 0) {
			getProdutor().setCheck(true);
		}else {
			resetProdutor();
		}
		
		if (rotulo.getDistribuidor().getIndexReport() > 0) {
			getDistribuidor().setCheck(true);
		}else {
			resetDistribuidor();
		}
		
		if (rotulo.getImportador().getIndexReport() > 0) {
			getImportador().setCheck(true);
		}else {
			resetImportador();
		}
		
		if (rotulo.getDataFabricacao().getIndexReport() > 0) {
			getDataFabricacao().setCheck(true);
		}else {
			resetDataFrabricacao();
		}
		
		if (rotulo.getPrazoValidade().getIndexReport() > 0) {
			getPrazoValidade().setCheck(true);
		}else {
			resetPrazoValidade();
		}
		
		if (rotulo.getLote().getIndexReport() > 0) {
			getLote().setCheck(true);
		}else {
			resetLote();
		}
		
		if (rotulo.getGlutenAlergenos().getIndexReport() > 0) {
			getGlutenAlergenos().setCheck(true);
		}else {
			resetGlutenAlergenos();
		}
		
		if (rotulo.getConservacaoProduto().getIndexReport() > 0) {
			getValidadeProduto().setCheck(true);
		}else {
			resetConservacaoProdutos();
		}
		
		if (rotulo.getInformacaoNutricional().getIndexReport() > 0) {
			getInformacaoNutricional().setCheck(true);
		}else {
			resetInformacaoNutricional();
		}
		
		if (rotulo.getTartrazina().getIndexReport() > 0) {
			getTartrazina().setCheck(true);
		}else {
			resetTartrazina();
		}
		
		if (rotulo.getAspartameFenilalanina().getIndexReport() > 0) {
			getFenilalanina().setCheck(true);
		}else {
			resetAspartameFenilalanina();
		}
		
		if (rotulo.getSac().getIndexReport() > 0) {
			getSac().setCheck(true);
		}else {
			resetSac();
		}
		
		if (rotulo.getTransgenico().getIndexReport() > 0 ) {
			getTransgenico().setCheck(true);
		}else {
			resetTransgenico();
		}
		
		if (rotulo.getRegistroMAPA().getIndexReport() > 0) {
			getRegistroMAPA().setCheck(true);
		}else {
			resetRegistroMAPA();
		}
		
		if(rotulo.getAzeite().getIndexReport() > 0) {
			getAzeite().setCheck(true);
			
			int indexClassificacao 	= 0;
			int indexDenominacao 	= 0;
			
			if(rotulo.getAzeite().getClassificacao() != null && rotulo.getAzeite().getClassificacao().getIndexReport() > 0) {
				indexClassificacao = 	rotulo.getAzeite().getClassificacao().getIndexReport();
				
				getClassificacaoAzeite().setCheck(true);
			}
			if(rotulo.getAzeite().getDenominacao() != null && rotulo.getAzeite().getDenominacao().getIndexReport() > 0) {
				indexDenominacao = rotulo.getAzeite().getDenominacao().getIndexReport();
				getDenominacaoAzeite().setCheck(true);
			}
			resetAzeite();
			rotulo.getAzeite().setIndexReport(1);
			rotulo.getAzeite().getClassificacao().setIndexReport(indexClassificacao);
			rotulo.getAzeite().getDenominacao().setIndexReport(indexDenominacao);
			
		}else {
			resetAzeite();
		}
		
		if (!rotulo.getOutros().isEmpty()) {
			getOutros().setCheck(true);
		}else {
			resetOutros();
		}
		
		setSessionAttribute("rotulo", rotulo);

		setSessionAttribute("fromPage", "rotulo");
		
		return "report";
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
		getOutros().setCheck(value);
		getValidadeProduto().setCheck(value);
		getAzeite().setCheck(value);
		getClassificacaoAzeite().setCheck(value);
		getDenominacaoAzeite().setCheck(value);
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
		outros 					= new SimpleView();
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
		azeite					= new SimpleView();
		classificacaoAzeite		= new SimpleView();
		denominacaoAzeite		= new SimpleView();
	}

	public void addItemOutros() {
		
		int index = getOutros().getIndex()+1;
		
		rotulo.getOutros().add(new SimpleObject(index,getOutros().getValue()));
		getOutros().setValue(null);
		getOutros().setIndex(index);
	}
	
	public void deleteItemOutros() {
	
		int index = 0;
		
		for(SimpleObject so : rotulo.getOutros()) {
			if(selectedItemOutros.getIndexReport() == so.getIndexReport()) {
				index = rotulo.getOutros().indexOf(so);
				break;
			}
		}
		
		rotulo.getOutros().remove(index);
	}
	
	public SimpleObject getSelectedItemOutros() {
		return selectedItemOutros;
	}

	public void setSelectedItemOutros(SimpleObject selectedItemOutros) {
		this.selectedItemOutros = selectedItemOutros;
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

	public SimpleView getOutros() {
		return outros;
	}

	public void setOutros(SimpleView outros) {
		this.outros = outros;
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

	public SimpleView getAzeite() {
		return azeite;
	}

	public void setAzeite(SimpleView azeite) {
		this.azeite = azeite;
	}

	public SimpleView getDenominacaoAzeite() {
		return denominacaoAzeite;
	}

	public void setDenominacaoAzeite(SimpleView denominacaoAzeite) {
		this.denominacaoAzeite = denominacaoAzeite;
	}

	public SimpleView getClassificacaoAzeite() {
		return classificacaoAzeite;
	}

	public void setClassificacaoAzeite(SimpleView classificacaoAzeite) {
		this.classificacaoAzeite = classificacaoAzeite;
	}
}