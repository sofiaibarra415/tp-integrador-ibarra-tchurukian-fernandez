package ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.catalogo1.Catalogo;
import ecommerce.catalogo1.Item;
import ecommerce.catalogo1.Producto;
import ecommerce.catalogo1.Paquete;


class CatalogoTest {

    private Catalogo catalogoDeUNQShop;
    private Producto producto1;
    private Producto producto2;
    private Paquete  paquete1;
    ArrayList<Item> lista = new ArrayList<>();
    
    

    @BeforeEach
    void setUp() {
        catalogoDeUNQShop = new Catalogo("Catalogo UNQ-Shop");

        producto1 = new Producto("a", "b", "c", 5.0, "SKU-1", "e", 120.0, 1, 0);
        producto2 = new Producto("a", "b", "c", 5.0, "SKU-2", "e", 120.0, 1, 0);
        lista.add(producto1);
        lista.add(producto2);
        paquete1  = new Paquete("a" , "b", "c", 5.0 ,lista);
        
    }

    @Test
    void test001_GetNombre() {
        assertEquals("Catalogo UNQ-Shop", catalogoDeUNQShop.getNombre());
    }

    @Test
    void test002_Catalogo_EmpiezaVacio() {
        assertTrue(catalogoDeUNQShop.getItemsDelCatalogo().isEmpty());
    }

    @Test
    void test003_AgregarItem() {
        catalogoDeUNQShop.agregarItem(producto1);
        assertEquals(1, catalogoDeUNQShop.getItemsDelCatalogo().size());
        assertTrue(catalogoDeUNQShop.getItemsDelCatalogo().contains(producto1));
        
        catalogoDeUNQShop.agregarItem(producto2);
        assertEquals(2, catalogoDeUNQShop.getItemsDelCatalogo().size());
        assertTrue(catalogoDeUNQShop.getItemsDelCatalogo().contains(producto2));
        
        catalogoDeUNQShop.agregarItem(paquete1);
        assertEquals(3, catalogoDeUNQShop.getItemsDelCatalogo().size());
        assertTrue(catalogoDeUNQShop.getItemsDelCatalogo().contains(paquete1));
   }

    
}