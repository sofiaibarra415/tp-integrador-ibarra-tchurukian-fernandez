package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class MetodoEnvioTest {

    private Pedido pedido;
    private Item item;
    private MetodoEnvio envio;

    @BeforeEach
    void setUp() {
        pedido = new Pedido("PED-001");
        item = mock(Item.class);
        when(item.getPrecioFinal()).thenReturn(100.0);
        when(item.getPeso()).thenReturn(2.0);
    }
    
    @Test
    void envioExpressCalculaCostoCorrectamente() {
        pedido.agregarItem(item);
        pedido.setMetodoEnvio(new EnvioExpress());
        assertEquals(515.0, pedido.calcularCostoEnvio());
    }
    
    @Test
    void envioEstandarCalculaCostoCorrectamente() {
        pedido.agregarItem(item);  // item pesa 2.0
        Direccion dir = new Direccion("Calle Falsa 123");
        pedido.setMetodoEnvio(new EnvioEstandar(dir));
        assertEquals(20.0, pedido.calcularCostoEnvio()); // 2.0 * 10 = 20.0
    }
    
    @Test
    void envioExpressEstimaUnDia() {
        assertEquals(1, new EnvioExpress().estimarDias());
    }

    @Test
    void envioEstandarEstimaSeteDias() {
        assertEquals(7, new EnvioEstandar(new Direccion("Calle Falsa 123")).estimarDias());
    }

    @Test
    void retiroEnSucursalConStockLocalCuestaCoeroYDiaCero() {
        Sucursal sucursal = new Sucursal("Sucursal Centro", true);
        pedido.setMetodoEnvio(new RetiroEnSucursal(sucursal));
        assertEquals(0.0, pedido.calcularCostoEnvio());
        assertEquals(0, new RetiroEnSucursal(sucursal).estimarDias());
    }

    @Test
    void retiroEnSucursalSinStockLocalEstimaTresDias() {
        Sucursal sucursal = new Sucursal("Sucursal Norte", false);
        assertEquals(3, new RetiroEnSucursal(sucursal).estimarDias());
    }
    
    @Test
    void direccionDevuelveSuValor() {
        Direccion dir = new Direccion("Calle Falsa 123");
        assertEquals("Calle Falsa 123", dir.getDireccion());
    }
}
