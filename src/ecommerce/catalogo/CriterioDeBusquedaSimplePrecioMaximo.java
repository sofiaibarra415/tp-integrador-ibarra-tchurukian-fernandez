package ecommerce.catalogo;

import ecommerce.items.Item;

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
