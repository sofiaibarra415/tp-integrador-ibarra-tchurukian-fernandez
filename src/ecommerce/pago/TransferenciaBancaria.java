package ecommerce.pago;

public class TransferenciaBancaria extends MetodoDePago {
	
private APIBanco api;
private RegistroPagos reg;
	
	public TransferenciaBancaria(APIBanco api, RegistroPagos reg) {
		this.api = api;
		this.reg = reg;
	}
	
	@Override
	protected boolean validarDatos(DatosPago datos) {
        if (!(datos instanceof DatosTransferenciaBancaria)) {
            throw new IllegalArgumentException("Datos inválidos para Transferencia Bancaria");
        }
        DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;
        return api.sonDatosValidos(dt.getCbuOcvu(), dt.getAlias());
    }

	@Override
	protected String reservarFondos(double monto, DatosPago datos) {
		System.out.println("No aplica reserva de fondos para transferencia directa.");
		return "No aplica";
	}

	@Override
	protected String ejecutarTransaccion(DatosPago datos, String idReserva, double monto) {
		DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;
		return api.realizarTransferencia(dt.getCbuOcvu(), dt.getAlias(), monto);
	}

	@Override
	protected void notificarResultado(String idTransaccion, DatosPago datos, double monto) {
		DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;
		reg.agregarComprobante(new ComprobanteCBU(dt.getCbuOcvu(), dt.getAlias(), monto, idTransaccion));
		reg.agregarId(idTransaccion);
	}
}