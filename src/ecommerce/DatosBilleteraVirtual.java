package ecommerce;

import java.math.BigDecimal;

public class DatosBilleteraVirtual implements DatosPago {
	
	private BigDecimal saldo;
	
	public DatosBilleteraVirtual(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public BigDecimal getSaldo() { return saldo; }
}
