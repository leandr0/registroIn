/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

/**
 * @author digitallam
 *
 */
public class Rotulo implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2572041025114523780L;

	private String id;
	
	private String data;
	
	private Client client;
	
	private InformacaoNutricional informacaoNutricional;
	
	private SimpleObject denominacaoProduto;
	
	private ConservacaoProduto conservacaoProduto;
	
	private SimpleObject distribuidor;
	
	private SimpleObject tartrazina;
	
	private SimpleObject aromatizante;
	
	private SimpleObject derivadosLacteos;
	
	private SimpleObject importador;
	
	private SimpleObject aspartameFenilalanina;
	
	private PesoLiquido pesoLiquido;
	
	private SimpleObject alergicos;
	
	private SimpleObject dataFabricacao;
	
	private SimpleObject sac;
	
	private SimpleObject gluten;
	
	private SimpleObject glutenAlergenos;
	
	private SimpleObject prazoValidade;
	
	private SimpleObject transgenico;
	
	private SimpleObject industriaOrigem;
	
	private SimpleObject usoProduto;
	
	private SimpleObject lote;
	
	private SimpleObject registroMAPA;
	
	private SimpleObject ingredientes;
	
	private SimpleObject produtor;

	public Rotulo() {
		
		id = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
		
		client 	= new Client();
		
		informacaoNutricional = new InformacaoNutricional();
		
		denominacaoProduto	= new SimpleObject();
		aromatizante		= new SimpleObject();
		gluten				= new SimpleObject();
		pesoLiquido 		= new PesoLiquido();
		industriaOrigem     = new SimpleObject();
		ingredientes		= new SimpleObject();
		conservacaoProduto	= new ConservacaoProduto();
		
		derivadosLacteos	= new SimpleObject();
		alergicos			= new SimpleObject();
		glutenAlergenos		= new SimpleObject();
		usoProduto			= new SimpleObject();
		produtor			= new SimpleObject();
		distribuidor		= new SimpleObject();
		importador			= new SimpleObject();
		dataFabricacao		= new SimpleObject();
		prazoValidade		= new SimpleObject();
		lote				= new SimpleObject();
		tartrazina			= new SimpleObject();
		
		aspartameFenilalanina	= new SimpleObject();
		
		sac 			= new SimpleObject();
		transgenico		= new SimpleObject();
		registroMAPA 	= new SimpleObject();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public InformacaoNutricional getInformacaoNutricional() {
		return informacaoNutricional;
	}

	public void setInformacaoNutricional(InformacaoNutricional informacaoNutricional) {
		this.informacaoNutricional = informacaoNutricional;
	}

	public SimpleObject getDenominacaoProduto() {
		return denominacaoProduto;
	}

	public void setDenominacaoProduto(SimpleObject denominacaoProduto) {
		this.denominacaoProduto = denominacaoProduto;
	}

	public ConservacaoProduto getConservacaoProduto() {
		return conservacaoProduto;
	}

	public void setConservacaoProduto(ConservacaoProduto conservacaoProduto) {
		this.conservacaoProduto = conservacaoProduto;
	}

	public SimpleObject getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(SimpleObject distribuidor) {
		this.distribuidor = distribuidor;
	}

	public SimpleObject getTartrazina() {
		return tartrazina;
	}

	public void setTartrazina(SimpleObject tartrazina) {
		this.tartrazina = tartrazina;
	}

	public SimpleObject getAromatizante() {
		return aromatizante;
	}

	public void setAromatizante(SimpleObject aromatizante) {
		this.aromatizante = aromatizante;
	}

	public SimpleObject getDerivadosLacteos() {
		return derivadosLacteos;
	}

	public void setDerivadosLacteos(SimpleObject derivadosLacteos) {
		this.derivadosLacteos = derivadosLacteos;
	}

	public SimpleObject getImportador() {
		return importador;
	}

	public void setImportador(SimpleObject importador) {
		this.importador = importador;
	}

	public SimpleObject getAspartameFenilalanina() {
		return aspartameFenilalanina;
	}

	public void setAspartameFenilalanina(SimpleObject aspartameFenilalanina) {
		this.aspartameFenilalanina = aspartameFenilalanina;
	}

	public PesoLiquido getPesoLiquido() {
		return pesoLiquido;
	}

	public void setPesoLiquido(PesoLiquido pesoLiquido) {
		this.pesoLiquido = pesoLiquido;
	}

	public SimpleObject getAlergicos() {
		return alergicos;
	}

	public void setAlergicos(SimpleObject alergicos) {
		this.alergicos = alergicos;
	}

	public SimpleObject getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(SimpleObject dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public SimpleObject getSac() {
		return sac;
	}

	public void setSac(SimpleObject sac) {
		this.sac = sac;
	}

	public SimpleObject getGluten() {
		return gluten;
	}

	public void setGluten(SimpleObject gluten) {
		this.gluten = gluten;
	}

	public SimpleObject getGlutenAlergenos() {
		return glutenAlergenos;
	}

	public void setGlutenAlergenos(SimpleObject glutenAlergenos) {
		this.glutenAlergenos = glutenAlergenos;
	}

	public SimpleObject getPrazoValidade() {
		return prazoValidade;
	}

	public void setPrazoValidade(SimpleObject prazoValidade) {
		this.prazoValidade = prazoValidade;
	}

	public SimpleObject getTransgenico() {
		return transgenico;
	}

	public void setTransgenico(SimpleObject transgenico) {
		this.transgenico = transgenico;
	}

	public SimpleObject getIndustriaOrigem() {
		return industriaOrigem;
	}

	public void setIndustriaOrigem(SimpleObject industriaOrigem) {
		this.industriaOrigem = industriaOrigem;
	}

	public SimpleObject getUsoProduto() {
		return usoProduto;
	}

	public void setUsoProduto(SimpleObject usoProduto) {
		this.usoProduto = usoProduto;
	}

	public SimpleObject getLote() {
		return lote;
	}

	public void setLote(SimpleObject lote) {
		this.lote = lote;
	}

	public SimpleObject getRegistroMAPA() {
		return registroMAPA;
	}

	public void setRegistroMAPA(SimpleObject registroMAPA) {
		this.registroMAPA = registroMAPA;
	}

	public SimpleObject getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(SimpleObject ingredientes) {
		this.ingredientes = ingredientes;
	}

	public SimpleObject getProdutor() {
		return produtor;
	}

	public void setProdutor(SimpleObject produtor) {
		this.produtor = produtor;
	}
}
