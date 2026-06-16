package ecommerce.pago;

import java.time.LocalDate;

public class ComprobanteCBU extends ResultadoPago {
	private String cbuOcvu;
	private String alias;
	private double monto;
	private String idTransaccion;
	private LocalDate fecha;
	
	public ComprobanteCBU(String cbuOcvu, String alias, double monto, String idTransaccion) {
		this.fecha = LocalDate.now();
		this.cbuOcvu = cbuOcvu;
		this.alias = alias;
		this.monto = monto;
		this.idTransaccion = idTransaccion;
	}
	
	 public String getCbuOcvu() { return cbuOcvu; }
	 public String getAlias() { return alias; }
	 public double getMonto() { return monto; }
	 public String getIDTransaccion() { return idTransaccion; }
	 public LocalDate getFecha() { return fecha; }
}