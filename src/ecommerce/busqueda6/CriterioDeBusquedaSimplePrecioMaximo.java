package ecommerce.busqueda6;

import ecommerce.catalogo1.Item;

public class CriterioDeBusquedaSimplePrecioMaximo extends CriterioDeBusqueda {
    private double precioMaximo;

    public CriterioDeBusquedaSimplePrecioMaximo(double unPrecioMaximo) {
        this.precioMaximo = unPrecioMaximo;
    }

    @Override
    public boolean evaluar(Item item) {
        return item.getPrecioBase() <= this.precioMaximo;
    }
}
