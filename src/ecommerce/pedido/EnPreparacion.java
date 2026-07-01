package ecommerce.pedido;
import ecommerce.catalogo1.Item;

public class EnPreparacion extends EstadoPedido {

    @Override
    public void cancelar(Pedido pedido) {
        for (Item i : pedido.getItems()) {
            i.incrementarStock();
        }
        pedido.generarNotaDeCredito(pedido.calcularTotal() + pedido.calcularCostoEnvio());
        pedido.setEstado(new Cancelado());
    }

    @Override
    public void enviar(Pedido pedido) {
    	pedido.setEstado(new Enviado());
    }

    @Override
    public String getNombreEstado() {
        return "EN_PREPARACION";
    }
}
