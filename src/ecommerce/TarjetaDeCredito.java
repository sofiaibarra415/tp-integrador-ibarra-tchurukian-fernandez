package ecommerce;
import java.time.YearMonth;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TarjetaDeCredito extends MetodoDePago {
	
	private Clock reloj;
	
	public TarjetaDeCredito(Clock reloj) {
		this.reloj = reloj;
	}
	@Override
	protected void validarDatos(DatosPago datos) {
		if (!(datos instanceof DatosTarjetaCredito)) {
            throw new IllegalArgumentException("Datos inválidos para Tarjeta de Crédito");
        }
		DatosTarjetaCredito dt = (DatosTarjetaCredito) datos;
		// valida el numero de la tarjeta esté entre 13 y 19 dígitos y que use números del 0 al 9
		if (dt.getNumeroTarjeta() == null || !dt.getNumeroTarjeta().matches("\\d{13,19}")) {
            throw new IllegalArgumentException("Número de tarjeta inválido.");
        }
		// valida que el cvv sea entre 3 y 4 dígitos
		if (dt.getCvv() == null || !dt.getCvv().matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV inválido.");
        }
		// que la tarjeta no esté vencida
		YearMonth fechaActual = YearMonth.now(reloj);
		if (dt.getVencimiento() == null || dt.getVencimiento().isBefore(fechaActual)) {
            throw new IllegalArgumentException("Tarjeta expirada respecto a la fecha: " + fechaActual);
        }
	}
	
	@Override
	protected void reservarFondos(BigDecimal monto) {
		// nada más hace un print
		System.out.println("Pre-autorizando " + monto + " al banco emisor...");
	}
	
	@Override
	protected void ejecutarTransaccion(BigDecimal monto) {
		// hace un print y llama a la función de notificación
		String idTransaccion = "" + System.currentTimeMillis();
        System.out.println("Débito diferido de " + monto + ".");
        notificarResultado(idTransaccion);
	}
	
	@Override
	protected void notificarResultado(String idTransaccion) {
		LocalDateTime fechaActual = LocalDateTime.now(reloj);
		
		System.out.println("CUPÓN DE PAGO");
		System.out.println("ID Transacción: " + idTransaccion);
		System.out.println("Fecha: " + fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	}
}