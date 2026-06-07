package eccomerce;

public class Confirmado extends EstadoPedido {
	public Confirmado(Pedido p) {
        super(p);
        for (Item i : p.getItems()) {
        	i.decrementarStock();
        }
    }
    
	@Override
	public void cancelar() {
	    for (Item i : pedido.getItems()) {
	        i.incrementarStock();
	    }
	    pedido.reembolsar(pedido.calcularTotal() + pedido.calcularCostoEnvio());
	    pedido.setEstado(new Cancelado(pedido));
	}

    @Override
    public String getNombreEstado() {
        return "CONFIRMADO";
    }
    
    @Override
    public void iniciarPreparacion() {
        pedido.setEstado(new EnPreparacion(pedido));
    }
}
