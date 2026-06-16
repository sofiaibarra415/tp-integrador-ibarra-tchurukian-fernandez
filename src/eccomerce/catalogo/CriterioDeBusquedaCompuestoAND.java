package eccomerce.catalogo;

import java.util.ArrayList;

import eccomerce.items.Item;

public class CriterioDeBusquedaCompuestoAND extends CriterioDeBusqueda {
    
    private ArrayList<CriterioDeBusqueda> criteriosHijos;

    public CriterioDeBusquedaCompuestoAND() {
        this.criteriosHijos = new ArrayList<>();
    }

    
    public void agregarCriterio(CriterioDeBusqueda unCriterio) {
        this.criteriosHijos.add(unCriterio);
    }//para agregar criterios al and

    @Override
    public boolean evaluar(Item item) {
        
        if (criteriosHijos.isEmpty()) {
        	return true; //si esta vacia devuelvo true que es el neutor de AND
        }

        for (CriterioDeBusqueda criterioHijo : criteriosHijos) { 
        	//recorro la lista de criterios y 
        	//si es un criterio compuesto estonces es recursivo
        	
        	if (!criterioHijo.evaluar(item)) {
                return false; 	//si no cumple alguno ya retorno false no sigo evaluado
                				//osea seria como un circuito corto
            }
        }
        return true; //y si son todos true llege aca y entonces devuelvo true
    }
}
