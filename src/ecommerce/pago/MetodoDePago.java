package ecommerce.pago;

public abstract class MetodoDePago {
	
	protected RegistroPagos reg;
	
	public final void procesarPago(DatosPago datos, double monto) {
		
		if (!validarDatos(datos, monto)) {
			System.out.println("Los datos son inválidos. Transacción cancelada.");
        }
		else {
			String idReserva = reservarFondos(datos, monto);
			String idTransaccion = ejecutarTransaccion(datos, monto, idReserva);
			notificarResultado(datos, monto, idTransaccion);
		}
    }
	//devuelve false si los datos son inválidos
	protected abstract boolean validarDatos(DatosPago datos, double monto);
	//devuelve id de reserva
	protected abstract String reservarFondos(DatosPago datos, double monto);
	//devuelve id de transacción
	protected abstract String ejecutarTransaccion(DatosPago datos, double monto, String idReserva);
	//registra la id de transacción (implementación por defecto, pero nunca se usa)
	protected void notificarResultado(DatosPago datos, double monto, String idTransaccion) {
		reg.agregarId(idTransaccion);
		System.out.println("El código de transacción es: " + idTransaccion);
	}
}