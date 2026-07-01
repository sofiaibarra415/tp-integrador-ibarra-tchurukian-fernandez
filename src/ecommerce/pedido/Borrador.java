package ecommerce.pedido;

import ecommerce.catalogo1.Item;

public class Borrador extends EstadoPedido {

    @Override
    public void confirmar(Pedido pedido) {
        if (!pedido.tieneMetodoEnvio()) {
            throw new OperacionInvalidaException("Debe asignar un método de envío antes de confirmar");
        }
        pedido.setEstado(new Confirmado(pedido));
    }

    @Override
    public void cancelar(Pedido pedido) {
    	pedido.setEstado(new Cancelado());
    }

    @Override
    public void agregarItem(Pedido pedido, Item i) {
        pedido.addItem(i);
    }

    @Override
    public void quitarItem(Pedido pedido, Item i) {
        pedido.removeItem(i);
    }

    @Override
    public String getNombreEstado() {
        return "BORRADOR";
    }
}