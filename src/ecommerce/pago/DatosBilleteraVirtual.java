package ecommerce.pago;

public class DatosBilleteraVirtual implements DatosPago {
	
	private String alias;
	private double saldo;
	
	public DatosBilleteraVirtual(double saldo, String alias) {
		this.saldo = saldo;
		this.alias = alias;
	}
	
	public double getSaldo() { return saldo; }
	public String getAlias() { return alias; }
}
