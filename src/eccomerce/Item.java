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

protected void agregarAtributo(String unNombre, String unValor) {
	if (this.existeAtributoOpcional(unNombre)) {
		throw new IllegalArgumentException("El atributo ya existe"); //error lanza excepcion
	}
	this.atributos.add(new Atributo(unNombre, unValor)) ;
	// si no existe se crea un atributo y se agrega a la lista de atributos dinamicos
	
}

protected boolean existeAtributoOpcional(String nombreAtributo) {
	if (nombreAtributo.equalsIgnoreCase("peso") && this.hayPeso()){
		return true;
		
	}else {
	return this.getAtributos().stream()
		    			      .anyMatch(atributo -> atributo.getNombre().equalsIgnoreCase(nombreAtributo) );
	}
}

protected String getAtributoOpcional(String nombreAtributo) {
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
