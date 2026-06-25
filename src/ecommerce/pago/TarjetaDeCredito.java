package ecommerce.pago;

public class TarjetaDeCredito extends MetodoDePago {
	
    private APITarjeta api;
    //private RegistroPagos reg;
	
	public TarjetaDeCredito(APITarjeta api, RegistroPagos reg) {
		//this.nroTarjeta = nroTarjeta;
		//this.cvv =cvv;
		//this.vencimiento = vencimiento;
		 this.api = api;
		 this.reg = reg;
	}
	@Override
	protected boolean validarDatos(DatosPago datos, double monto) {
		if (!(datos instanceof DatosTarjetaCredito)) {
            throw new IllegalArgumentException("Datos inválidos para Tarjeta de Crédito");
        }
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		return api.sonDatosValidos(dt.getNumeroTarjeta(), dt.getCvv(), dt.getVencimiento());
	}
	
	@Override
	protected String reservarFondos(DatosPago datos, double monto) {
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		return api.preAutorizar(dt.getNumeroTarjeta(), dt.getCvv(), monto);
	}
	
	@Override
	protected String ejecutarTransaccion(DatosPago datos, double monto, String idReserva) {
		return api.ejecutarTransaccion(idReserva);
	}
	
	@Override
	protected void notificarResultado(DatosPago datos, double monto, String idTransaccion) {
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		reg.agregarComprobante(new CuponPago(idTransaccion, dt.getNumeroTarjeta(), dt.getCvv(), monto));
		reg.agregarId(idTransaccion);
	}
}