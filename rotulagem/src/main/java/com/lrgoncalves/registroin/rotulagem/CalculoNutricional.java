/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import static java.util.logging.Level.INFO;

import java.util.logging.Logger;

import com.lrgoncalves.registroin.rotulagem.data.entity.InformacaoNutricional;

/**
 * @author lrgoncalves
 *
 */
public class CalculoNutricional extends AbstractNutricionalCalculation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1711694583835066453L;
	private static final Logger LOGGER = Logger.getLogger(CalculoNutricional.class.getName());
	
	public void calcularPorcao(InformacaoNutricional informacaoNutricional) {
		
		
		final double pesoPorcao = informacaoNutricional.getDadosBase().getPesoPorcao();
		final double pesoBase   = informacaoNutricional.getDadosBase().getPeso();
		
		informacaoNutricional.getQtdPorcao().setCarboidratos(calculoBaseDesc(informacaoNutricional.getDadosBase().getCarboidratos(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setFibraAlimentar(calculoBaseDesc(informacaoNutricional.getDadosBase().getFibraAlimentar(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setGordurasSaturadas(calculoBaseDesc(informacaoNutricional.getDadosBase().getGordurasSaturadas(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setGordurasTrans(calculoBaseDesc(informacaoNutricional.getDadosBase().getGordurasTrans(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setGordurasTotais(calculoBaseDesc(informacaoNutricional.getDadosBase().getGorduraTotais(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setProteinas(calculoBaseDesc(informacaoNutricional.getDadosBase().getProteinas(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setSodio(calculoBaseDesc(informacaoNutricional.getDadosBase().getSodio(), pesoPorcao, pesoBase));
		informacaoNutricional.getQtdPorcao().setValorEnergetico(calculoBaseDesc(informacaoNutricional.getDadosBase().getValorEnergetico(), pesoPorcao, pesoBase));
	}
	
	/**
	 * 
	 * @param porcao
	 * @param porcaoDiaria
	 */
	public void calculoPercentualDiario(InformacaoNutricional informacaoNutricional) {
		
		informacaoNutricional.getPercVlrDiario().setValorEnergetico(
				String.valueOf(
						roundVD(
								informacaoNutricional.getQtdPorcao().getVlrEnergeticoKcal()/2000*100
								)
						)
				);
		LOGGER.log(INFO, "%VD ValorEnergetico : "+informacaoNutricional.getPercVlrDiario().getValorEnergetico());
		LOGGER.log(INFO, "%VD ValorEnergetico : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getValorEnergetico())));
		
		informacaoNutricional.getPercVlrDiario().setCarboidratos(
				String.valueOf(
						roundVD(
								Double.valueOf(informacaoNutricional.getQtdPorcao().getCarboidratos())*100/300
								)
						)
				);		
		LOGGER.log(INFO, "%VD Carboidratos : "+informacaoNutricional.getPercVlrDiario().getCarboidratos());
		LOGGER.log(INFO, "%VD Carboidratos : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getCarboidratos())));
		
		
		informacaoNutricional.getPercVlrDiario().setProteinas(
				String.valueOf(
						roundVD(
 								Double.valueOf(informacaoNutricional.getQtdPorcao().getProteinas())*100/75
								)
						)
				);		
		LOGGER.log(INFO, "%VD Proteinas : "+informacaoNutricional.getPercVlrDiario().getProteinas());
		LOGGER.log(INFO, "%VD Proteinas : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getProteinas())));

		
		informacaoNutricional.getPercVlrDiario().setGordurasTotais(
				String.valueOf(
						roundVD(
 								Double.valueOf(informacaoNutricional.getQtdPorcao().getGordurasTotais())*100/55
								)
						)
				);	
		LOGGER.log(INFO, "%VD Gordura Totais : "+informacaoNutricional.getPercVlrDiario().getGordurasTotais());
		LOGGER.log(INFO, "%VD Gordura Totais : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getGordurasTotais())));
		
		informacaoNutricional.getPercVlrDiario().setGordurasSaturadas(
				String.valueOf(
						roundVD(
 								Double.valueOf(informacaoNutricional.getQtdPorcao().getGordurasSaturadas())*100/22
								)
						)
				);	
		LOGGER.log(INFO, "%VD Gorduras Saturadas : "+informacaoNutricional.getPercVlrDiario().getGordurasSaturadas());
		LOGGER.log(INFO, "%VD Gorduras Saturadas : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getGordurasSaturadas())));
		
		informacaoNutricional.getPercVlrDiario().setFibraAlimentar(
				String.valueOf(
						roundVD(
 								Double.valueOf(informacaoNutricional.getQtdPorcao().getFibraAlimentar())*100/25
								)
						)
				);	
		LOGGER.log(INFO, "%VD Fibra Alimentar : "+informacaoNutricional.getPercVlrDiario().getFibraAlimentar());
		LOGGER.log(INFO, "%VD Fibra Alimentar : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getFibraAlimentar())));
		
		informacaoNutricional.getPercVlrDiario().setSodio(
				String.valueOf(
						roundVD(
 								Double.valueOf(informacaoNutricional.getQtdPorcao().getSodio())*100/2400
								)
						)
				);	
		LOGGER.log(INFO, "%VD Sodio : "+informacaoNutricional.getPercVlrDiario().getSodio());
		LOGGER.log(INFO, "%VD Sodio : "+roundVD(Double.valueOf(informacaoNutricional.getPercVlrDiario().getSodio())));
	}		
}