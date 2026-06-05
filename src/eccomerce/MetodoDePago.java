package eccomerce;

public abstract class MetodoDePago {
	
	public abstract void validarDatos();
	
	public abstract void reservarFondos();
	
	public abstract void ejecutarTransaccion();
	
	public abstract void notificarResultado();
}