package eccomerce;

import java.util.ArrayList;

abstract public class Item {
	
	private String 	nombre;
	private String 	sku;
	private String 	marca;
	private String	categoria;
	private double 	precioBase;
	private double 	descuentoPromocional;
	private int 	stock;
	private ArrayList<Atributo> atributos = new ArrayList<>();



abstract protected void 	incrementarStock();
abstract protected void 	incrementarStockEn(int cantidad);
abstract protected void 	decrementarStock();
abstract protected double 	getPrecioBase();
abstract protected double 	getPrecioFinal();
abstract protected boolean 	existeAtributoOpcional(String nombreAtributo);
abstract protected String 	getAtributoOpcional(String nombreAtributo);
abstract protected boolean 	esItemValido();
abstract protected String	getNombre();
abstract protected String	getCategoria();
abstract protected String	getSKU();
abstract protected int		getStock();
abstract protected boolean	hayStock();

}
