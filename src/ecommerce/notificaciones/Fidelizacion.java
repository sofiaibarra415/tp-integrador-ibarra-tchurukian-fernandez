package ecommerce.notificaciones;
import ecommerce.pedido.EstadoPedido;

public class Fidelizacion implements ObserverPedido {
	@Override
	public void actualizar(EstadoPedido anterior, EstadoPedido nuevo) {
	    if (nuevo.aplicaCuponFidelidad()) {
	        System.out.println("Enviando cupón de descuento del 5%.");
	    }
	}
}
