package eccomerce;

import java.util.ArrayList;

abstract public class Item {
	
	private String 	nombre;
	private String 	descripcion;
	private String	categoria;
	private double 	descuentoPromocional;
	private ArrayList<Atributo> atributos = new ArrayList<>();
	private double peso = 0;	

protected Item(String nombre, String descripcion, String categoria, double descuentoPromocional) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuentoPromocional = descuentoPromocional;
	}

abstract protected void 	incrementarStock();
abstract protected void 	decrementarStock();
abstract protected double 	getPrecioBase();
abstract protected boolean 	esItemValido();
abstract protected int		getStock();
abstract protected boolean	hayStock();
abstract protected void     agregarAtributo(String unNombre, String unValor);
abstract protected boolean  existeAtributoOpcional(String nombreAtributo);
abstract protected String   getAtributoOpcional(String nombreAtributo);
abstract protected boolean  hayPeso();

protected String getNombre() {
	return this.nombre;
}
protected String getCategoria() {
	return this.categoria;
}
protected String getDescripcion() {
	return this.descripcion;
}
protected double getDescuentoPromocional() {
	return this.descuentoPromocional;
}
protected double getPrecioFinal() {
	return this.getPrecioBase() * ( 1 - this.getDescuentoPromocional() / 100 );
}

protected ArrayList<Atributo> getAtributos() {
	return atributos;
}

protected double getPeso() {
	return peso;
}

protected void setPeso(double peso) {
	this.peso = peso;
}



}
