package ecommerce.notificaciones;

import ecommerce.pedido.EstadoPedido;

public interface ObserverPedido {
    void actualizar(EstadoPedido estadoAnterior, EstadoPedido estadoNuevo);
}