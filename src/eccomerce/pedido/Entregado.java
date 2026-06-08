package eccomerce.pedido;

public class Entregado extends EstadoPedido {
	public Entregado(Pedido p) {
		super(p);
	}
	
    @Override
    public String getNombreEstado() {
        return "ENTREGADO";
    }
}
