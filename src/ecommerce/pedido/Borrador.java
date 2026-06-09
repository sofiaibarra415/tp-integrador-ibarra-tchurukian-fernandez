package ecommerce.pedido;

import ecommerce.Item;

public class Borrador extends EstadoPedido {

    public Borrador(Pedido p) {
        super(p);
    }

    @Override
    public void confirmar() {
        if (!pedido.tieneMetodoEnvio()) {
            throw new OperacionInvalidaException("Debe asignar un método de envío antes de confirmar");
        }
        pedido.setEstado(new Confirmado(pedido));
    }

    @Override
    public void cancelar() {
    	pedido.setEstado(new Cancelado(pedido));
    }

    @Override
    public void agregarItem(Item i) {
        pedido.addItem(i);
    }

    @Override
    public void quitarItem(Item i) {
        pedido.removeItem(i);
    }

    @Override
    public String getNombreEstado() {
        return "BORRADOR";
    }
}