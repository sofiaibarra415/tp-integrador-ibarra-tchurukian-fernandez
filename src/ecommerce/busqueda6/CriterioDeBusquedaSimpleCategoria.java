package ecommerce.busqueda6;

import ecommerce.catalogo1.Item;

public class CriterioDeBusquedaSimpleCategoria extends CriterioDeBusqueda {
    private String categoriaBuscada;

    public CriterioDeBusquedaSimpleCategoria(String unaCategoria) {
        this.categoriaBuscada = unaCategoria;
    }

    @Override
    public boolean evaluar(Item item) {
        return item.getCategoria().equalsIgnoreCase(this.categoriaBuscada);
    }
}
