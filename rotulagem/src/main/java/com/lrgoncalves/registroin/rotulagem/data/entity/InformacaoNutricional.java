package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

import com.lrgoncalves.registroin.rotulagem.model.TabelaNutricional;

public class InformacaoNutricional implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3569304456251692783L;

	private String descricao;
	
	private String  pesoTotalGramas;
	
	private String valorPorcao;
	
	private String legislacao;
	
	private String porcao;
	
	private TabelaNutricional dadosBase;
	
	private QuantidadeNutricional qtdPorcao;
	
	private QuantidadeNutricional percVlrDiario;

	private int indexReport;
	
	public InformacaoNutricional() {
		dadosBase 		= new TabelaNutricional();
		qtdPorcao 		= new QuantidadeNutricional();
		percVlrDiario 	= new QuantidadeNutricional();
	}
	
	public void calculoInformacaoNutricionalPorcao() {
		
		getQtdPorcao().setValorEnergetico(
				Double.valueOf(getQtdPorcao().getVlrEnergeticoKcal()).intValue()+"kcal/"
				+Double.valueOf(getQtdPorcao().getVlrEnergeticoKj()).intValue()+"kJ"
		);
	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPesoTotalGramas() {
		return pesoTotalGramas;
	}

	public void setPesoTotalGramas(String pesoTotalGramas) {
		this.pesoTotalGramas = pesoTotalGramas;
	}

	public String getValorPorcao() {
		return valorPorcao;
	}

	public void setValorPorcao(String valorPorcao) {
		this.valorPorcao = valorPorcao;
	}

	public String getLegislacao() {
		return legislacao;
	}

	public void setLegislacao(String legislacao) {
		this.legislacao = legislacao;
	}

	public QuantidadeNutricional getQtdPorcao() {
		return qtdPorcao;
	}

	public void setQtdPorcao(QuantidadeNutricional qtdPorcao) {
		this.qtdPorcao = qtdPorcao;
	}

	public QuantidadeNutricional getPercVlrDiario() {
		return percVlrDiario;
	}

	public void setPercVlrDiario(QuantidadeNutricional percVlrDiario) {
		this.percVlrDiario = percVlrDiario;
	}

	public TabelaNutricional getDadosBase() {
		return dadosBase;
	}

	public void setDadosBase(TabelaNutricional dadosBase) {
		this.dadosBase = dadosBase;
	}

	public String getPorcao() {
		return porcao;
	}

	public void setPorcao(String porcao) {
		this.porcao = porcao;
	}

	public int getIndexReport() {
		return indexReport;
	}

	public void setIndexReport(int indexReport) {
		this.indexReport = indexReport;
	}
}