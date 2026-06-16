package ecommerce.pago;

public abstract class MetodoDePago {

	public final void procesarPago(DatosPago datos, double monto) {
		
		if (!validarDatos(datos)) {
			System.out.println("Los datos son inválidos. Transacción cancelada.");
        }
		else {
			String idReserva = reservarFondos(monto, datos);
			String idTransaccion = ejecutarTransaccion(datos, idReserva, monto);
			notificarResultado(idTransaccion, datos, monto);
		}
    }
	//devuelve false si los datos son inválidos
	protected abstract boolean validarDatos(DatosPago datos);
	//devuelve id de reserva
	protected abstract String reservarFondos(double monto, DatosPago datos);
	//devuelve id de transacción
	protected abstract String ejecutarTransaccion(DatosPago datos, String idReserva, double monto);
	
	protected ResultadoPago notificarResultado(String idTransaccion, DatosPago datos, double monto) {
		//registrar código de transacción (implementar)
		ResultadoPago res = new ResultadoPago();
		return res;
	}
}