package eccomerce;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        
        producto = new Producto(
            "Auriculares",			//nombre 
            "Auriculares wireless",	//descripcion 
            "Electronica", 			//categoria
            15.0, 					//descuento
            "ABC-123", 				//sku
            "Sony", 				//marca
            120.0, 					//precio base
            1						//stock
        );
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
        assertEquals(102.0, producto.getPrecioFinal(), 0.001); //precio final es una operacion
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
        // arranca en peso cero y devuelve false
        assertFalse(producto.existeAtributoOpcional("Peso"));
        assertFalse(producto.hayPeso(),"fallo 7.2");

        //si lo agrego
        producto.agregarAtributo("Peso","0.35");
        
        assertTrue(producto.existeAtributoOpcional("Peso"));
        assertEquals("0.35", producto.getAtributoOpcional("Peso"));
        
        assertEquals(0.35, producto.getPeso(), 0.001);
        assertTrue(producto.hayPeso());
    }


    @Test
    void test008_EsItem_Valido() {
        assertTrue(producto.esItemValido()); //producto tiene todos datos validos
    }

    @Test
    void test009_EsItem_Invalido_SiPrecioBaseEsCero() {
        Producto prodInvalido = new Producto("a", "b", "c", 5.0, "d", "e", 0, 5);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test010_EsItem_Invalido_SiStockEsNegativo() {
        Producto prodInvalido = new Producto("a", "b", "c", 30, "d", "e", 5.0, -5);
        assertFalse(prodInvalido.esItemValido());
    }

    @Test
    void test011_EsItem_Invalido_SiTieneAtributoDinamicoNulo() {
        
    	producto.agregarAtributo("algo", null);
        
        assertFalse(producto.esItemValido());
    }
}