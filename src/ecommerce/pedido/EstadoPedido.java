package ecommerce.pedido;

import ecommerce.catalogo1.Item;

public abstract class EstadoPedido {

	public void confirmar(Pedido pedido) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}

	public void cancelar(Pedido pedido) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}

	public void iniciarPreparacion(Pedido pedido) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}

	public void enviar(Pedido pedido) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}

	public void entregar(Pedido pedido) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}

	public void agregarItem(Pedido pedido, Item i) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}

	public void quitarItem(Pedido pedido, Item i) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public boolean esAlertable()          { return false; }
	public boolean esFacturable()         { return false; }
	public boolean aplicaCuponFidelidad() { return false; }
	
	public abstract String getNombreEstado();
}
