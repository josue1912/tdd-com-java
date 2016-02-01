package br.com.caelum.leilao.dominio;

import org.junit.Before;
import org.junit.Test;

public class LanceTest {

	
	private Usuario jose;
	
	@Before
	public void setup(){
		this.jose = new Usuario("josé");
	}

	@Test(expected=IllegalArgumentException.class)
    public void deveRecusarLancesComValorDeZero() {
        new Lance(jose, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void deveRecusarLancesComValorNegativo() {
        new Lance(jose, -10);
    }
}
