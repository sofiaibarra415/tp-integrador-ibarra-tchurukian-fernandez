package eccomerce;

public class Atributo {
private String nombre;
private String valor;

protected Atributo(String unNombre, String unValor) {
	this.nombre = unNombre;
	this.valor  = unValor;
}
protected String getNombre() {
	return this.nombre;
}
protected String getValor() {
	return this.valor;
}
}
