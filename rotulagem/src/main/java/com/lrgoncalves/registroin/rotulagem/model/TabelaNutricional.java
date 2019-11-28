package com.lrgoncalves.registroin.rotulagem.model;

import java.io.Serializable;

public class TabelaNutricional implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2016799411927292371L;
	
	double peso;
	String pesoDesc;
	double valorEnergetico;
	String valorEnergeticoDesc;
	double carboidratos;
	String carboidratosDesc;
	double proteinas;
	String proteinasDesc;
	double gorduraTotais;
	String gorduraTotaisDesc;
	double gordurasSaturadas;
	String gordurasSaturadasDesc;
	double gordurasTrans;
	String gordurasTransDesc;
	double fibraAlimentar;
	String fibraAlimentarDesc;
	double sodio;
	String sodioDesc;
	double valorEnergeticoKcal;
	String valorEnergeticoKcalDesc;
	double valoEnergeticoKj;
	String valoEnergeticoKjDesc;
	String valorEnergeticoDescritivo;
	String porcao;

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getValorEnergetico() {
		return valorEnergetico;
	}

	public void setValorEnergetico(double valorEnergetico) {
		this.valorEnergetico = valorEnergetico;
	}

	public double getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(double carboidratos) {
		this.carboidratos = carboidratos;
	}

	public double getProteinas() {
		return proteinas;
	}

	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}

	public double getGorduraTotais() {
		return gorduraTotais;
	}

	public void setGorduraTotais(double gorduraTotais) {
		this.gorduraTotais = gorduraTotais;
	}

	public double getGordurasSaturadas() {
		return gordurasSaturadas;
	}

	public void setGordurasSaturadas(double gordurasSaturadas) {
		this.gordurasSaturadas = gordurasSaturadas;
	}

	public double getGordurasTrans() {
		return gordurasTrans;
	}

	public void setGordurasTrans(double gordurasTrans) {
		this.gordurasTrans = gordurasTrans;
	}

	public double getFibraAlimentar() {
		return fibraAlimentar;
	}

	public void setFibraAlimentar(double fibraAlimentar) {
		this.fibraAlimentar = fibraAlimentar;
	}

	public double getSodio() {
		return sodio;
	}

	public void setSodio(double sodio) {
		this.sodio = sodio;
	}

	public double getValorEnergeticoKcal() {
		return valorEnergeticoKcal;
	}

	public void setValorEnergeticoKcal(double valorEnergeticoKcal) {
		this.valorEnergeticoKcal = valorEnergeticoKcal;
	}

	public double getValoEnergeticoKj() {
		return valoEnergeticoKj;
	}

	public void setValoEnergeticoKj(double valoEnergeticoKj) {
		this.valoEnergeticoKj = valoEnergeticoKj;
	}

	public String getValorEnergeticoDescritivo() {
	 
		valorEnergeticoDescritivo = valorEnergeticoKcal+"kcal/"+valoEnergeticoKj+"kJ";
		
		return valorEnergeticoDescritivo;
	}

	public void setValorEnergeticoDescritivo(String valorEnergeticoDescritivo) {
		this.valorEnergeticoDescritivo = valorEnergeticoDescritivo;
	}

	public String getPorcao() {
		return porcao;
	}

	public void setPorcao(String porcao) {
		this.porcao = porcao;
	}

	public String getPesoDesc() {
		return pesoDesc;
	}

	public void setPesoDesc(String pesoDesc) {
		this.pesoDesc = pesoDesc;
	}

	public String getValorEnergeticoDesc() {
		return valorEnergeticoDesc;
	}

	public void setValorEnergeticoDesc(String valorEnergeticoDesc) {
		this.valorEnergeticoDesc = valorEnergeticoDesc;
	}

	public String getCarboidratosDesc() {
		return carboidratosDesc;
	}

	public void setCarboidratosDesc(String carboidratosDesc) {
		this.carboidratosDesc = carboidratosDesc;
	}

	public String getProteinasDesc() {
		return proteinasDesc;
	}

	public void setProteinasDesc(String proteinasDesc) {
		this.proteinasDesc = proteinasDesc;
	}

	public String getGorduraTotaisDesc() {
		return gorduraTotaisDesc;
	}

	public void setGorduraTotaisDesc(String gorduraTotaisDesc) {
		this.gorduraTotaisDesc = gorduraTotaisDesc;
	}

	public String getGordurasSaturadasDesc() {
		return gordurasSaturadasDesc;
	}

	public void setGordurasSaturadasDesc(String gordurasSaturadasDesc) {
		this.gordurasSaturadasDesc = gordurasSaturadasDesc;
	}

	public String getGordurasTransDesc() {
		return gordurasTransDesc;
	}

	public void setGordurasTransDesc(String gordurasTransDesc) {
		this.gordurasTransDesc = gordurasTransDesc;
	}

	public String getFibraAlimentarDesc() {
		return fibraAlimentarDesc;
	}

	public void setFibraAlimentarDesc(String fibraAlimentarDesc) {
		this.fibraAlimentarDesc = fibraAlimentarDesc;
	}

	public String getSodioDesc() {
		return sodioDesc;
	}

	public void setSodioDesc(String sodioDesc) {
		this.sodioDesc = sodioDesc;
	}

	public String getValorEnergeticoKcalDesc() {
		return valorEnergeticoKcalDesc;
	}

	public void setValorEnergeticoKcalDesc(String valorEnergeticoKcalDesc) {
		this.valorEnergeticoKcalDesc = valorEnergeticoKcalDesc;
	}

	public String getValoEnergeticoKjDesc() {
		return valoEnergeticoKjDesc;
	}

	public void setValoEnergeticoKjDesc(String valoEnergeticoKjDesc) {
		this.valoEnergeticoKjDesc = valoEnergeticoKjDesc;
	}

}
