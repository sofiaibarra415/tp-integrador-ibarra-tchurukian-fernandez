package eccomerce.catalogo1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.catalogo1.Producto;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        // producto valido
        producto = new Producto(
            "Auriculares",           // nombre 
            "Auriculares wireless",  // descripcion 
            "Electronica",           // categoria
            15.0,                    // descuento
            "ABC-123",               // sku
            "Sony",                  // marca
            120.0,                   // precio base
            1,                       // stock
            20.0                     // peso
        );
        producto.agregarAtributo("origen", "japon");
    }

    @Test
    void test001_Getters_Producto() {
        assertEquals("Auriculares", producto.getNombre());
        assertEquals("Auriculares wireless", producto.getDescripcion());
        assertEquals("Electronica", producto.getCategoria());
        assertEquals(15.0, producto.getDescuentoPromocional(), 0.001);
        assertEquals("ABC-123", producto.getSKU());
        assertEquals("Sony", producto.getMarca());
        assertEquals(120.0, producto.getPrecioBase(), 0.001);
        assertEquals(1, producto.getStock());
        assertEquals(102.0, producto.getPrecioFinal(), 0.001); 
    }

    @Test
    void test002_IncrementarStock() {
        producto.incrementarStock();
        assertEquals(2, producto.getStock());
    }

    @Test
    void test003_IncrementarStockEn5() {
        producto.incrementarStockEn(5);
        assertEquals(6, producto.getStock());
    }

    @Test
    void test004_DecrementarStock() {
        producto.decrementarStock();
        assertEquals(0, producto.getStock());
    }

    @Test
    void test005_HayStock() {
        assertTrue(producto.hayStock());
    }

    @Test
    void test006_NoHayStock() {
        producto.decrementarStock();
        assertFalse(producto.hayStock());
    }

    @Test
    void test007_FuncionamientoDe_hayPeso_Y_getPeso() {
        assertEquals(20.0, producto.getPeso(), 0.001);
        assertTrue(producto.hayPeso());
        
        Producto prodInvalido = new Producto("a", "b", "c", 5.0, "d", "e", 120.0, 1, 0);
        assertFalse(prodInvalido.hayPeso());
   
        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> prodInvalido.getPeso()
        );
        
        assertEquals("No hay peso", excepcion.getMessage());
    }

    @Test
    void test008_EsItem_Valido() {
        assertTrue(producto.esItemValido()); 
    }

    @Test
    void test009_EsItem_Invalido_SiPrecioBaseEsCero() {
        Producto prodInvalido = new Producto("a", "b", "c", 5.0, "d", "e", 0.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test010_EsItem_Invalido_SiStockEsNegativo() {
        Producto prodInvalido = new Producto("a", "b", "c", 30.0, "d", "e", 5.0, -5, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test011_EsItem_Invalido_SiTieneAtributoDinamicoNulo() {
        producto.agregarAtributo("algo", null);
        assertFalse(producto.esItemValido());
    }

    @Test
    void test012_EsItem_Invalido_SiSKUEsNulo() {
        Producto prodInvalido = new Producto("a", "b", "c", 5.0, null, "e", 120.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test013_EsItem_Invalido_SiCategoriaEsNulo() {
        Producto prodInvalido = new Producto("a", "b", null, 5.0, "d", "e", 120.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test014_EsItem_Invalido_SiNombreEsNulo() {
        Producto prodInvalido = new Producto(null, "b", "c", 5.0, "d", "e", 120.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test015_EsItem_Invalido_SiDescripcionEsNulo() {
        Producto prodInvalido = new Producto("a", null, "c", 5.0, "d", "e", 120.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test016_EsItem_Invalido_SiMarcaEsNulo() {
        Producto prodInvalido = new Producto("a", "b", "c", 5.0, "d", null, 120.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test017_EsItem_Invalido_SiDescuentoEsNegativo() {
        Producto prodInvalido = new Producto("a", "b", "c", -1.0, "d", "e", 120.0, 1, 20.0);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test018_EsItem_Invalido_SiDescuentoEsMasQue100() {
        Producto prodInvalidoLimite = new Producto("a", "b", "c", 100.0, "d", "e", 120.0, 1, 20.0);
        Producto prodInvalidoExcedido = new Producto("a", "b", "c", 105.0, "d", "e", 120.0, 1, 20.0);
        
        assertFalse(prodInvalidoLimite.esItemValido());
        assertFalse(prodInvalidoExcedido.esItemValido());
    }

    
    @Test
    void test019_EsItem_Invalido_SiNoTienePeso() {
        Producto prodInvalido = new Producto("a", "b", "c", 5.0, "d", "e", 120.0, 1, 0.0);
        assertFalse(prodInvalido.esItemValido());
    }
    
    @Test
    void test020_EsItem_Valido_ConStockEnCero() {
        Producto prodSinStock = new Producto("a", "b", "c", 5.0, "d", "e", 120.0, 0, 20.0);
        assertTrue(prodSinStock.esItemValido());
    }
    
    @Test
    void test021_AgregarAtributo_LanzaIExepcionSiYaExiste() {
    	producto.agregarAtributo("a", "b");
    	
    	IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> producto.agregarAtributo("a", "c"));
    	
    	assertEquals("El atributo ya existe", excepcion.getMessage());
    }
    
    @Test
    void test022_GetAtributoOpcional() {
    	producto.agregarAtributo("a", "b");
    	assertEquals("b", producto.getAtributoOpcional("a"));
    	
    	producto.agregarAtributo("AaA", "b");
    	assertEquals("b", producto.getAtributoOpcional("aaa")); //no importan mayusculas y minusculas
    	  	
    }

    @Test
    void test023_GetAtributoOpcional_LanzaExepcionSiNoExisteAtributo() {
    	IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class, 
            () -> producto.getAtributoOpcional("un atributo"));
    	
    	assertEquals("El atributo no existe", excepcion.getMessage());
    }
}
