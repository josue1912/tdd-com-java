package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	@Test
	public void avaliaMaiorEMenorLanceDeUmLeilao() {
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario zica = new Usuario("zica");
		
		Leilao leilao = new Leilao("Notebook ASUS");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(jose, 200.0));
		leilao.propoe(new Lance(zica, 300.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		double maiorValor = 300.0;
		double menorValor = 100.0;
		double valorMedio = 200.0;
		
		Assert.assertEquals(menorValor, leiloeiro.getMenorValor(), 0.00001);
		Assert.assertEquals(maiorValor, leiloeiro.getMaiorValor(), 0.00001);
		Assert.assertEquals(valorMedio, leiloeiro.getValorMedio(leilao), 0.0001);
		
	}
	 
}
