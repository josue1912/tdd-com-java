package br.com.caelum.leilao.servico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private double maiorValor = Double.MIN_VALUE;
	private double menorValor = Double.MAX_VALUE;
	private double valorMedio = 0;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao){
		double somaValores = 0;
		for (Lance lance: leilao.getLances() ){
			if (lance.getValor() > maiorValor) maiorValor = lance.getValor();
			if (lance.getValor() < menorValor) menorValor = lance.getValor();
			somaValores += lance.getValor();
		}
		this.valorMedio = somaValores/leilao.getLances().size();
		pegaOsMaiores(leilao);
	}
	

	private void pegaOsMaiores(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>(){
			public int compare(Lance o1, Lance o2){
				if (o1.getValor() < o2.getValor()) return 1;
				if (o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
		
	}

	public List<Lance> getMaiores() {
		return maiores;
	}

	public double getMaiorValor() {
		return maiorValor;
	}
	
	public double getMenorValor() {
		return menorValor;
	}
	
	public double getValorMedio() {
		return valorMedio;
	}
}
