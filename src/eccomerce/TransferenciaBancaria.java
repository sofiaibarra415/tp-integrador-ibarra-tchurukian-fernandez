package eccomerce;

import java.math.BigDecimal;

public class TransferenciaBancaria extends MetodoDePago {

	private String cbuCvu;
	private String alias;
	
	@Override
	protected void validarDatos(DatosPago datos) {
        if (!(datos instanceof DatosTransferenciaBancaria)) {
            throw new IllegalArgumentException("Datos inválidos para Transferencia Bancaria");
        }
        
        DatosTransferenciaBancaria dt = (DatosTransferenciaBancaria) datos;

        // valida que CBU/CVU y alias no sean vacíos
        this.cbuCvu = dt.getCbuOcvu();
        this.alias = dt.getAlias();

        if ((cbuCvu == null || cbuCvu.trim().isEmpty()) && 
            (alias == null || alias.trim().isEmpty())) {
            throw new IllegalArgumentException("Debe proporcionar un CBU/CVU y un Alias válido.");
        }

        // valida formato de CBU/CVU
        if (cbuCvu != null && !cbuCvu.trim().isEmpty()) {
            if (!cbuCvu.matches("\\d{22}")) {
                throw new IllegalArgumentException("CBU/CVU inválido: debe contener exactamente 22 dígitos numéricos.");
            }
        }

        // valida formato del alias, debe tener entre 6 y 20 caracteres alfanuméricos
        if (alias != null && !alias.trim().isEmpty()) {
            if (!alias.matches("^[a-zA-Z0-9.-]{6,20}$")) {
                throw new IllegalArgumentException("Alias inválido: debe tener entre 6 y 20 caracteres alfanuméricos.");
            }
        }
    }

	@Override
	protected void reservarFondos(BigDecimal monto) {
		System.out.println("No aplica reserva de fondos para transferencia directa.");

	}

	@Override
	protected void ejecutarTransaccion(BigDecimal monto) {
		// aun no agrega dinero a la tienda, solo hace un print por ahora
		String idTransaccion = "" + System.currentTimeMillis();
		System.out.println("Transferencia de " + monto + " recibida. ID Operación: " + idTransaccion);
		notificarResultado(idTransaccion);
	}

	@Override
	protected void notificarResultado(String idTransaccion) {
		// cambiar esto, ver si hay que hacer una clase "Comprobante"
		System.out.println("COMPROBANTE DE TRANSFERENCIA");
	    System.out.println("Estado: EXITOSA");
	    System.out.println("ID Operación: " + idTransaccion);
	    System.out.println("CBU/CVU: " + this.cbuCvu);
	    System.out.println("Alias: " + this.alias);
	}

}
