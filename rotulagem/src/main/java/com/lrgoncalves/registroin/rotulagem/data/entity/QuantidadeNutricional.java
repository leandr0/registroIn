package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

public class QuantidadeNutricional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7239774934751276301L;

	private String valorEnergetico;
	
	private String carboidratos;
	
	private String  proteinas;
	
	private String gordurasTotais;
	
	private String gordurasSaturadas;
	
	private String gordurasTrans;
	
	private String fibraAlimentar;
	
	private String sodio;

	private double vlrEnergeticoKcal;
	
	private double vlrEnergeticoKj;

	public String getValorEnergetico() {
		return valorEnergetico;
	}

	public void setValorEnergetico(String valorEnergetico) {
		this.valorEnergetico = valorEnergetico;
	}

	public String getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(String carboidratos) {
		this.carboidratos = carboidratos;
	}

	public String getProteinas() {
		return proteinas;
	}

	public void setProteinas(String proteinas) {
		this.proteinas = proteinas;
	}

	public String getGordurasTotais() {
		return gordurasTotais;
	}

	public void setGordurasTotais(String gordurasTotais) {
		this.gordurasTotais = gordurasTotais;
	}

	public String getGordurasSaturadas() {
		return gordurasSaturadas;
	}

	public void setGordurasSaturadas(String gordurasSaturadas) {
		this.gordurasSaturadas = gordurasSaturadas;
	}

	public String getGordurasTrans() {
		return gordurasTrans;
	}

	public void setGordurasTrans(String gordurasTrans) {
		this.gordurasTrans = gordurasTrans;
	}

	public String getFibraAlimentar() {
		return fibraAlimentar;
	}

	public void setFibraAlimentar(String fibraAlimentar) {
		this.fibraAlimentar = fibraAlimentar;
	}

	public String getSodio() {
		return sodio;
	}

	public void setSodio(String sodio) {
		this.sodio = sodio;
	}

	public double getVlrEnergeticoKcal() {
		return vlrEnergeticoKcal;
	}

	public void setVlrEnergeticoKcal(double vlrEnergeticoKcal) {
		this.vlrEnergeticoKcal = vlrEnergeticoKcal;
	}

	public double getVlrEnergeticoKj() {
		return vlrEnergeticoKj;
	}

	public void setVlrEnergeticoKj(double vlrEnergeticoKj) {
		this.vlrEnergeticoKj = vlrEnergeticoKj;
	}
}
