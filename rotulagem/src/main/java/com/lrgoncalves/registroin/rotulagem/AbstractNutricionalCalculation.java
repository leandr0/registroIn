/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.lrgoncalves.registroin.rotulagem.model.TabelaNutricional;

/**
 * @author digitallam
 *
 */
public abstract class AbstractNutricionalCalculation {

	
	protected  double calculoValoEnergeticoKcal(TabelaNutricional porcao) {

		double carboidratos = BigDecimal.valueOf(porcao.getCarboidratos() * 4).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();

		double protetinas = BigDecimal.valueOf(porcao.getProteinas() * 4).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double gordurasTotais = BigDecimal.valueOf(porcao.getGorduraTotais() * 9).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double result = BigDecimal.valueOf(carboidratos + protetinas + gordurasTotais).setScale(0, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		return result;
	}
	
	protected  String calculoBaseDesc(double total, double porcao, double razao) {

		double part = BigDecimal.valueOf(total * porcao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		double result = BigDecimal.valueOf(part / razao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		return roundDesc(result);
	}	

	protected  double calculoBase(double total, double porcao, double razao) {

		double part = BigDecimal.valueOf(total * porcao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		double result = BigDecimal.valueOf(part / razao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		return round(result);
	}	
	
	protected  double calculoValoEnergeticoKj(TabelaNutricional porcao) {

		double carboidratos = BigDecimal.valueOf(porcao.getCarboidratos() * 17).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();

		double protetinas = BigDecimal.valueOf(porcao.getProteinas() * 17).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double gordurasTotais = BigDecimal.valueOf(porcao.getGorduraTotais() * 37).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double result = BigDecimal.valueOf(carboidratos + protetinas + gordurasTotais).setScale(0, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		return result;
	}
	
	/***
	 * entre 0 -0.99	2 casas dec
entre1 - 9.9	1 casa dec
acima de 10	Inteiro
	 * @param porcao
	 * @return
	 */
	
	protected  String roundDesc(double value) {
		
		String result = "";
		
		if(value == 0 ) {
			result = BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_EVEN).toString();
		}
		else if(value > 0 && value <= 0.99) {
			result = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_EVEN).toString();
		}
		else if (value >= 1 && value <= 9.9) {
			result = BigDecimal.valueOf(value).setScale(1, RoundingMode.HALF_EVEN).toString();
		}else if (value >= 10) {
			result = BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_EVEN).toString();
		}  
		
		return result;
	}
	
	protected  double round(double value) {
		
		double result = 0.0;
		
		if(value == 0 ) {
			result = BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_EVEN).doubleValue();
		}
		else if(value > 0 && value <= 0.99) {
			result = BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		}
		else if (value >= 1 && value <= 9.9) {
			result = BigDecimal.valueOf(value).setScale(1, RoundingMode.HALF_EVEN).doubleValue();
		}else if (value >= 10) {
			result = BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_EVEN).doubleValue();
		}  
		
		return result;
	}
	protected  double roundVD(double value) {
		
			return BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_EVEN).doubleValue();
		}
}
