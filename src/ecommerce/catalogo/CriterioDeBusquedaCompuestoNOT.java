package ecommerce.catalogo;

import ecommerce.items.Item;

public class CriterioDeBusquedaCompuestoNOT extends CriterioDeBusqueda {
    
    private CriterioDeBusqueda criterioHijo; // tiene un solo criterio, que hay que negar

    public CriterioDeBusquedaCompuestoNOT(CriterioDeBusqueda unCriterio) {
        this.criterioHijo = unCriterio;
    }

    @Override
    public boolean evaluar(Item item) {
        
        return !this.criterioHijo.evaluar(item);
        // mira el criterio, si es recursivo entra calcularlo
        // y despues lo niega y lo retorna
    }
}