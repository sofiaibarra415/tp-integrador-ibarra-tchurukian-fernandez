package ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.busqueda6.CriterioDeBusqueda;
import ecommerce.busqueda6.CriterioDeBusquedaCompuestoAND;
import ecommerce.busqueda6.CriterioDeBusquedaCompuestoNOT;
import ecommerce.busqueda6.CriterioDeBusquedaCompuestoOR;
import ecommerce.busqueda6.CriterioDeBusquedaSimpleCategoria;
import ecommerce.busqueda6.CriterioDeBusquedaSimpleDisponibilidad;
import ecommerce.busqueda6.CriterioDeBusquedaSimpleNombre;
import ecommerce.busqueda6.CriterioDeBusquedaSimplePrecioMaximo;
import ecommerce.catalogo1.Catalogo;
import ecommerce.catalogo1.Item;
import ecommerce.catalogo1.Producto;
import ecommerce.catalogo1.Paquete;


class BusquedaTest {

    private Catalogo catalogoDeUNQShop;
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;
    private Paquete  paquete1;
    ArrayList<Item> lista = new ArrayList<>();
    
    

    @BeforeEach
    void setUp() {
        catalogoDeUNQShop = new Catalogo("Catalogo UNQ-Shop");
        lista = new ArrayList<>();
        
        producto1 = new Producto(
                "Auriculares",           // nombre 
                "Auriculares wireless",  // descripcion 
                "Electronica",           // categoria
                5.0,                     // descuento
                "ABC-123",               // sku
                "Sony",                  // marca
                120.0,                   // precio base
                1,                       // stock
                20.0                     // peso
            );

        producto2 = new Producto(
                "Auriculares",           // nombre 
                "Auriculares mini plug", // descripcion 
                "Electronica",           // categoria
                5.0,                     // descuento
                "ABC-124",               // sku
                "Bose",                  // marca
                80.0,                    // precio base
                10,                      // stock
                20.0                     // peso
            );
        producto3 = new Producto(
                "Zapatilla",             // nombre 
                "Zapatilla runner",      // descripcion 
                "Deportes",              // categoria
                10.0,                    // descuento
                "ABC-125",               // sku
                "Nike",                  // marca
                1200.0,                  // precio base
                0,                       // stock
                20.0                     // peso
            );
        
        lista.add(producto1);
        lista.add(producto2);
        
        paquete1 = new Paquete(
        		"Combo auriculares",	//nombre
				"Con y sin cable",  	//descripsion
				"Electronica",			//categoria
				10.0,					//descuento
				lista);		
        
        catalogoDeUNQShop.agregarItem(paquete1);
        catalogoDeUNQShop.agregarItem(producto1);
        catalogoDeUNQShop.agregarItem(producto2);
        catalogoDeUNQShop.agregarItem(producto3);
           
        
    }

    @Test
    void test001_Busqueda_Simple_Nombre() {
    	CriterioDeBusqueda busqueda = new CriterioDeBusquedaSimpleNombre("auriculares");
    	ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(3, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto1));
        assertTrue(resultadoBusqueda.contains(producto2));
        assertTrue(resultadoBusqueda.contains(paquete1));
    	
    	busqueda = new CriterioDeBusquedaSimpleNombre("zapatilla");
    	resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(1, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto3));
    	
    	busqueda = new CriterioDeBusquedaSimpleNombre("mouse");
    	resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertTrue(resultadoBusqueda.isEmpty());
       	
    }
    
    @Test
    void test002_Busqueda_Simple_Categoria() {
    	CriterioDeBusqueda busqueda = new CriterioDeBusquedaSimpleCategoria("electronica");
    	ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(3, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto1));
        assertTrue(resultadoBusqueda.contains(producto2));
        assertTrue(resultadoBusqueda.contains(paquete1));
    	
    	busqueda = new CriterioDeBusquedaSimpleCategoria("deportes");
    	resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(1, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto3));
    	
    	busqueda = new CriterioDeBusquedaSimpleCategoria("herramientas");
    	resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertTrue(resultadoBusqueda.isEmpty());
       }
    
    @Test
    void test003_Busqueda_Simple_Disponibilidad() {
    	CriterioDeBusqueda busqueda = new CriterioDeBusquedaSimpleDisponibilidad();
    	ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(3, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto1));
        assertTrue(resultadoBusqueda.contains(producto2));
        assertTrue(resultadoBusqueda.contains(paquete1));
    	
    	}
    
    @Test
    void test004_Busqueda_Simple_PrecioMaximo() {
    	CriterioDeBusqueda busqueda = new CriterioDeBusquedaSimplePrecioMaximo(800.0);
    	ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(3, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto1));
        assertTrue(resultadoBusqueda.contains(producto2));
        assertTrue(resultadoBusqueda.contains(paquete1));
    	
    	}
    
    @Test
    void test005_Busqueda_Compuesto_NOT() {
    	CriterioDeBusqueda busqueda = new CriterioDeBusquedaCompuestoNOT(new CriterioDeBusquedaSimpleDisponibilidad());
    	ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busqueda);
    	assertEquals(1, resultadoBusqueda.size());
    	assertTrue(resultadoBusqueda.contains(producto3));
            	
    	}
    
    @Test
    void test006_Busqueda_Compuesto_AND() {
        
        CriterioDeBusquedaCompuestoAND busquedaAND = new CriterioDeBusquedaCompuestoAND();
        busquedaAND.agregarCriterio(new CriterioDeBusquedaSimpleNombre("auriculares"));
        busquedaAND.agregarCriterio(new CriterioDeBusquedaSimpleCategoria("electronica"));
        
        ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busquedaAND);
        
        assertEquals(3, resultadoBusqueda.size());
        assertTrue(resultadoBusqueda.contains(producto1));
        assertTrue(resultadoBusqueda.contains(producto2));
        assertTrue(resultadoBusqueda.contains(paquete1));
    }

    @Test
    void test007_Busqueda_Compuesto_AND_UsandoCircuitoCorto() {
    	
        CriterioDeBusquedaCompuestoAND busquedaAND = new CriterioDeBusquedaCompuestoAND();
        busquedaAND.agregarCriterio(new CriterioDeBusquedaSimpleNombre("auriculares"));
        busquedaAND.agregarCriterio(new CriterioDeBusquedaSimpleDisponibilidad());
        busquedaAND.agregarCriterio(new CriterioDeBusquedaSimplePrecioMaximo(90.0));
        
        ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busquedaAND);
        
        assertEquals(1, resultadoBusqueda.size());
        assertTrue(resultadoBusqueda.contains(producto2));
    }
    
    @Test
    void test008_Busqueda_Compuesto_AND_VacioDevuelveTodo() {
        CriterioDeBusquedaCompuestoAND busquedaANDVacia = new CriterioDeBusquedaCompuestoAND();
        
        ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busquedaANDVacia);
        assertEquals(4, resultadoBusqueda.size());
    }
    
    @Test
    void test009_Busqueda_Compuesto_OR() {
        CriterioDeBusquedaCompuestoOR busquedaOR = new CriterioDeBusquedaCompuestoOR();
        
        busquedaOR.agregarCriterio(new CriterioDeBusquedaSimpleCategoria("deportes"));
        busquedaOR.agregarCriterio(new CriterioDeBusquedaSimplePrecioMaximo(80.0));
        
        ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busquedaOR);

        assertEquals(2, resultadoBusqueda.size());
        assertTrue(resultadoBusqueda.contains(producto3));
        assertTrue(resultadoBusqueda.contains(producto2));
    }

    @Test
    void test010_Busqueda_Compuesto_OR_SinResultados() {
        CriterioDeBusquedaCompuestoOR busquedaOR = new CriterioDeBusquedaCompuestoOR();
      
        busquedaOR.agregarCriterio(new CriterioDeBusquedaSimpleCategoria("herramientas"));
        busquedaOR.agregarCriterio(new CriterioDeBusquedaSimpleNombre("mouse"));
        
        ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busquedaOR);
        
        assertTrue(resultadoBusqueda.isEmpty());
    }

    @Test
    void test011_Busqueda_Compuesto_OR_Vacio_Devuelve_Falso() {
        
        CriterioDeBusquedaCompuestoOR busquedaORVacia = new CriterioDeBusquedaCompuestoOR();
        
        ArrayList<Item> resultadoBusqueda = catalogoDeUNQShop.buscar(busquedaORVacia);
        
        assertTrue(resultadoBusqueda.isEmpty());
    }
        
    }

   