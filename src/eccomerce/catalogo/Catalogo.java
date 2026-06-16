package eccomerce.catalogo;

import java.util.ArrayList;
import eccomerce.items.Item;

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

    
    public ArrayList<Item> buscar(CriterioDeBusqueda unCriterio) {
        
    	ArrayList<Item> resultados = new ArrayList<>();
        
        for (Item item : this.itemsDelCatalogo) {
            
            if (unCriterio.evaluar(item)) {
                resultados.add(item);
            }
        }
        
        return resultados;
    }
}