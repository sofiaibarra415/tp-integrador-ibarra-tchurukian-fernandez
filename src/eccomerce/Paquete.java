package eccomerce;

import java.util.ArrayList;

public class Paquete extends Item {
	
	private ArrayList<Item> items = new ArrayList<>();

	public Paquete(String nombre, String descripcion, String categoria, double descuentoPromocional,ArrayList<Item> items) {
		super(nombre, descripcion, categoria, descuentoPromocional);
		this.items = items;
	}

	@Override
	protected void incrementarStock() {
		this.items.forEach(item -> item.incrementarStock());
		// icrementa todos los productos y si son paquetes es recursiva
	}
	
	@Override
	protected void decrementarStock() {
		this.items.forEach(item -> item.decrementarStock());
		// decrementa todos los productos y si son paquetes es recursiva
	}

	@Override
	protected double getPrecioBase() {
		return this.items.stream()
						 .mapToDouble(item -> item.getPrecioBase())
						 .sum();
		//si son productos suma y si son paquetes entra recursivamente a cada paquete anidado
		//por lo que entendi los descuentos no se anidan
		//preguntar
		
	}


	@Override
	protected boolean esItemValido() {
		return	this.getCategoria() 	!= null //es un String si no esta cargado es null
				&& this.getNombre() 	!= null //es un String si no esta cargado es null
				&& this.getDescripcion()!= null //es un String si no esta cargado es null
				&& this.getDescuentoPromocional() >= 0  //el decuento tiene que ser cero o mas
				&& this.getDescuentoPromocional() < 100 //el descuento tiene que ser menor a cien
				&& this.getAtributos().stream().noneMatch(atributo -> atributo.getValor() == null)
				&& this.items.stream().allMatch(item -> item.esItemValido()); //recursion
	}


	@Override
	protected int getStock() {
		return this.items.stream()
					     .mapToInt(item -> item.getStock())
					     .min() //me da un optional int
					     .orElse(0); // por si el paquete esta vacio
		
	}

	@Override
	protected boolean hayStock() {
		return this.items.stream()
						 .allMatch(item -> item.hayStock());
		//recorro todo los productos y si es paquete es recursivo
	}

	@Override
	protected void agregarAtributo(String unNombre, String unValor) {
		
		if (unNombre.equalsIgnoreCase("peso")) {
			throw new IllegalArgumentException("En un Paquete el peso es la suma de sus Productos, no es un atributo seteable"); //error lanza excepcion
		}else {
			super.agregarAtributo(unNombre, unValor);
		}
	}

	@Override
	protected double getPeso() {
		
		return this.items.stream()
				         .mapToDouble(item -> item.getPeso())
				         .sum(); //recorro recursivamente para sumar los pesos de los productos
	}

	@Override
	protected boolean hayPeso() {
		
		return this.items.stream()
				 .allMatch(item -> item.hayPeso());
       //recorro todo los productos y si es paquete es recursivo;
	}


	
}
