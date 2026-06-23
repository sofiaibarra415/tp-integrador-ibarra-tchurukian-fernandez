package ecommerce.busqueda6;

import ecommerce.catalogo1.Item;

public class CriterioDeBusquedaSimpleNombre extends CriterioDeBusqueda {
    
	private String elNombreQueBusco;

    public CriterioDeBusquedaSimpleNombre(String unNombreABuscar) {
        this.elNombreQueBusco = unNombreABuscar.toLowerCase();//lo paso a minusculas
    }

    @Override
    public boolean evaluar(Item item) {
        return item.getNombre()						//consigo el nombre del item
        		   .toLowerCase()					//tambien lo paso a minusculas
        		   .contains(this.elNombreQueBusco);//y ahora comparo minusculas con minusculas
    }
}
