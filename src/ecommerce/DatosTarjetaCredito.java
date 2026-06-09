package ecommerce;

import java.time.YearMonth;

public class DatosTarjetaCredito implements DatosPago {
    private final String numeroTarjeta;
    private final String cvv;
    private final YearMonth vencimiento;

    public DatosTarjetaCredito(String numeroTarjeta, String cvv, YearMonth vencimiento) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.vencimiento = vencimiento;
    }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public String getCvv() { return cvv; }
    public YearMonth getVencimiento() { return vencimiento; }
}