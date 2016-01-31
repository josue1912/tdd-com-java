package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {

	
	private Avaliador leiloeiro;
	private Usuario maria;
    private Usuario jose;
    private Usuario joao;

    @BeforeClass
    public static void testandoBeforeClass() {
      System.out.println("before class");
    }

    @AfterClass
    public static void testandoAfterClass() {
      System.out.println("after class");
    }
    
	@Before
	public void setUp(){
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
		
	}
	
	@Test
	public void avaliaMaiorEMenorLanceDeUmLeilaoEmOrdemCrescente() {
		Leilao leilao = new CriadorDeLeilao()
	            .para("Playstation 3 Novo")
	            .lance(joao, 100)
	            .lance(maria, 200)
	            .lance(joao, 300)
	            .lance(maria, 400)
	            .constroi();

		leiloeiro.avalia(leilao);

		assertEquals(100.0, leiloeiro.getMenorValor(), 0.00001);
		assertEquals(400.0, leiloeiro.getMaiorValor(), 0.00001);
		assertEquals(250.0, leiloeiro.getValorMedio(), 0.0001);

	}

	@Test
	public void avaliaMaiorEMenosLanceComApenasUmLance() {
		Usuario joao = new Usuario("Joao");
		Leilao leilao = new Leilao("Mesa de sinuca");
		leilao.propoe(new Lance(joao, 200.0));

		leiloeiro.avalia(leilao);

		assertEquals(200.0, leiloeiro.getMaiorValor(), 0.00001);
		assertEquals(200.0, leiloeiro.getMenorValor(), 0.00001);

	}

	@Test
	public void avaliaMaiorEMenorLanceDeUmLeilaoAleatorio() {
		Usuario cris = new Usuario("cris");
		Usuario ju = new Usuario("ju");
		Usuario bia = new Usuario("bia");

		Leilao leilao = new Leilao("Camera fotográfica");

		leilao.propoe(new Lance(cris, 200.0));
		leilao.propoe(new Lance(ju, 450.0));
		leilao.propoe(new Lance(bia, 120.0));
		leilao.propoe(new Lance(cris, 700.0));
		leilao.propoe(new Lance(ju, 630.0));
		leilao.propoe(new Lance(bia, 230.0));


		leiloeiro.avalia(leilao);

		assertEquals(120.0, leiloeiro.getMenorValor(), 0.00001);
		assertEquals(700.0, leiloeiro.getMaiorValor(), 0.00001);
	}

	@Test
	public void avaliaMaiorEMenorLanceDeUmLeilaoEmOrdemDecrescente() {
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario zica = new Usuario("zica");
		Usuario zeca = new Usuario("zeca");

		Leilao leilao = new Leilao("Notebook ASUS");

		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(zica, 200.0));
		leilao.propoe(new Lance(zeca, 100.0));


		leiloeiro.avalia(leilao);

		assertEquals(100.0, leiloeiro.getMenorValor(), 0.00001);
		assertEquals(400.0, leiloeiro.getMaiorValor(), 0.00001);
	}

	@Test
	public void encontraOsTresMaioresLancesDeUmLeilao() {
		Usuario joao = new Usuario("joao");
		Usuario jose = new Usuario("jose");
		Usuario zica = new Usuario("zica");
		Usuario zeca = new Usuario("zeca");
		Usuario zeus = new Usuario("zeca");

		Leilao leilao = new Leilao("Notebook ASUS");

		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 359.0));
		leilao.propoe(new Lance(zica, 200.0));
		leilao.propoe(new Lance(zeca, 900.0));
		leilao.propoe(new Lance(zeus, 100.0));


		leiloeiro.avalia(leilao);

		assertEquals(3, leiloeiro.getMaiores().size());
		assertEquals(900.0, leiloeiro.getMaiores().get(0).getValor(), 0.00001);
		assertEquals(400.0, leiloeiro.getMaiores().get(1).getValor(), 0.00001);
		assertEquals(359.0, leiloeiro.getMaiores().get(2).getValor(), 0.00001);

	}

	@Test
	public void encontraOsTresMaioresLancesDeUmLeilaoComApenasDoisLances() {
		Usuario joao = new Usuario("joao");
		Usuario zeca = new Usuario("zeca");

		Leilao leilao = new Leilao("Notebook ASUS");

		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(zeca, 900.0));

		leiloeiro.avalia(leilao);

		assertEquals(900.0, leiloeiro.getMaiores().get(0).getValor(), 0.00001);
		assertEquals(400.0, leiloeiro.getMaiores().get(1).getValor(), 0.00001);

	}

	@Test
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getMaiores().size());
	}

}
