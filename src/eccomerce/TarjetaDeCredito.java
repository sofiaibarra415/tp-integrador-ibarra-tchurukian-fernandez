package eccomerce;
import java.time.YearMonth;
import java.time.Clock;

public class TarjetaDeCredito extends MetodoDePago {
	
	private String nroTarjeta;
	private String cvv;
	private YearMonth vencimiento;
	private Clock reloj;
	
	public TarjetaDeCredito(String numeroTarjeta, String cvv, YearMonth vencimiento, Clock reloj) {
		this.nroTarjeta = numeroTarjeta;
		this.cvv = cvv;
		this.vencimiento = vencimiento;
		this.reloj = reloj;
	}
	
	@Override
	public void validarDatos() {
		if (nroTarjeta == null || !nroTarjeta.matches("\\d{13,19}")) {
            throw new IllegalArgumentException("Número de tarjeta inválido.");
        }
		
		if (cvv == null || !cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV inválido.");
        }
		
		YearMonth fechaActual = YearMonth.now(reloj);
		if (vencimiento == null || vencimiento.isBefore(fechaActual)) {
            throw new IllegalArgumentException("Tarjeta expirada respecto a la fecha: " + fechaActual);
        }
	}
	
	@Override
	public void reservarFondos() {
		return;
	}
	
	@Override
	public void ejecutarTransaccion() {
		return;
	}
	
	@Override
	public void notificarResultado() {
		return;
	}
}