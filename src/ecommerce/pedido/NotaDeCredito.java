package ecommerce.pedido;
import java.time.LocalDate;

public class NotaDeCredito {
	private String idPedido;
    private double monto;
    private LocalDate fecha;
    
    public NotaDeCredito(String idPedido, double monto, LocalDate fecha) {
    	this.idPedido = idPedido;
    	this.monto = monto;
    	this.fecha = fecha;
    }
    
    public String getIdPedido() {
    	return idPedido; 
    }
    public double getMonto()    { 
    	return monto; 
    }
    public LocalDate getFecha() { 
    	return fecha; 
    }
}
