package eccomerce.pedido;

import eccomerce.Item;

public abstract class EstadoPedido {
	protected Pedido pedido;
	
	public EstadoPedido(Pedido p){
		this.pedido = p;
	}
	
	public void confirmar() {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public void cancelar() {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public void iniciarPreparacion() {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public void enviar() {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public void entregar() {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public void agregarItem(Item i) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public void quitarItem(Item i) {
		throw new OperacionInvalidaException(
			    "Operación inválida en estado " + getNombreEstado()
		);
	}
	
	public abstract String getNombreEstado();
}
