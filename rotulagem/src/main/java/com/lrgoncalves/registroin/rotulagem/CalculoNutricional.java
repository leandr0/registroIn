/**
 * 
 */
package com.lrgoncalves.registroin.rotulagem;

import com.lrgoncalves.registroin.rotulagem.model.TabelaNutricional;

/**
 * @author digitallam
 *
 */
public class CalculoNutricional extends AbstractNutricionalCalculation{

	
	TabelaNutricional dadosBase 		= new TabelaNutricional();
	TabelaNutricional dadosPorcao 		= new TabelaNutricional();
	TabelaNutricional dadosPorcaoVD 	= new TabelaNutricional();
	
	
	
	public TabelaNutricional getDadosBase() {
		return dadosBase;
	}

	public void setDadosBase(TabelaNutricional dadosBase) {
		this.dadosBase = dadosBase;
	}

	public TabelaNutricional getDadosPorcao() {
		return dadosPorcao;
	}

	public void setDadosPorcao(TabelaNutricional dadosPorcao) {
		this.dadosPorcao = dadosPorcao;
	}

	public TabelaNutricional getDadosPorcaoVD() {
		return dadosPorcaoVD;
	}

	public void setDadosPorcaoVD(TabelaNutricional dadosPorcaoVD) {
		this.dadosPorcaoVD = dadosPorcaoVD;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CalculoNutricional calculoNutricional = new CalculoNutricional();

		calculoNutricional.dadosBase.setPeso(100);
		calculoNutricional.dadosBase.setCarboidratos(0.1);
		calculoNutricional.dadosBase.setFibraAlimentar(0.0);
		calculoNutricional.dadosBase.setGordurasSaturadas(4.8);
		calculoNutricional.dadosBase.setGordurasTrans(0.4);
		calculoNutricional.dadosBase.setGorduraTotais(12);
		calculoNutricional.dadosBase.setPeso(100);
		calculoNutricional.dadosBase.setProteinas(32);
		calculoNutricional.dadosBase.setSodio(2000);
		calculoNutricional.dadosBase.setValorEnergetico(102);
		/***
		 * (Carboidratos * 4) + (proteina * 4 ) + (gord totais * 9)
		 */
		
		
		System.out.println("IN Peso :"+ calculoNutricional.dadosBase.getPeso());
		System.out.println("IN Valor Energéticos :"+ calculoNutricional.dadosBase.getValorEnergetico());
		System.out.println("IN Carboidratos :"+ calculoNutricional.dadosBase.getCarboidratos());
		System.out.println("IN Proteínas :"+ calculoNutricional.dadosBase.getProteinas());
		System.out.println("IN Gorduras Totais :"+ calculoNutricional.dadosBase.getGorduraTotais());
		System.out.println("IN Gorduras Saturadas :"+ calculoNutricional.dadosBase.getGordurasSaturadas());
		System.out.println("IN Gorduras Trans :"+ calculoNutricional.dadosBase.getGordurasTrans());
		System.out.println("IN Proteínas :"+ calculoNutricional.dadosBase.getProteinas());
		System.out.println("IN Fibra Alimentar :"+ calculoNutricional.dadosBase.getFibraAlimentar());
		System.out.println("IN Sódio :"+ calculoNutricional.dadosBase.getSodio());
		
		calculoNutricional.dadosPorcao.setPeso(25);
		calculoNutricional.dadosPorcao.setCarboidratos(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getCarboidratos(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setFibraAlimentar(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getFibraAlimentar(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGordurasSaturadas(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getGordurasSaturadas(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGordurasTrans(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getGordurasTrans(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGorduraTotais(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getGorduraTotais(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setProteinas(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getProteinas(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setSodio(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getSodio(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setValorEnergetico(calculoNutricional.calculoBase(calculoNutricional.dadosBase.getValorEnergetico(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		
		
		System.out.println("####################################################");
		System.out.println("Peso : "+calculoNutricional.dadosPorcao.getPeso());
		System.out.println("Valor Energético : "+calculoNutricional.dadosPorcao.getValorEnergetico());
		System.out.println("Carboidratos (g) : "+calculoNutricional.dadosPorcao.getCarboidratos());
		System.out.println("Proteínas (g) : "+calculoNutricional.dadosPorcao.getProteinas());		
		System.out.println("Gorduras Totais (g) : "+calculoNutricional.dadosPorcao.getGorduraTotais());
		System.out.println("Gorduras Saturadas (g) : "+calculoNutricional.dadosPorcao.getGordurasSaturadas());
		System.out.println("GordurasTrans (g) : "+calculoNutricional.dadosPorcao.getGordurasTrans());
		System.out.println("Fibra Alimentar (g) : "+calculoNutricional.dadosPorcao.getFibraAlimentar());
		System.out.println("Sódio (mg) : "+calculoNutricional.dadosPorcao.getSodio());

		/**
		 * carboidratos * 17 
		 * proteinas * 17 
		 * gorduras totais * 37 
		 * = kJ
		 * 
		 * carboidratos * 4 
		 * proteinas * 4 
		 * gorduras totais * 9 
		 * =kcal
		 */

		calculoNutricional.dadosPorcao.setValoEnergeticoKj(calculoNutricional.calculoValoEnergeticoKj(calculoNutricional.dadosPorcao));
		calculoNutricional.dadosPorcao.setValorEnergeticoKcal(calculoNutricional.calculoValoEnergeticoKcal(calculoNutricional.dadosPorcao));
		
		 System.out.println("Valor Energético : "+calculoNutricional.dadosPorcao.getValorEnergeticoDescritivo());

		 calculoNutricional.calculopercentualDiario(calculoNutricional.dadosPorcao, calculoNutricional.dadosPorcaoVD);
		 
	}
	
	
	public void calcularPorcao(CalculoNutricional calculoNutricional) {
		
		calculoNutricional.dadosPorcao.setCarboidratos(calculoBase(calculoNutricional.dadosBase.getCarboidratos(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setFibraAlimentar(calculoBase(calculoNutricional.dadosBase.getFibraAlimentar(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGordurasSaturadas(calculoBase(calculoNutricional.dadosBase.getGordurasSaturadas(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGordurasTrans(calculoBase(calculoNutricional.dadosBase.getGordurasTrans(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGorduraTotais(calculoBase(calculoNutricional.dadosBase.getGorduraTotais(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setProteinas(calculoBase(calculoNutricional.dadosBase.getProteinas(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setSodio(calculoBase(calculoNutricional.dadosBase.getSodio(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setValorEnergetico(calculoBase(calculoNutricional.dadosBase.getValorEnergetico(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		
		calculoNutricional.dadosPorcao.setCarboidratosDesc(calculoBaseDesc(calculoNutricional.dadosBase.getCarboidratos(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setFibraAlimentarDesc(calculoBaseDesc(calculoNutricional.dadosBase.getFibraAlimentar(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGordurasSaturadasDesc(calculoBaseDesc(calculoNutricional.dadosBase.getGordurasSaturadas(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGordurasTransDesc(calculoBaseDesc(calculoNutricional.dadosBase.getGordurasTrans(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setGorduraTotaisDesc(calculoBaseDesc(calculoNutricional.dadosBase.getGorduraTotais(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setProteinasDesc(calculoBaseDesc(calculoNutricional.dadosBase.getProteinas(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setSodioDesc(calculoBaseDesc(calculoNutricional.dadosBase.getSodio(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
		calculoNutricional.dadosPorcao.setValorEnergeticoDesc(calculoBaseDesc(calculoNutricional.dadosBase.getValorEnergetico(), calculoNutricional.dadosPorcao.getPeso(), calculoNutricional.dadosBase.getPeso()));
	
		
	}
	
	public void calculopercentualDiario(TabelaNutricional porcao, TabelaNutricional porcaoDiaria) {
		
		System.out.println("####################################################");
		porcaoDiaria.setValorEnergetico(roundVD(porcao.getValorEnergeticoKcal()/2000*100));
		System.out.println("%VD ValorEnergetico : "+porcaoDiaria.getValorEnergetico());
		System.out.println("%VD ValorEnergetico : "+roundVD(porcaoDiaria.getValorEnergetico()));
		
		porcaoDiaria.setCarboidratos(roundVD(porcao.getCarboidratos()*100/300));
		System.out.println("%VD Carboidratos : "+porcaoDiaria.getCarboidratos());
		System.out.println("%VD Carboidratos : "+roundVD(porcaoDiaria.getCarboidratos()));
		
		porcaoDiaria.setProteinas(roundVD(porcao.getProteinas()*100/75));
		System.out.println("%VD Proteinas : "+porcaoDiaria.getProteinas());
		System.out.println("%VD Proteinas : "+roundVD(porcaoDiaria.getProteinas()));
		
		porcaoDiaria.setGorduraTotais(roundVD(porcao.getGorduraTotais()*100/55));
		System.out.println("%VD Gordura Totais : "+porcaoDiaria.getGorduraTotais());
		System.out.println("%VD Gordura Totais : "+roundVD(porcaoDiaria.getGorduraTotais()));
		
		porcaoDiaria.setGordurasSaturadas(roundVD(porcao.getGordurasSaturadas()*100/22));
		System.out.println("%VD Gorduras Saturadas : "+porcaoDiaria.getGordurasSaturadas());
		System.out.println("%VD Gorduras Saturadas : "+roundVD(porcaoDiaria.getGordurasSaturadas()));
		
		porcaoDiaria.setFibraAlimentar(roundVD(porcao.getFibraAlimentar())*100/25);
		System.out.println("%VD Fibra Alimentar : "+porcaoDiaria.getFibraAlimentar());
		System.out.println("%VD Fibra Alimentar : "+roundVD(porcaoDiaria.getFibraAlimentar()));
		
		porcaoDiaria.setSodio(roundVD(porcao.getSodio()*100/2400));
		System.out.println("%VD Sodio : "+porcaoDiaria.getSodio());
		System.out.println("%VD Sodio : "+roundVD(porcaoDiaria.getSodio()));
	}
		
}
