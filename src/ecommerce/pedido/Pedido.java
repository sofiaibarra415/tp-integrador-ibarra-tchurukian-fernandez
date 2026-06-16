package ecommerce.pedido;
import java.util.List;
import java.util.ArrayList;

import ecommerce.Item;
import ecommerce.envio.MetodoEnvio;
import ecommerce.notificaciones.ObserverPedido;

import java.time.LocalDate;

public class Pedido {
	private String id;
	private List<Item> items = new ArrayList<>();
	private EstadoPedido estado;
	private MetodoEnvio envio;
	private NotaDeCredito notaDeCredito;
	private List<ObserverPedido> observadores = new ArrayList<>();
	
	public Pedido(String id){
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
		EstadoPedido anterior = this.estado;
	    this.estado = e;
	    notificar(anterior, e);
	}
	
	public EstadoPedido getEstado() {
	    return estado;
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
	
	public void generarNotaDeCredito(double monto) {
	    this.notaDeCredito = new NotaDeCredito(this.id, monto, LocalDate.now());
	}

	public NotaDeCredito getNotaDeCredito() {
	    return notaDeCredito;
	}
	
	public double calcularPeso() {
	    double total = 0;
	    for (Item item : items) {
	        total += item.getPeso();
	    }
	    return total;
	}
	
	public void setMetodoEnvio(MetodoEnvio envio) {
	    this.envio = envio;
	}

	public boolean tieneMetodoEnvio() {
	    return envio != null;
	}
	
	public double calcularCostoEnvio() {
	    return envio.calcularCosto(this);
	}
	
	public void suscribir(ObserverPedido o) {
	    this.observadores.add(o);
	}

	public void desuscribir(ObserverPedido o) {
		this.observadores.remove(o);
	}

	private void notificar(EstadoPedido anterior, EstadoPedido nuevo) {
	   for (ObserverPedido o : this.observadores) {
		   o.actualizar(anterior, nuevo);
	   }
	}
			
}
