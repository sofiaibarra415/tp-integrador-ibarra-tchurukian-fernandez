package ecommerce.pedido;
import ecommerce.catalogo1.Item;

public class EnPreparacion extends EstadoPedido {
    public EnPreparacion(Pedido p) {
        super(p);
    }

    @Override
    public void cancelar() {
        for (Item i : pedido.getItems()) {
            i.incrementarStock();
        }
        pedido.generarNotaDeCredito(pedido.calcularTotal() + pedido.calcularCostoEnvio());
        pedido.setEstado(new Cancelado(pedido));
    }
    
    @Override
    public void enviar() {
    	pedido.setEstado(new Enviado(pedido));
    }

    @Override
    public String getNombreEstado() {
        return "EN_PREPARACION";
    }
}
