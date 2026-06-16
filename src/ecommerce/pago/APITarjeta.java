package ecommerce.pago;
import java.time.YearMonth;

public interface APITarjeta {
	
	boolean sonDatosValidos(String nroTarjeta, String cvv, YearMonth vencimiento);
	
    String preAutorizar(String nroTarjeta, String cvv, Double monto);
    
    String ejecutarTransaccion(String idPreAutorizacion); // se le envía la ID de pre autorización
}
