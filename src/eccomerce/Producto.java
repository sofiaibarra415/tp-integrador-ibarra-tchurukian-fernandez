package eccomerce;

import java.util.ArrayList;

public class Producto extends Item {

	private String 	sku;
	private String 	marca;
	private double 	precioBase;
	private int 	stock;
	private ArrayList<Atributo> atributos = new ArrayList<>();
	
	
	@Override
	protected void incrementarStock() {
		this.stock ++;
	}

	@Override
	protected void incrementarStockEn(int cantidad) {
		this.stock = this.stock + cantidad;
	}

	@Override
	protected void decrementarStock() {
		this.stock --;
	}

	@Override
	protected double getPrecioBase() {
		return this.precioBase;
	}

	@Override
	protected double getPrecioFinal() {
		return this.getPrecioBase() * ( 1 - this.getDescuentoPromocional() );
	}

	@Override
	protected boolean existeAtributoOpcional(String nombreAtributo) {
		
		return this.atributos.stream()
			    			 .anyMatch(atributo -> atributo.getNombre() == nombreAtributo);
	}

	@Override
	protected String getAtributoOpcional(String nombreAtributo) {
		return this.atributos.stream()
				             .filter(atributo -> atributo.getNombre().equals(nombreAtributo)) 	// filtro para encontrar el atributo
				             .findFirst()													  	// deberia haber uno solo, pero por las dudas elijo el primero
				             .get()															 	// me da el objeto, si noy falla, se deberia chequear que este el atributo con boolean existeAtributoOpcional(String nombreAtributo) antes de usar 
				             .getValor();														// me da el valor del atributo
		}
	
	@Override
	protected boolean esItemValido() {
		
		return	   this.getSKU() 		!= null //es un String si no esta cargado es null
				&& this.getCategoria() 	!= null //es un String si no esta cargado es null
				&& this.getNombre() 	!= null //es un String si no esta cargado es null
				&& this.getDescripcion()!= null //es un String si no esta cargado es null
				&& this.getMarca()		!= null //es un String si no esta cargado es null
				&& this.getPrecioBase() > 0;    //es el precio tiene que ser un valor positivo, ademas si no esta cargado java le asigna cero
				
	}


	
	protected String getSKU() {
		return this.sku;
	}
	
	protected String getMarca() {
		return this.marca;
	}

	@Override
	protected int getStock() {
		return this.stock;
	}

	@Override
	protected boolean hayStock() {
		return (this.stock > 0);
	}

	@Override
	protected double getDescuentoPromocional() {
		return super.getDescuentoPromocional();
	}

}
