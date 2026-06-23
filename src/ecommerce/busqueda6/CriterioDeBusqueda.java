package ecommerce.busqueda6;

import ecommerce.catalogo1.Item;


public abstract class CriterioDeBusqueda {

    public abstract boolean evaluar (Item item);
}