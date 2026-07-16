package ecommerce.pago;

public class TarjetaDeCredito extends MetodoDePago<DatosTarjetaCredito> {
	
    private APITarjeta api;
	
	public TarjetaDeCredito(APITarjeta api, RegistroPagos reg) {
		 this.api = api;
		 this.reg = reg;
	}
	@Override
	protected boolean validarDatos(DatosTarjetaCredito datos, double monto) {
		DatosTarjetaCredito dt = datos;
		return api.sonDatosValidos(dt.getNumeroTarjeta(), dt.getCvv(), dt.getVencimiento());
	}
	
	@Override
	protected String reservarFondos(DatosTarjetaCredito datos, double monto) {
		DatosTarjetaCredito dt = datos;
		return api.preAutorizar(dt.getNumeroTarjeta(), dt.getCvv(), monto);
	}
	
	@Override
	protected String ejecutarTransaccion(DatosTarjetaCredito datos, double monto, String idReserva) {
		return api.ejecutarTransaccion(idReserva);
	}
	
	@Override
	protected void notificarResultado(DatosTarjetaCredito datos, double monto, String idTransaccion) {
		DatosTarjetaCredito dt = datos;
		reg.agregarComprobante(new CuponPago(idTransaccion, dt.getNumeroTarjeta(), dt.getCvv(), monto));
		reg.agregarId(idTransaccion);
	}
}