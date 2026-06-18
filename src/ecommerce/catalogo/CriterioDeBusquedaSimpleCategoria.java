package ecommerce.catalogo;

import ecommerce.items.Item;

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
