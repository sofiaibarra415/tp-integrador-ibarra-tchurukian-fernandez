package ecommerce.notificaciones;
import ecommerce.pedido.EstadoPedido;

public class GeneradorFactura implements ObserverPedido {
	@Override
	public void actualizar(EstadoPedido anterior, EstadoPedido nuevo) {
	    if (nuevo.esEntregado()) {
	        System.out.println("Generando factura para pedido entregado.");
	    }
	}

}
