package ecommerce.pedido;

public class Entregado extends EstadoPedido {

    @Override
    public String getNombreEstado() {
        return "ENTREGADO";
    }
    
    @Override
    public boolean esAlertable() { 
    	return true; 
    }
    
    @Override
    public boolean esFacturable() { 
    	return true; 
    }
}
