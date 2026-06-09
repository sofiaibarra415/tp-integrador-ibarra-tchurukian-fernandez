package ecommerce.envio;

public class Sucursal {
    private String nombre;
    private boolean tieneStockLocal;
    
    public Sucursal(String nombre, boolean tieneStockLocal) {
        this.nombre = nombre;
        this.tieneStockLocal = tieneStockLocal;
    }
    
    public boolean tieneStockLocal() {
        return tieneStockLocal;
    }
}