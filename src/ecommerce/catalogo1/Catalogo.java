package ecommerce.catalogo1;

import java.util.ArrayList;

import ecommerce.busqueda6.CriterioDeBusqueda;
import ecommerce.reportes.ItemVisitor;

public class Catalogo {
	
	private ArrayList<Item> itemsDelCatalogo = new ArrayList<>();
	private String nombre;

    public Catalogo(String unNombre) {
        this.nombre = unNombre;
    }

    public String getNombre() {
    	return this.nombre;
    }
    
    public void agregarItem(Item unItem) {
        this.itemsDelCatalogo.add(unItem); //para agregar productos o paquetes
    }

    
    public ArrayList<Item> getItemsDelCatalogo() {
		return itemsDelCatalogo;
	}

	public ArrayList<Item> buscar(CriterioDeBusqueda unCriterio) {
        
    	ArrayList<Item> resultados = new ArrayList<>();
        
        for (Item item : this.itemsDelCatalogo) {
            
            if (unCriterio.evaluar(item)) {
                resultados.add(item);
            }
        }
        
        return resultados;
    }
	
	public String reportar(ItemVisitor unVisitor) {
	    
	    for (Item item : this.itemsDelCatalogo) {
	        item.accept(unVisitor); // el visitor concreto elegido visita cada item
	    }
	    
	    
	    return unVisitor.getResultado(); //obtenemos el resultado
	}
}