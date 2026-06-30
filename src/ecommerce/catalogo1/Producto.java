package ecommerce.catalogo1;

import ecommerce.reportes8.ItemVisitor;

public class Producto extends Item {

	private String 	marca;
	private double 	precioBase;
	private int 	stock;
	private String 	sku;
	private double peso;
	
	public Producto(String nombre, String descripcion, String categoria, double descuentoPromocional, String sku,
			String marca, double precioBase, int stock,double peso) {
		super(nombre, descripcion, categoria, descuentoPromocional);
		this.sku = sku;
		this.marca = marca;
		this.precioBase = precioBase;
		this.stock = stock;
		this.peso = peso;
	}	
	
	@Override
	public void incrementarStock() {
		this.stock ++;
	}

	
	public void incrementarStockEn(int cantidad) {
		this.stock = this.stock + cantidad;
	}

	@Override
	public void decrementarStock() {
		this.stock --;
	}

	@Override
	public double getPrecioBase() {
		return this.precioBase;
	}

	
	@Override
	public boolean esItemValido() {
		
		return	   this.getSKU() 		!= null //es un String si no esta cargado es null
				&& this.getCategoria() 	!= null //es un String si no esta cargado es null
				&& this.getNombre() 	!= null //es un String si no esta cargado es null
				&& this.getDescripcion()!= null //es un String si no esta cargado es null
				&& this.getMarca()		!= null //es un String si no esta cargado es null
				&& this.getPrecioBase() > 0     //es el precio tiene que ser un valor positivo, ademas si no esta cargado java le asigna cero
				&& this.getStock() 		>= 0	//el stock no puede ser negativo, pero si cero
				&& this.getDescuentoPromocional() >= 0  //el decuento tiene que ser cero o mas
				&& this.getDescuentoPromocional() < 100 //el descuento tiene que ser menor a cien
				&& this.getAtributos().stream().noneMatch(atributo -> atributo.getValor() == null)//cheque que ningun atributo dinamico tiene valor nulo 
				&& this.hayPeso();      
				
	}

	
	public String getSKU() {
		return this.sku;
	}
	
	public String getMarca() {
		return this.marca;
	}

	@Override
	public int getStock() {
		return this.stock;
	}

	@Override
	public boolean hayStock() {
		return (this.stock > 0);
	}

		
	@Override
	public double getPeso() {
		if (!this.hayPeso()) {
			throw new IllegalArgumentException("No hay peso"); //error lanza excepcion
		}
		return peso;
	}

	@Override
	public boolean hayPeso() {
		
		return peso > 0;
	}
	
	@Override
	public void registrarVentaConFactor(double factorAcumulado) {
		
	double precioProporcionalFinal = this.getPrecioFinal() * factorAcumulado;
	//calculo el precio proporcional con los descuentos acumulados de las capas superiores
	//si entro aca de una porque la venta era un producto y no un paquete
	//el factorAcumulado es uno y no cambia precio
		
	Venta nuevaVenta = new Venta(precioProporcionalFinal);
	this.agregarRegistroVenta(nuevaVenta);
	}

	@Override
	public void accept(ItemVisitor visitor) {
	    visitor.visit(this); // voy a visit(Producto)
	    					 //mitad de camino del double dispatch
	}
}
