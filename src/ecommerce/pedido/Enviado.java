package ecommerce.pedido;

import ecommerce.catalogo1.Item;

public class Enviado extends EstadoPedido{
	
	public Enviado(Pedido p) {
		super(p);
	}
	
	@Override
	public void cancelar() {
	    for (Item i : pedido.getItems()) {
	        i.incrementarStock();
	    }
	    pedido.generarNotaDeCredito(pedido.calcularTotal());
	    pedido.setEstado(new Cancelado(pedido));
	}
    
    @Override
    public void entregar() {
        pedido.setEstado(new Entregado(pedido));
    }
    
    @Override
    public String getNombreEstado() {
        return "ENVIADO";
    }
    
    @Override
    public boolean esEnviado() { 
    	return true; 
    }
}
