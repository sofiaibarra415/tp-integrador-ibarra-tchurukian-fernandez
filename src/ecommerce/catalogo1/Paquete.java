package ecommerce.catalogo1;

import java.util.ArrayList;

import ecommerce.reportes8.ItemVisitor;

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
						 .mapToDouble(item -> item.getPrecioFinal())
						 .sum();
		//si son productos suma y si son paquetes entra recursivamente a cada paquete anidado
		
		
	}


	@Override
	public boolean esItemValido() {
		return	this.getCategoria() 	!= null //es un String si no esta cargado es null
				&& this.getNombre() 	!= null //es un String si no esta cargado es null
				&& this.getDescripcion()!= null //es un String si no esta cargado es null
				&& this.getDescuentoPromocional() >= 0  //el decuento tiene que ser cero o mas
				&& this.getDescuentoPromocional() < 100 //el descuento tiene que ser menor a cien
				&& this.getAtributos().stream().noneMatch(atributo -> atributo.getValor() == null)
				&& this.items.stream().allMatch(item -> item.esItemValido()) //recursion
				&& !this.items.isEmpty(); //si esta vacio el paquete no es valido
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
		if (!this.hayPeso()) {
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
	
	@Override
	public void registrarVentaConFactor(double factorAcumulado) {
	
	
	double factorEstePaquete = (1 - this.getDescuentoPromocional() / 100);
	//calculo el factor de descuento de este paquete
	//o sea si el descuento es 10 el factor queda en 0.9
	
	
	double nuevoFactorAcumulado = factorAcumulado * factorEstePaquete;
	//multiplico el factor de arriba por el propio para anidar los descuentos
	
	
	this.items.forEach(item -> item.registrarVentaConFactor(nuevoFactorAcumulado));
	//le mando el nuevo factor a los item de este paquete
	//si es un paquete el item estonces es recursivo
	
	double precioProporcionalPaquete = this.getPrecioFinal() * factorAcumulado;
	Venta ventaPaquete = new Venta(precioProporcionalPaquete);
	this.agregarRegistroVenta(ventaPaquete);
	//registro la venta de este paquete con su precio proporcianal
	}
	
	@Override
	public void accept(ItemVisitor visitor) {
	    visitor.visit(this); // voy a visit(Producto)
	    					 //mitad de camino del double dispatch
	}
	
}
