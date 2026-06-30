package ecommerce.reportes8;

import ecommerce.catalogo1.Item;
import ecommerce.catalogo1.Paquete;
import ecommerce.catalogo1.Producto;
import ecommerce.catalogo1.Venta;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ItemVisitor {
    protected LocalDate inicio;
    protected LocalDate fin;
    
    private Map<Item, Integer> unidadesVendidas = new HashMap<>();
    private Map<Item, Double>  totalRecaudado = new HashMap<>();

    public ItemVisitor(LocalDate inicio, LocalDate fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public void visit(Producto producto) {
        procesarItem(producto);// double dispatch del visitor
    }

    public void visit(Paquete paquete) {
        procesarItem(paquete); // double dispatch del visitor
    }

    //calculo lo que nesesito
    private void procesarItem(Item item) {
        List<Venta> ventasPeriodo = item.getVentasRegistradas().stream()
        		.filter(v -> v.getFecha().isBefore(fin) && v.getFecha().isAfter(inicio))
                .collect(Collectors.toList());//filtro por fecha las ventas

            int cantidad = ventasPeriodo.size();//cero si esta vacio
            double monto = ventasPeriodo.stream()
            							.mapToDouble(Venta::getPrecioCobrado)
            							.sum();//cero si esta vacio

            unidadesVendidas.put(item,cantidad);
            totalRecaudado.put(item, monto);
            //las agrego en los diccionarios
      }

    public abstract String getResultado();

    //ordeno los items por sus ventas en el periodo
    protected List<Item> getItemsOrdenados() {
        List<Item> listaItems = new ArrayList<>(unidadesVendidas.keySet());
        
        listaItems.sort((item1, item2) -> Integer.compare(unidadesVendidas.get(item2), unidadesVendidas.get(item1)));
        
        return listaItems;
    }

    protected int getUnidadesDe(Item item) {
        return unidadesVendidas.getOrDefault(item, 0);
    }

    protected double getPrecioPromedioDe(Item item) {
        int unidades = unidadesVendidas.getOrDefault(item, 0);
        if (unidades == 0) return 0.0;//no dividir por cero
        return totalRecaudado.getOrDefault(item, 0.0) / unidades;
    }
}
