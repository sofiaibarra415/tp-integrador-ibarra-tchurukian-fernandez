package ecommerce.busqueda6;

import java.util.ArrayList;

import ecommerce.catalogo1.Item;

public class CriterioDeBusquedaCompuestoOR extends CriterioDeBusqueda {
    
    private ArrayList<CriterioDeBusqueda> criteriosHijos;

    public CriterioDeBusquedaCompuestoOR() {
        this.criteriosHijos = new ArrayList<>();
    }

    public void agregarCriterio(CriterioDeBusqueda unCriterio) {
        this.criteriosHijos.add(unCriterio);
    } // para agregar criterios al or

    @Override
    public boolean evaluar(Item item) {
        
        if (criteriosHijos.isEmpty()) {
            return false; // si esta vacia devuelvo false que es el neutro de OR
        }

        for (CriterioDeBusqueda criterioHijo : criteriosHijos) {
            // recorro la lista de criterios y
            // si es un criterio compuesto entonces es recursivo
            
            if (criterioHijo.evaluar(item)) {
                return true; // si cumple alguno retorno true, no sigo evaluando
                             // o sea seria como un circuito corto del OR
            }
        }
        return false; // si recorri todos y ninguno fue true, llege aca y devuelvo false
    }
}
