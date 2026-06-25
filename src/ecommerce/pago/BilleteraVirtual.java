package ecommerce.pago;

public class BilleteraVirtual extends MetodoDePago {
	
	private APIBilletera api;
    //private RegistroPagos reg;
	
	public BilleteraVirtual(APIBilletera api, RegistroPagos reg) {
		this.api = api;
		this.reg = reg;
	}
	@Override
	protected boolean validarDatos(DatosPago datos, double monto) {
		if (!(datos instanceof DatosBilleteraVirtual)) {
            throw new IllegalArgumentException("Datos inválidos para Billetera Virtual");
        }
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;

		return api.saldoSuficienteEnLaCuenta(dt.getAlias(), dt.getSaldo(), monto);
	}

	@Override
	protected String reservarFondos(DatosPago datos, double monto) {
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;
		
		return api.bloquearSaldoDeLaCuenta(dt.getAlias(), monto);
	}

	@Override
	protected String ejecutarTransaccion(DatosPago datos, double monto, String idReserva) {
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;
		
		return api.confirmarTransaccion(dt.getAlias(), idReserva);
	}

	@Override
	protected void notificarResultado(DatosPago datos, double monto, String idTransaccion) {
		api.push("Transacción " + idTransaccion + " exitosa");
		reg.agregarId(idTransaccion);
	}
}
