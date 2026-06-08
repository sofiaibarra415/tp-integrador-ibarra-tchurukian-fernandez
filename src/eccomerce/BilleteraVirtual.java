package eccomerce;

import java.math.BigDecimal;

public class BilleteraVirtual extends MetodoDePago {
	
	private BigDecimal saldo;
	
	@Override
	protected void validarDatos(DatosPago datos) {
		if (!(datos instanceof DatosBilleteraVirtual)) {
            throw new IllegalArgumentException("Datos inválidos para Billetera Virtual");
        }
		DatosBilleteraVirtual dt = (DatosBilleteraVirtual) datos;
		
		this.saldo = dt.getSaldo();
		
	}

	@Override
	protected void reservarFondos(BigDecimal monto) {
		System.out.println("Saldo de " + monto + " bloqueado hasta realizar transferencia.");

	}

	@Override
	protected void ejecutarTransaccion(BigDecimal monto) {
		// no acredita ningún saldo a la tienda por ahora, cambiar cuando se implemente
		System.out.println("Se ha recibido " + monto + " exitosamente.");
		String idTransaccion = "" + System.currentTimeMillis();
		notificarResultado(idTransaccion);
	}

	@Override
	protected void notificarResultado(String idTransaccion) {
		// no realiza la notificación push ni el reintegro, cambiar cuando se implementen
		System.out.println("Transacción realizada correctamente. Se ha recibido " 
				+ this.saldo + " exitosamente. Número de transacción: " + idTransaccion);
		//cashback = reintegro
	}

}
