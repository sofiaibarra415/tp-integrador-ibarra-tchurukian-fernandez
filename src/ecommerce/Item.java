package ecommerce;

import java.util.ArrayList;

abstract public class Item {
	
	private String 	nombre;
	private String 	descripcion;
	private String	categoria;
	private double 	descuentoPromocional;
	private ArrayList<Atributo> atributos = new ArrayList<>();
	private double peso = 0;	

public Item(String nombre, String descripcion, String categoria, double descuentoPromocional) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuentoPromocional = descuentoPromocional;
	}

abstract public void 	incrementarStock();
abstract public void 	decrementarStock();
abstract public double 	getPrecioBase();
abstract public boolean 	esItemValido();
abstract public int		getStock();
abstract public boolean	hayStock();
abstract public boolean  hayPeso();

public String getNombre() {
	return this.nombre;
}
public String getCategoria() {
	return this.categoria;
}
public String getDescripcion() {
	return this.descripcion;
}
public double getDescuentoPromocional() {
	return this.descuentoPromocional;
}
public double getPrecioFinal() {
	return this.getPrecioBase() * ( 1 - this.getDescuentoPromocional() / 100 );
}

public ArrayList<Atributo> getAtributos() {
	return atributos;
}

public double getPeso() {
	return peso;
}

public void setPeso(double peso) {
	this.peso = peso;
}

public void agregarAtributo(String unNombre, String unValor) {
	if (this.existeAtributoOpcional(unNombre)) {
		throw new IllegalArgumentException("El atributo ya existe"); //error lanza excepcion
	}
	if (unNombre.equalsIgnoreCase("peso")) {
		this.setPeso(Double.parseDouble(unValor)); //convierto string en double
	}
	this.atributos.add(new Atributo(unNombre, unValor)) ;
	// si no existe se crea un atributo y se agrega a la lista de atributos dinamicos
	
}

public boolean existeAtributoOpcional(String nombreAtributo) {
	if (nombreAtributo.equalsIgnoreCase("peso") && this.hayPeso()){
		return true;
		
	}else {
	return this.getAtributos().stream()
		    			      .anyMatch(atributo -> atributo.getNombre().equalsIgnoreCase(nombreAtributo) );
	}
}

public String getAtributoOpcional(String nombreAtributo) {
	if ( ! this.existeAtributoOpcional(nombreAtributo)) {
		throw new IllegalArgumentException("El atributo no existe"); //error lanza excepcion
	}
	
	if (nombreAtributo.equalsIgnoreCase("peso")){
		
		return String.valueOf(this.getPeso());
		
	}else {
	return this.getAtributos().stream()
			             .filter(atributo -> atributo.getNombre().equalsIgnoreCase(nombreAtributo) ) // filtro para encontrar el atributo
			             .findFirst()		// deberia haber uno solo, pero por las dudas elijo el primero
			             .get()				// me da el objeto, si noy falla, se deberia chequear que este el atributo con boolean existeAtributoOpcional(String nombreAtributo) antes de usar 
			             .getValor();		// me da el valor del atributo
	}
}



}
