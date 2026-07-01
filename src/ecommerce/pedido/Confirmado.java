package ecommerce.pedido;

import ecommerce.catalogo1.Item;

public class Confirmado extends EstadoPedido {
	public Confirmado(Pedido pedido) {
        for (Item i : pedido.getItems()) {
        	i.decrementarStock();
        	i.registrarVenta();
        }
    }

	@Override
	public void cancelar(Pedido pedido) {
	    for (Item i : pedido.getItems()) {
	        i.incrementarStock();
	    }
	    pedido.generarNotaDeCredito(pedido.calcularTotal() + pedido.calcularCostoEnvio());
	    pedido.setEstado(new Cancelado());
	}

    @Override
    public String getNombreEstado() {
        return "CONFIRMADO";
    }

    @Override
    public void iniciarPreparacion(Pedido pedido) {
        pedido.setEstado(new EnPreparacion());
    }
    
    @Override
    public boolean esAlertable() { 
    	return true; 
    }
}
