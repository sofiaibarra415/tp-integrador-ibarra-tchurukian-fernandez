package eccomerce;

import java.math.BigDecimal;

public class BilleteraVirtual extends MetodoDePago {

	@Override
	protected void validarDatos(DatosPago datos) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void reservarFondos(BigDecimal monto) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void ejecutarTransaccion(BigDecimal monto) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void notificarResultado(String idTransaccion) {
		// TODO Auto-generated method stub
		//cashback = reintegro
	}

}
