package ecommerce.pedido;

import ecommerce.Item;

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
	
	public boolean esConfirmado()  { return false; }
	public boolean esEnviado()     { return false; }
	public boolean esEntregado()   { return false; }
	public boolean esCancelado()   { return false; }
	
	public abstract String getNombreEstado();
}
