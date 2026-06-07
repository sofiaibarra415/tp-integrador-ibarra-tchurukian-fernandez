package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        when(mockItem1.getPrecioBase()).thenReturn(100.0);
        when(mockItem2.getPrecioBase()).thenReturn(50.0);

        assertEquals(150.0, paquete.getPrecioBase(), 0.001);
    }

    @Test
    void test002_GetPrecioFinal_HaceElDescuento() {
        when(mockItem1.getPrecioBase()).thenReturn(100.0);
        when(mockItem2.getPrecioBase()).thenReturn(50.0);
    
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
    void test007_GetPesoSumaElPesoDeLosComponentes() {
        when(mockItem1.getPeso()).thenReturn(1.5);
        when(mockItem2.getPeso()).thenReturn(0.8);

        assertEquals(2.3, paquete.getPeso(), 0.001);
    }

    @Test
    void test008_AgregarAtributoPesoLanzaExcepcion() {
        // no se puede setear peso a mano en un paquete
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paquete.agregarAtributo("peso", "2.0");
        });
        assertEquals("En un Paquete el peso es la suma de sus Productos, no es un atributo seteable", exception.getMessage());
    }

    @Test
    void test009_EsItemInvalido_SiUnItemQueContiene_EsInvalido() {
        // El primer item es valido pero el segundo no
        when(mockItem1.esItemValido()).thenReturn(true);
        when(mockItem2.esItemValido()).thenReturn(false);

        assertFalse(paquete.esItemValido(), "Si un componente es inválido, el paquete debe ser inválido");
    }
}