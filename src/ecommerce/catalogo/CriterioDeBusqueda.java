package ecommerce.catalogo;

import ecommerce.items.Item;


public abstract class CriterioDeBusqueda {

    public abstract boolean evaluar (Item item);
}