package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorValor = Double.MIN_VALUE;
	private double menorValor = Double.MAX_VALUE;

	public void avalia(Leilao leilao){
		for (Lance lance: leilao.getLances() ){
			if (lance.getValor() > maiorValor) maiorValor = lance.getValor();
			if (lance.getValor() < menorValor) menorValor = lance.getValor();
		}
	}
	
	public double getValorMedio(Leilao leilao){
		double valorMedio = 0;
		double somaValores = 0;

		for(Lance lance : leilao.getLances()){
			somaValores += lance.getValor();
		}

		valorMedio = somaValores/leilao.getLances().size();
		
		return valorMedio;
	}
	
	public double getMaiorValor() {
		return maiorValor;
	}
	
	public double getMenorValor() {
		return menorValor;
	}
	
	
}
