package ecommerce.pago;

public class TransferenciaBancaria extends MetodoDePago {
	
private APIBanco api;
//private RegistroPagos reg;
	
	public TransferenciaBancaria(APIBanco api, RegistroPagos reg) {
		this.api = api;
		this.reg = reg;
	}
	
	@Override
	protected boolean validarDatos(DatosPago datos, double monto) {
        if (!(datos instanceof DatosTransferenciaBancaria)) {
            throw new IllegalArgumentException("Datos inválidos para Transferencia Bancaria");
        }
        DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;
        return api.sonDatosValidos(dt.getCbuOcvu(), dt.getAlias());
    }

	@Override
	protected String reservarFondos(DatosPago datos, double monto) {
		System.out.println("No aplica reserva de fondos para transferencia directa.");
		return "No aplica";
	}

	@Override
	protected String ejecutarTransaccion(DatosPago datos, double monto, String idReserva) {
		DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;
		return api.realizarTransferencia(dt.getCbuOcvu(), dt.getAlias(), monto);
	}

	@Override
	protected void notificarResultado(DatosPago datos, double monto, String idTransaccion) {
		DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;
		reg.agregarComprobante(new ComprobanteCBU(dt.getCbuOcvu(), dt.getAlias(), monto, idTransaccion));
		reg.agregarId(idTransaccion);
	}
}