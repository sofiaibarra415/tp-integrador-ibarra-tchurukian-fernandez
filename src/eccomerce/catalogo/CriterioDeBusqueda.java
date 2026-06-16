package eccomerce.catalogo;

import eccomerce.items.Item;


public abstract class CriterioDeBusqueda {

    public abstract boolean evaluar (Item item);
}