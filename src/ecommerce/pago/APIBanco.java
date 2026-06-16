package ecommerce.pago;

public interface APIBanco {
	//true si los datos son validos
	public boolean sonDatosValidos(String cbuOcvu, String alias);
	// devuelve la id de transaccion
	public String realizarTransferencia(String cbuOcvu, String alias, double monto);
}
