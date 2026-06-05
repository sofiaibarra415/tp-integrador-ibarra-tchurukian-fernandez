package eccomerce;

abstract public class Item {
	
	private String 	nombre;
	private String 	descripcion;
	private String	categoria;
	private double 	descuentoPromocional;
	

abstract protected void 	incrementarStock();
abstract protected void 	incrementarStockEn(int cantidad);
abstract protected void 	decrementarStock();
abstract protected double 	getPrecioBase();
abstract protected double 	getPrecioFinal();
abstract protected boolean 	existeAtributoOpcional(String nombreAtributo);
abstract protected String 	getAtributoOpcional(String nombreAtributo);
abstract protected boolean 	esItemValido();
abstract protected int		getStock();
abstract protected boolean	hayStock();

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
}
