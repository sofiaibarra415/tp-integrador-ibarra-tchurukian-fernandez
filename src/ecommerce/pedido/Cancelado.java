package ecommerce.pedido;

public class Cancelado extends EstadoPedido {

    @Override
    public String getNombreEstado() {
        return "CANCELADO";
    }
    
    @Override
    public boolean aplicaCuponFidelidad() { 
    	return true; 
    }
}
