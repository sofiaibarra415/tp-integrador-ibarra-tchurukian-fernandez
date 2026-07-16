package ecommerce.pago;

public class TransferenciaBancaria extends MetodoDePago<DatosTransferenciaBancaria> {
	
private APIBanco api;
//private RegistroPagos reg;
	
	public TransferenciaBancaria(APIBanco api, RegistroPagos reg) {
		this.api = api;
		this.reg = reg;
	}
	
	@Override
	protected boolean validarDatos(DatosTransferenciaBancaria datos, double monto) {
        DatosTransferenciaBancaria dt = datos;
        return api.sonDatosValidos(dt.getCbuOcvu(), dt.getAlias());
    }

	@Override
	protected String reservarFondos(DatosTransferenciaBancaria datos, double monto) {
		System.out.println("No aplica reserva de fondos para transferencia directa.");
		return "No aplica";
	}

	@Override
	protected String ejecutarTransaccion(DatosTransferenciaBancaria datos, double monto, String idReserva) {
		DatosTransferenciaBancaria dt = datos;
		return api.realizarTransferencia(dt.getCbuOcvu(), dt.getAlias(), monto);
	}

	@Override
	protected void notificarResultado(DatosTransferenciaBancaria datos, double monto, String idTransaccion) {
		DatosTransferenciaBancaria dt = datos;
		reg.agregarComprobante(new ComprobanteCBU(dt.getCbuOcvu(), dt.getAlias(), monto, idTransaccion));
		reg.agregarId(idTransaccion);
	}
}