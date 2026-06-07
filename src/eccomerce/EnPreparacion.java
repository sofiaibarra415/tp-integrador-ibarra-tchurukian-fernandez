package eccomerce;

public class EnPreparacion extends EstadoPedido {
    public EnPreparacion(Pedido p) {
        super(p);
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
