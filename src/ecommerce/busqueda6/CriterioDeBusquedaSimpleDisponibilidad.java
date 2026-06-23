package ecommerce.busqueda6;

import ecommerce.catalogo1.Item;

public class CriterioDeBusquedaSimpleDisponibilidad extends CriterioDeBusqueda {



	@Override
    public boolean evaluar(Item item) {
        return item.hayStock();
    }
}
