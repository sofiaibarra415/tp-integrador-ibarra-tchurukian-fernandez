package ecommerce;

import java.math.BigDecimal;

public abstract class MetodoDePago {

	public final void procesarPago(DatosPago datos, BigDecimal monto) {
		
        validarDatos(datos);
        
        reservarFondos(monto);
        
        ejecutarTransaccion(monto);
        
       // notificarResultado(idTransaccion); ejecutarTransacción() llama esta función
    }
	
	protected abstract void validarDatos(DatosPago datos);
	
	protected abstract void reservarFondos(BigDecimal monto);
	
	protected abstract void ejecutarTransaccion(BigDecimal monto);
	
	protected abstract void notificarResultado(String idTransaccion);
}