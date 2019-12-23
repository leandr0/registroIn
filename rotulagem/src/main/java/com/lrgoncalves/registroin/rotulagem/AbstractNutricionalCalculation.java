/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author digitallam
 *
 */
public abstract class AbstractNutricionalCalculation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1024618596768504670L;

	/**
	 * 
	 * @param carboidratos
	 * @param proteinas
	 * @param gordurasTotais
	 * @return @double
	 */
	protected  double calculoValoEnergeticoKcal(final double carboidratos, final double proteinas, final double gordurasTotais) {

		double carboidratosCalculado = BigDecimal.valueOf(carboidratos * 4).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();

		double proteinasCalculada = BigDecimal.valueOf(proteinas * 4).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double gordurasTotaisCalculadas = BigDecimal.valueOf(gordurasTotais * 9).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double result = BigDecimal.valueOf(carboidratosCalculado + proteinasCalculada + gordurasTotaisCalculadas).setScale(0, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		return result;
	}
	
	/**
	 * 
	 * @param total
	 * @param porcao
	 * @param razao
	 * @return @double
	 */
	protected  String calculoBaseDesc(double total, double porcao, double razao) {

		double part = BigDecimal.valueOf(total * porcao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		double result = BigDecimal.valueOf(part / razao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		return roundDesc(result);
	}	

	/**
	 * 
	 * @param total
	 * @param porcao
	 * @param razao
	 * @return @double
	 */
	protected  double calculoBase(double total, double porcao, double razao) {

		double part = BigDecimal.valueOf(total * porcao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		double result = BigDecimal.valueOf(part / razao).setScale(3, RoundingMode.HALF_EVEN).doubleValue();

		return round(result);
	}	
	
	/**
	 * 
	 * @param carboidratos
	 * @param proteinas
	 * @param gordurasTotais
	 * @return @double
	 */
	protected  double calculoValoEnergeticoKj(final double carboidratos, final double proteinas, final double gordurasTotais) {

		double carboidratosCalculado = BigDecimal.valueOf(carboidratos * 17).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();

		double proteinasCalculadas = BigDecimal.valueOf(proteinas * 17).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double gordurasTotaisCalculadas = BigDecimal.valueOf(gordurasTotais * 37).setScale(3, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		double result = BigDecimal.valueOf(carboidratosCalculado + proteinasCalculadas + gordurasTotaisCalculadas).setScale(0, RoundingMode.HALF_EVEN)
				.doubleValue();
		
		return result;
	}
	
	/***
	 * entre 0 -0.99	2 casas dec
	 * entre   1 - 9.9	1 casa dec
     * acima de 10	Inteiro
	 * @param porcao
	 * @return @double
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
	
	/**
	 * 
	 * @param value
	 * @return @double
	 */
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
	
	/**
	 * 
	 * @param @double value
	 * @return @double
	 * 
	 */
	protected  int roundVD(double value) {
		
			return BigDecimal.valueOf(value).setScale(0, RoundingMode.HALF_EVEN).intValue();
		}
}