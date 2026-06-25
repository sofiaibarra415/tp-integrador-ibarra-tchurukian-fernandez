package ecommerce.pago;

public class BilleteraVirtual extends MetodoDePago {
	
	private APIBilletera api;
    private RegistroPagos reg;
	
	public BilleteraVirtual(APIBilletera api, RegistroPagos reg) {
		this.api = api;
		this.reg = reg;
	}
	@Override
	protected boolean validarDatos(DatosPago datos) {
		if (!(datos instanceof DatosBilleteraVirtual)) {
            throw new IllegalArgumentException("Datos inválidos para Billetera Virtual");
        }
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;

		return api.saldoSuficienteEnLaCuenta(dt.getAlias(), dt.getSaldo());
	}

	@Override
	protected String reservarFondos(double monto, DatosPago datos) {
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;
		
		return api.bloquearSaldoDeLaCuenta(dt.getAlias(), monto);
	}

	@Override
	protected String ejecutarTransaccion(DatosPago datos, String idReserva, double monto) {
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;
		
		return api.confirmarTransaccion(dt.getAlias(), idReserva);
	}

	@Override
	protected void notificarResultado(String idTransaccion, DatosPago datos, double monto) {
		api.push("Transacción " + idTransaccion + " exitosa");
		reg.agregarId(idTransaccion);
	}
}
