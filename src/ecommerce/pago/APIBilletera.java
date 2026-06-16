package ecommerce.pago;

public interface APIBilletera {
	//true si hay saldo suficiente
	boolean saldoSuficienteEnLaCuenta(String alias, double saldo);
	//devuelve idReservaSaldo
	String bloquearSaldoDeLaCuenta(String alias, double saldo);
	//devuelve id de transacción
	String confirmarTransaccion(String alias, String idReservaSaldo);
	
	void push(String mensaje);
}
