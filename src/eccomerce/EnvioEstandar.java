package eccomerce;

public class EnvioEstandar extends MetodoEnvio {
	
	private Direccion direccion;
	
	public EnvioEstandar(Direccion direccion){
		this.direccion = direccion;
	}
	
	public double calcularCosto(Pedido p) {
		return (double) CorreoArgentina.estimarEnvio((float) p.calcularPeso(), direccion);
	}
	
	public int estimarDias() {
		return 7; // devuelvo rango 5 - 7? con el maximo esta bien? como hago?
	}
}
