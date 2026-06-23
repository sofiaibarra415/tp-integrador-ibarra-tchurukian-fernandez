package eccomerce.catalogo1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.catalogo1.Item;
import ecommerce.catalogo1.Paquete;

import java.util.ArrayList;


class PaqueteTest {

    private Item mockItem1;
    private Item mockItem2;
    private ArrayList<Item> listaItems;
    private Paquete paquete;

    @BeforeEach
    void setUp() {
    	System.setProperty("net.bytebuddy.experimental", "true");
        
        mockItem1 = mock(Item.class);//creo dos mock de item
        mockItem2 = mock(Item.class);

        listaItems = new ArrayList<>();
        listaItems.add(mockItem1);
        listaItems.add(mockItem2);

        paquete = new Paquete(	"Combo Gamer",		//nombre
        						"Teclado y Mouse",  //descripsion
        						"Electronica",		//categoria
        						10.0,				//descuento
        						listaItems);		//los mock de item
    }


    @Test
    void test001_GetPrecioBase_SumaLosPreciosDeLosItems() {
        when(mockItem1.getPrecioFinal()).thenReturn(100.0);
        when(mockItem2.getPrecioFinal()).thenReturn(50.0);

        assertEquals(150.0, paquete.getPrecioBase(), 0.001);
    }

    @Test
    void test002_GetPrecioFinal_HaceElDescuento() {
        when(mockItem1.getPrecioFinal()).thenReturn(100.0);
        when(mockItem2.getPrecioFinal()).thenReturn(50.0);
    
        assertEquals(135.0, paquete.getPrecioFinal(), 0.001); //el descuentp era de 10 porciento
    }

    @Test
    void test003_GetStock_Retorna_ElMenorStock() {
        when(mockItem1.getStock()).thenReturn(10);
        when(mockItem2.getStock()).thenReturn(3); 

        assertEquals(3, paquete.getStock());
    }

    @Test
    void test004_GetStock_DePaqueteVacio_EsCero() {
        Paquete paqueteVacio = new Paquete("a", "b", "c", 0, new ArrayList<>());
        assertEquals(0, paqueteVacio.getStock());
    }

    @Test
    void test005_IncrementarStock_Manda_IncrementarStock_ALosItems() {
        paquete.incrementarStock();

        verify(mockItem1, times(1)).incrementarStock();
        verify(mockItem2, times(1)).incrementarStock();
    }

    @Test
    void test006_DecrementarStock_Manda_DecrementarStock_ALosItems() {
        paquete.decrementarStock();

        verify(mockItem1, times(1)).decrementarStock();
        verify(mockItem2, times(1)).decrementarStock();
    }

    @Test
    void test007_Funcionamiento_deGetPeso() {
    	when(mockItem1.hayPeso()).thenReturn(true);
    	when(mockItem2.hayPeso()).thenReturn(true);
    	when(mockItem1.getPeso()).thenReturn(1.5);
    	when(mockItem2.getPeso()).thenReturn(0.8);
    	assertEquals(2.3, paquete.getPeso(), 0.001);
    }
    
    @Test
    void test008_Funcionamiento_hayPeso_CuandoTienePeso() {
    	when(mockItem1.hayPeso()).thenReturn(true);
        when(mockItem2.hayPeso()).thenReturn(true);
    	
        assertTrue(paquete.hayPeso());
    }

    @Test
    void test009_Funcionamiento_hayStock_CuandoTieneStock() {
    	when(mockItem1.hayStock()).thenReturn(true);
        when(mockItem2.hayStock()).thenReturn(true);
        assertTrue(paquete.hayStock());
    }
    
    @Test
    void test010_Funcionamiento_hayStock_CuandoNoHay() {
    	when(mockItem1.hayStock()).thenReturn(true);
    	when(mockItem2.hayStock()).thenReturn(false);
    	assertFalse(paquete.hayStock());
    }

    @Test
    void test011_Funcionamiento_hayPeso_CuandoNoHay() {
    	when(mockItem1.hayPeso()).thenReturn(true);
    	when(mockItem2.hayPeso()).thenReturn(false);
    	assertFalse(paquete.hayPeso());
    }

    @Test
    void test012_GetPeso_LanzaExcepcion_SiNoHayPeso() {
    	
    	when(mockItem1.hayPeso()).thenReturn(true);
    	when(mockItem2.hayPeso()).thenReturn(false);
    	IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> paquete.getPeso()
        );
    	assertEquals("No hay peso", excepcion.getMessage());
    }

    @Test
    void test013_EsPaqueteValido() {
    	when(mockItem1.esItemValido()).thenReturn(true);
    	when(mockItem2.esItemValido()).thenReturn(true);
    	assertTrue(paquete.esItemValido());
    }

    @Test
    void test014_EsPaqueteInvalido_SiUnItemEsInvalido() {
    	when(mockItem1.esItemValido()).thenReturn(true);
    	when(mockItem2.esItemValido()).thenReturn(false); 
    	assertFalse(paquete.esItemValido());
    }

    @Test
    void test015_EsPaqueteInvalido_SiCategoriaEsNula() {
    	when(mockItem1.esItemValido()).thenReturn(true);
    	when(mockItem2.esItemValido()).thenReturn(true);
    	
    	Paquete paqueteSinCategoria = new Paquete("a", "b", null, 10.0, listaItems);
    	
    	assertFalse(paqueteSinCategoria.esItemValido());
    }

    @Test
    void test016_EsPaqueteInvalido_SiNombreEsNulo() {
        when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(true);

        Paquete paqueteSinNombre = new Paquete(null, "b", "c", 10.0, listaItems);
        
        assertFalse(paqueteSinNombre.esItemValido());
    }

    @Test
    void test017_EsPaqueteInvalido_SiDescripcionEsNula() {
        when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(true);

        Paquete paqueteSinDesc = new Paquete("a", null, "c", 10.0, listaItems);
        
        assertFalse(paqueteSinDesc.esItemValido());
    }

    @Test
    void test018_EsPaqueteInvalido_SiDescuentoEsNegativo() {
        when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(true);

        Paquete paqueteDescuentoNegativo = new Paquete("a", "b", "c", -5.0, listaItems);
        
        assertFalse(paqueteDescuentoNegativo.esItemValido());
    }

    @Test
    void test019_EsPaqueteInvalido_SiDescuentoEsMasDe100() {
        when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(true);

        Paquete paqueteDescuento100 = new Paquete("a", "b", "c", 100.0, listaItems);
        Paquete paqueteDescuento101 = new Paquete("a", "b", "c", 101.0, listaItems);
        
        assertFalse(paqueteDescuento100.esItemValido());
        assertFalse(paqueteDescuento101.esItemValido());
    }

    @Test
    void test020_EsPaqueteInValido_SiEstaVacio() {
        
        Paquete paqueteVacio = new Paquete("a", "b", "c", 10.0, new ArrayList<>());
        
        assertFalse(paqueteVacio.esItemValido());
    }
    
    @Test
    void test021_EsPaqueteInValido_SiAtibutoDinamicoEsNulo() {
    	when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(true);
        
        Paquete paqueteAtribuloInvalido = new Paquete("a", "b", "c", 99.0, listaItems);
        paqueteAtribuloInvalido.agregarAtributo("invalido", null);
        
        assertFalse(paqueteAtribuloInvalido.esItemValido());
    }
    
    @Test
    void test022_EsPaqueteValido_SiAtibutoDinamicoEsValido() {
    	when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(true);
        
        Paquete paqueteAtribuloValido = new Paquete("a", "b", "c", 99.0, listaItems);
        paqueteAtribuloValido.agregarAtributo("valido", "algo");
        
        assertTrue(paqueteAtribuloValido.esItemValido());
    }
    
   
}
