package ecommerce.catalogo;

import ecommerce.items.Item;

public class CriterioDeBusquedaSimpleDisponibilidad extends CriterioDeBusqueda {



	@Override
    public boolean evaluar(Item item) {
        return item.hayStock();
    }
}
