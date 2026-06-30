package ecommerce.catalogo1;

import java.util.ArrayList;

import ecommerce.reportes.ItemVisitor;

abstract public class Item {
	
	private String 	nombre;
	private String 	descripcion;
	private String	categoria;
	private double 	descuentoPromocional;
	private ArrayList<Atributo> atributos = new ArrayList<>();
	private ArrayList<Venta> ventasRegistradas = new ArrayList<>();
		

public Item(String nombre, String descripcion, String categoria, double descuentoPromocional) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuentoPromocional = descuentoPromocional;
	}

	abstract public void 	incrementarStock();
	abstract public void 	decrementarStock();
	abstract public double 	getPrecioBase();
	abstract public boolean esItemValido();
	abstract public int		getStock();
	abstract public boolean	hayStock();
	abstract public boolean hayPeso();
	abstract public double 	getPeso();

	public String getNombre() {
		return this.nombre;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public double getDescuentoPromocional() {
		return this.descuentoPromocional;
	}
	
	public double getPrecioFinal() {
		return this.getPrecioBase() * ( 1 - this.getDescuentoPromocional() / 100 );
	}

	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

	public void agregarAtributo(String unNombre, String unValor) {
		if (this.existeAtributoOpcional(unNombre)) {
			throw new IllegalArgumentException("El atributo ya existe"); //error lanza excepcion
		}
		this.atributos.add(new Atributo(unNombre, unValor)) ;
		// si no existe se crea un atributo y se agrega a la lista de atributos dinamicos
	
	}

	public boolean existeAtributoOpcional(String nombreAtributo) {
	
		return this.getAtributos().stream()
								  .anyMatch(atributo -> atributo.getNombre()
								  .equalsIgnoreCase(nombreAtributo) );
	}

	public String getAtributoOpcional(String nombreAtributo) {
		if ( ! this.existeAtributoOpcional(nombreAtributo)) {
			throw new IllegalArgumentException("El atributo no existe"); //error lanza excepcion
		}else {
			return this.getAtributos().stream()
			             .filter(atributo -> atributo.getNombre().equalsIgnoreCase(nombreAtributo) ) // filtro para encontrar el atributo
			             .findFirst()		// deberia haber uno solo, pero por las dudas elijo el primero
			             .get()				// me da el objeto, no falla ya cheque que exista antes
			             .getValor();		// me da el valor del atributo
		}
	}


	public ArrayList<Venta> getVentasRegistradas() {
		return this.ventasRegistradas;
		}

	public void agregarRegistroVenta(Venta unaVenta) {
		this.ventasRegistradas.add(unaVenta);
	}

	abstract public void registrarVentaConFactor(double factorAcumulado);

	public void registrarVenta() {
		this.registrarVentaConFactor(1.0); //arranco con un factor uno
	}

	abstract public void accept(ItemVisitor visitor);
	
}
