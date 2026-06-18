package ecommerce.items;

import java.util.ArrayList;

public class Paquete extends Item {
	
	private ArrayList<Item> items = new ArrayList<>();

public Paquete(String nombre, String descripcion, String categoria, double descuentoPromocional,ArrayList<Item> items) {
		super(nombre, descripcion, categoria, descuentoPromocional);
		this.items = items;
	}

	@Override
	public void incrementarStock() {
		this.items.forEach(item -> item.incrementarStock());
		// icrementa todos los productos y si son paquetes es recursiva
	}
	
	@Override
	public void decrementarStock() {
		this.items.forEach(item -> item.decrementarStock());
		// decrementa todos los productos y si son paquetes es recursiva
	}

	@Override
	public double getPrecioBase() {
		return this.items.stream()
						 .mapToDouble(item -> item.getPrecioBase())
						 .sum();
		//si son productos suma y si son paquetes entra recursivamente a cada paquete anidado
		//por lo que entendi los descuentos no se anidan
		//preguntar
		
	}


	@Override
	public boolean esItemValido() {
		return	this.getCategoria() 	!= null //es un String si no esta cargado es null
				&& this.getNombre() 	!= null //es un String si no esta cargado es null
				&& this.getDescripcion()!= null //es un String si no esta cargado es null
				&& this.getDescuentoPromocional() >= 0  //el decuento tiene que ser cero o mas
				&& this.getDescuentoPromocional() < 100 //el descuento tiene que ser menor a cien
				&& this.getAtributos().stream().noneMatch(atributo -> atributo.getValor() == null)
				&& this.items.stream().allMatch(item -> item.esItemValido()); //recursion
	}


	@Override
	public int getStock() {
		return this.items.stream()
					     .mapToInt(item -> item.getStock())//si corresponde es recursivo
					     .min() //me da un optional int
					     .orElse(0); // por si el paquete esta vacio
		//el stock de un paquete esta dado por el producto con menos stock que contenga
		
	}

	@Override
	public boolean hayStock() {
		return this.items.stream()
						 .allMatch(item -> item.hayStock());
		//recorro todo los productos y si es paquete es recursivo
	}

	
	@Override
	public double getPeso() {
		if (this.hayPeso()) {
			throw new IllegalArgumentException("No hay peso"); //error lanza excepcion
		}
		
		return this.items.stream()
				         .mapToDouble(item -> item.getPeso())
				         .sum(); //recorro recursivamente para sumar los pesos de los productos
	}

	@Override
	public boolean hayPeso() {
		
		return this.items.stream()
				 .allMatch(item -> item.hayPeso());
       //recorro todo los productos y si es paquete es recursivo
		// solo si todos los procuctos tienen peso entonces el paquete tiene precio
	}


	
}
