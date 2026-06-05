package eccomerce;

public class Paquete extends Item {

	@Override
	protected void incrementarStock() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void incrementarStockEn(int cantidad) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void decrementarStock() {
		// TODO Auto-generated method stub

	}

	@Override
	protected double getPrecioBase() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double getPrecioFinal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean existeAtributoOpcional(String nombreAtributo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getAtributoOpcional(String nombreAtributo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean esItemValido() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	protected String getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean hayStock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected double getDescuentoPromocional() {
		// tiene que ser distinta a la superclase porque los descuentos no se suman o si se suman preguntar
		return 0;
	}

}
