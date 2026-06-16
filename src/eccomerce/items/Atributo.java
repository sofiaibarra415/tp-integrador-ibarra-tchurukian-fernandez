package eccomerce.items;

public class Atributo {
private String nombre;
private String valor;

public Atributo(String unNombre, String unValor) {
	this.nombre = unNombre;
	this.valor  = unValor;
}
public String getNombre() {
	return this.nombre;
}
public String getValor() {
	return this.valor;
}
}
