package eccomerce.envio;

import eccomerce.pedido.Pedido;

public class EnvioExpress extends MetodoEnvio {

	@Override
	public double calcularCosto(Pedido p) {
	    float precio = (float) p.calcularTotal();
	    return (double) (precio * 0.15 + 500); //logica inventada
	}

    @Override
    public int estimarDias() {
        return 1;
    }
}