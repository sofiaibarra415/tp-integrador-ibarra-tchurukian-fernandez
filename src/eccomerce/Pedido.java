package eccomerce;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
	private String id;
	private List<Item> items = new ArrayList<>();
	private EstadoPedido estado;
	private double montoReembolsado;
	
	Pedido(String id){
		this.id = id;
		this.estado = new Borrador(this);
	}
	
	public void confirmar() {
		estado.confirmar();
	}
	public void cancelar() {
		estado.cancelar();
	}
	public void iniciarPreparacion() { 
		estado.iniciarPreparacion();
	}
	public void enviar() { 
		estado.enviar(); 
	}
	public void entregar() { 
		estado.entregar(); 
	}
	public void agregarItem(Item i) { 
		estado.agregarItem(i);
	}
	public void quitarItem(Item i) { 
		estado.quitarItem(i); 
	}
	public void setEstado(EstadoPedido e) { 
		this.estado = e; 
	}
	public List<Item> getItems() { 
		return items;
	}

	public double calcularTotal() {
		double total = 0;
	    for (Item item : items) {
	        total += item.getPrecioFinal();
	    }
	    return total;
	}
	
	void addItem(Item i)    { 
		items.add(i);
	}
	void removeItem(Item i) { 
		items.remove(i); 
	}
	
	public void reembolsar(double monto) {
	    this.montoReembolsado = monto;
	}

	public double getMontoReembolsado() {
	    return montoReembolsado;
	}

	public double calcularCostoEnvio() {
	    return 0;
	}
			
}
