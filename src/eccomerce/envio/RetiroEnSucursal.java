package eccomerce.envio;

import eccomerce.pedido.Pedido;

public class RetiroEnSucursal extends MetodoEnvio {
	private Sucursal sucursal;
	
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public double calcularCosto(Pedido p) {
		return 0;
	}
	
	public int estimarDias() {
		if(sucursal.tieneStockLocal()) {
			return 0;
		}else {
			return 3;
		}
	}
}
