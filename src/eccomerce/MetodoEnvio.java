package eccomerce;

public abstract class MetodoEnvio {
	public abstract double calcularCosto(Pedido p);
	public abstract int estimarDias();
}
