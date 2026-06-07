package eccomerce;

public class Cancelado extends EstadoPedido {
	public Cancelado(Pedido p) {
		super(p);
	}
	
    @Override
    public String getNombreEstado() {
        return "CANCELADO";
    }
}
