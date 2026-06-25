package ecommerce.pago;

import java.util.ArrayList;
import java.util.List;

public class RegistroPagos {
	private final List<ComprobantePago> comprobantes;
	private final List<String> idsTransaccion;
	
	public RegistroPagos() {
        this.comprobantes = new ArrayList<>(); //los comprobantes cbu y cupon
        this.idsTransaccion = new ArrayList<>(); //TODAS las id de transaccion
    }

    public void agregarComprobante(ComprobantePago comprobante) {
        comprobantes.add(comprobante);
    }
    
    public int getCantidadComprobantes() {
        return comprobantes.size();
    }
    
    public void agregarId(String idTransaccion) {
    	idsTransaccion.add(idTransaccion);
    }
    
    public int getCantidadIds() {
        return idsTransaccion.size();
    }
}