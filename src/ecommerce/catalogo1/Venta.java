package ecommerce.catalogo1;

import java.time.LocalDate;

public class Venta {
	private LocalDate fecha;
	private double precioCobrado;
	
	public Venta(double precioCobrado) {
		this.fecha = LocalDate.now();
		this.precioCobrado = precioCobrado;
	}
	
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public double getPrecioCobrado() {
		return this.precioCobrado;
	}

}
