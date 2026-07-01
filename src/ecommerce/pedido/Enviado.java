package ecommerce.pedido;

import ecommerce.catalogo1.Item;

public class Enviado extends EstadoPedido{

	@Override
	public void cancelar(Pedido pedido) {
	    for (Item i : pedido.getItems()) {
	        i.incrementarStock();
	    }
	    pedido.generarNotaDeCredito(pedido.calcularTotal());
	    pedido.setEstado(new Cancelado());
	}

    @Override
    public void entregar(Pedido pedido) {
        pedido.setEstado(new Entregado());
    }
    
    @Override
    public String getNombreEstado() {
        return "ENVIADO";
    }
    
    @Override
    public boolean esAlertable() { 
    	return true; 
    }
}
