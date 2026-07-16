package ecommerce.pago;

public class BilleteraVirtual extends MetodoDePago<DatosBilleteraVirtual> {
	
	private APIBilletera api;
    //private RegistroPagos reg;
	
	public BilleteraVirtual(APIBilletera api, RegistroPagos reg) {
		this.api = api;
		this.reg = reg;
	}
	@Override
	protected boolean validarDatos(DatosBilleteraVirtual datos, double monto) {
		DatosBilleteraVirtual dt = datos;

		return api.saldoSuficienteEnLaCuenta(dt.getAlias(), dt.getSaldo(), monto);
	}

	@Override
	protected String reservarFondos(DatosBilleteraVirtual datos, double monto) {
		DatosBilleteraVirtual dt = datos;
		
		return api.bloquearSaldoDeLaCuenta(dt.getAlias(), monto);
	}

	@Override
	protected String ejecutarTransaccion(DatosBilleteraVirtual datos, double monto, String idReserva) {
		DatosBilleteraVirtual dt = datos;
		
		return api.confirmarTransaccion(dt.getAlias(), idReserva);
	}

	@Override
	protected void notificarResultado(DatosBilleteraVirtual datos, double monto, String idTransaccion) {
		api.push("Transacción " + idTransaccion + " exitosa");
		reg.agregarId(idTransaccion);
	}
}
