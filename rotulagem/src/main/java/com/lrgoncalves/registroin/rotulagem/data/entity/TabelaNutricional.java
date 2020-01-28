package com.lrgoncalves.registroin.rotulagem.data.entity;

import java.io.Serializable;

public class TabelaNutricional implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2349867624731716987L;

	private double peso;
	
	private double valorEnergetico;
	
	private double carboidratos;
	
	private double proteinas;
	
	private double gorduraTotais;
	
	private double gordurasSaturadas;
	
	private double gordurasTrans;
	
	private double fibraAlimentar;
	
	private double sodio;
	
	private double valorEnergeticoKcal;
	
	private double valoEnergeticoKj;

	private double pesoPorcao;

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

	public double getPesoPorcao() {
		return pesoPorcao;
	}

	public void setPesoPorcao(double pesoPorcao) {
		this.pesoPorcao = pesoPorcao;
	}

}
