package ecommerce.pago;

import java.time.YearMonth;

public class DatosTarjetaCredito implements DatosPago {
    private String numeroTarjeta;
    private String cvv;
    private YearMonth vencimiento;

    public DatosTarjetaCredito(String numeroTarjeta, String cvv, YearMonth vencimiento) {
    	if (!numeroTarjeta.matches("\\d{13,19}")) {
            throw new IllegalArgumentException("Número de tarjeta inválido.");
    	}
    	else { this.numeroTarjeta = numeroTarjeta; }
    	if (!cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV inválido.");
        }
    	else { this.cvv = cvv; }
        this.vencimiento = vencimiento;
    }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public String getCvv() { return cvv; }
    public YearMonth getVencimiento() { return vencimiento; }
}