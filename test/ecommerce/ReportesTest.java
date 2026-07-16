package ecommerce;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.catalogo1.Item;
import ecommerce.catalogo1.Paquete;
import ecommerce.catalogo1.Producto;
import ecommerce.reportes.ReporteCSVVisitor;
import ecommerce.reportes.ReporteHTMLVisitor;
import ecommerce.reportes.ReporteTextoPlanoVisitor;

import java.time.LocalDate;
import java.util.ArrayList;

class ReportesTest {

    private LocalDate inicio;
    private LocalDate fin;
    private Producto productoA;
    private Producto productoB;

    @BeforeEach
    void setUp() {
        inicio = LocalDate.now().minusDays(1);
        fin = LocalDate.now().plusDays(1);
        productoA = new Producto("ProductoA", "desc", "cat", 0, "SKU-A", "Marca", 100.0, 10, 1.0);
        productoB = new Producto("ProductoB", "desc", "cat", 0, "SKU-B", "Marca", 200.0, 10, 1.0);
    }

    // --- CSV ---

    @Test
    void reporteCSVContieneEncabezado() {
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicio, fin);
        productoA.accept(reporte);
        assertTrue(reporte.getResultado().contains("Nombre;UnidadesVendidas;PrecioPromedioCobrado"));
    }

    @Test
    void reporteCSVMuestraNombreYUnidadesDeUnProducto() {
        productoA.registrarVenta();
        productoA.registrarVenta();
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicio, fin);
        productoA.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.contains("ProductoA"));
        assertTrue(resultado.contains("2"));
    }
    
    @Test
    void reporteCSVMuestraPrecioPromedioDeProductoCorrectamente() {
        productoA.registrarVenta();
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicio, fin);
        productoA.accept(reporte);
        assertTrue(reporte.getResultado().contains("ProductoA;1;100.00"));
    }
    
    @Test
    void reporteCSVMuestraPrecioPromedioDePaqueteCorrectamente() {
    	ArrayList<Item> items = new ArrayList<>();
        items.add(productoA);
        items.add(productoB);
        Paquete paquete = new Paquete("PaqueteX", "desc", "cat", 0, items);
        paquete.registrarVenta();
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicio, fin);
        paquete.accept(reporte);
        assertTrue(reporte.getResultado().contains("PaqueteX;1;300.00"));
    }

    @Test
    void reporteCSVOrdenaPorMasVendido() {
        productoA.registrarVenta();
        productoB.registrarVenta();
        productoB.registrarVenta();
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicio, fin);
        productoA.accept(reporte);
        productoB.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.indexOf("ProductoB") < resultado.indexOf("ProductoA"));
    }

    @Test
    void reporteCSVFueraDeRangoMuestraCeroUnidades() {
        productoA.registrarVenta();
        LocalDate inicioViejo = LocalDate.now().minusDays(10);
        LocalDate finViejo = LocalDate.now().minusDays(5);
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicioViejo, finViejo);
        productoA.accept(reporte);
        assertTrue(reporte.getResultado().contains("ProductoA;0;0.00"));
    }

    @Test
    void reporteCSVConPaqueteMuestraNombreDelPaquete() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(productoA);
        Paquete paquete = new Paquete("PaqueteX", "desc", "cat", 0, items);
        paquete.registrarVenta();
        ReporteCSVVisitor reporte = new ReporteCSVVisitor(inicio, fin);
        paquete.accept(reporte);
        assertTrue(reporte.getResultado().contains("PaqueteX"));
    }

    // --- Texto Plano ---

    @Test
    void reporteTextoContieneEncabezado() {
        ReporteTextoPlanoVisitor reporte = new ReporteTextoPlanoVisitor(inicio, fin);
        productoA.accept(reporte);
        assertTrue(reporte.getResultado().contains("REPORTE DE MÁS VENDIDOS (TEXTO PLANO)"));
    }

    @Test
    void reporteTextoMuestraNombreYUnidadesDeUnProducto() {
        productoA.registrarVenta();
        ReporteTextoPlanoVisitor reporte = new ReporteTextoPlanoVisitor(inicio, fin);
        productoA.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.contains("ProductoA"));
        assertTrue(resultado.contains("Unidades: 1"));
    }

    @Test
    void reporteTextoOrdenaPorMasVendido() {
        productoA.registrarVenta();
        productoB.registrarVenta();
        productoB.registrarVenta();
        ReporteTextoPlanoVisitor reporte = new ReporteTextoPlanoVisitor(inicio, fin);
        productoA.accept(reporte);
        productoB.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.indexOf("ProductoB") < resultado.indexOf("ProductoA"));
    }

    @Test
    void reporteTextoSinVentasMuestraCeroUnidades() {
        ReporteTextoPlanoVisitor reporte = new ReporteTextoPlanoVisitor(inicio, fin);
        productoA.accept(reporte);
        assertTrue(reporte.getResultado().contains("Unidades: 0"));
    }
    
    @Test
    void reporteTextoMuestraPrecioPromedioDePaqueteCorrectamente() {
    	ArrayList<Item> items = new ArrayList<>();
        items.add(productoA);
        items.add(productoB);
        Paquete paquete = new Paquete("PaqueteX", "desc", "cat", 0, items);
        paquete.registrarVenta();
        ReporteTextoPlanoVisitor reporte = new ReporteTextoPlanoVisitor(inicio, fin);
        paquete.accept(reporte);
        System.out.println(reporte.getResultado());
        assertTrue(reporte.getResultado().contains("Precio Promedio Cobrado: $300.00"));
    }

    // --- HTML ---

    @Test
    void reporteHTMLContieneEtiquetasHTML() {
        ReporteHTMLVisitor reporte = new ReporteHTMLVisitor(inicio, fin);
        productoA.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.contains("<html>"));
        assertTrue(resultado.contains("</html>"));
        assertTrue(resultado.contains("<table"));
    }

    @Test
    void reporteHTMLContieneEncabezadoDeTabla() {
        ReporteHTMLVisitor reporte = new ReporteHTMLVisitor(inicio, fin);
        productoA.accept(reporte);
        assertTrue(reporte.getResultado().contains("Unidades Vendidas"));
    }

    @Test
    void reporteHTMLMuestraNombreYUnidades() {
        productoA.registrarVenta();
        ReporteHTMLVisitor reporte = new ReporteHTMLVisitor(inicio, fin);
        productoA.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.contains("ProductoA"));
        assertTrue(resultado.contains("<td>1</td>"));
    }

    @Test
    void reporteHTMLOrdenaPorMasVendido() {
        productoA.registrarVenta();
        productoB.registrarVenta();
        productoB.registrarVenta();
        ReporteHTMLVisitor reporte = new ReporteHTMLVisitor(inicio, fin);
        productoA.accept(reporte);
        productoB.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.indexOf("ProductoB") < resultado.indexOf("ProductoA"));
    }
    
    @Test
    void reporteHTMLMuestraPrecioPromedioCorrectamente() {
    	ArrayList<Item> items = new ArrayList<>();
        items.add(productoA);
        items.add(productoB);
        Paquete paquete = new Paquete("PaqueteX", "desc", "cat", 0, items);
        paquete.registrarVenta();
        ReporteHTMLVisitor reporte = new ReporteHTMLVisitor(inicio, fin);
        paquete.accept(reporte);
        String resultado = reporte.getResultado();
        assertTrue(resultado.contains("<td>PaqueteX</td>"));
        assertTrue(resultado.contains("<td>$300.00</td>"));
    }
}
