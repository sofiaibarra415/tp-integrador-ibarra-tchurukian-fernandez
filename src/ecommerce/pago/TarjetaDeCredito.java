package ecommerce.pago;

public class TarjetaDeCredito extends MetodoDePago {
	
    private APITarjeta api;
	
	public TarjetaDeCredito(APITarjeta api) {
		//this.nroTarjeta = nroTarjeta;
		//this.cvv =cvv;
		//this.vencimiento = vencimiento;
		 this.api = api;
	}
	@Override
	protected boolean validarDatos(DatosPago datos) {
		if (!(datos instanceof DatosTarjetaCredito)) {
            throw new IllegalArgumentException("Datos inválidos para Tarjeta de Crédito");
        }
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		return api.sonDatosValidos(dt.getNumeroTarjeta(), dt.getCvv(), dt.getVencimiento());
	}
	
	@Override
	protected String reservarFondos(double monto, DatosPago datos) {
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		return api.preAutorizar(dt.getNumeroTarjeta(), dt.getCvv(), monto);
	}
	
	@Override
	protected String ejecutarTransaccion(DatosPago datos, String idReserva, double monto) {
		return api.ejecutarTransaccion(idReserva);
	}
	
	@Override
	protected ResultadoPago notificarResultado(String idTransaccion, DatosPago datos, double monto) {
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		return new CuponPago(idTransaccion, dt.getNumeroTarjeta(), dt.getCvv(), monto);
	}
}