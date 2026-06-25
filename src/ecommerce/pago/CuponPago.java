package ecommerce.pago;

import java.time.LocalDate;

public class CuponPago implements ComprobantePago {
	
	private String idTransaccion; //no implemento getter porque nunca los usaría
	private String nroTarjeta;
	private String cvv;
	private LocalDate fecha;
	private double monto;
	
	public CuponPago(String idTransaccion, String nroTarjeta, String cvv, double monto) {
		this.idTransaccion = idTransaccion;
		this.nroTarjeta = nroTarjeta;
		this.cvv = cvv;
		this.fecha = LocalDate.now();
		this.monto = monto;
	}
	
	/*public String getCvv() { return cvv; }
	public String getNumeroTarjeta() { return nroTarjeta; }
	public double getMonto() { return monto; }
	public String getIDTransaccion() { return idTransaccion; }
	public LocalDate getFecha() { return fecha; }*/
}
