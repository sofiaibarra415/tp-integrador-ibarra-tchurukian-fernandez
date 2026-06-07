package eccomerce;


public class Producto extends Item {

	private String 	marca;
	private double 	precioBase;
	private int 	stock;
	private String 	sku;
	
	public Producto(String nombre, String descripcion, String categoria, double descuentoPromocional, String sku,
			String marca, double precioBase, int stock) {
		super(nombre, descripcion, categoria, descuentoPromocional);
		this.sku = sku;
		this.marca = marca;
		this.precioBase = precioBase;
		this.stock = stock;
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
				&& this.getAtributos().stream().noneMatch(atributo -> atributo.getValor() == null);  //cheque que ningun atributo dinamico tiene valor nulo     
				
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
		return super.getPeso();
	}

	@Override
	public boolean hayPeso() {
		
		return this.getPeso() > 0;
	}

}
