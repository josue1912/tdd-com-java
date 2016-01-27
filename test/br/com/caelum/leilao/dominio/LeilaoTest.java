package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void naoAceitaDoisLancesSeguidosDoMesmoUsuario(){
		Usuario usuario = new Usuario("Tio Bill");
		Leilao leilao = new Leilao("SO Windows 95");
		
		leilao.propoe(new Lance(usuario, 599.0));
		leilao.propoe(new Lance(usuario, 699.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(599.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void umMesmoUsuarioNaoPodeDarMaisQueCincoLances(){
		Usuario bill = new Usuario("Tio Bill");
		Usuario bia = new Usuario("Bia");
		
		Leilao leilao = new Leilao("SO Windows 95");
		
		leilao.propoe(new Lance(bill, 599.0));
		leilao.propoe(new Lance(bia, 699.0));
		leilao.propoe(new Lance(bill, 799.0));
		leilao.propoe(new Lance(bia, 899.0));
		leilao.propoe(new Lance(bill, 999.0));
		leilao.propoe(new Lance(bia, 1099.0));
		leilao.propoe(new Lance(bill, 1199.0));
		leilao.propoe(new Lance(bia, 1299.0));
		leilao.propoe(new Lance(bill, 1399.0));
		leilao.propoe(new Lance(bia, 1499.0));

		//Não deve ser inserido na lista
		leilao.propoe(new Lance(bill, 1599.0));
		
		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size()-1;
		Lance ultimoLance = leilao.getLances().get(ultimo);
		assertEquals(1499.0, ultimoLance.getValor(), 0.00001);
		
	}
	
	@Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }
	
	@Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }
}
