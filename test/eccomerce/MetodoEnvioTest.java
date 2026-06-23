package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.catalogo1.Item;
import ecommerce.envio.CorreoArgentinaAPI;
import ecommerce.envio.Direccion;
import ecommerce.envio.EnvioEstandar;
import ecommerce.envio.EnvioExpress;
import ecommerce.envio.EnvioExpressAPI;
import ecommerce.envio.MetodoEnvio;
import ecommerce.envio.RetiroEnSucursal;
import ecommerce.envio.Sucursal;
import ecommerce.pedido.Pedido;

class MetodoEnvioTest {

    private Pedido pedido;
    private Item item;
    private MetodoEnvio envio;
    private CorreoArgentinaAPI correoMock;
    private EnvioExpressAPI expressMock;

    @BeforeEach
    void setUp() {
        pedido = new Pedido("PED-001");
        item = mock(Item.class);
        correoMock = mock(CorreoArgentinaAPI.class);
        expressMock = mock(EnvioExpressAPI.class);
        when(item.getPrecioFinal()).thenReturn(100.0);
        when(item.getPeso()).thenReturn(2.0);
        when(expressMock.calcularCosto(anyFloat())).thenReturn(15.0f);
        when(correoMock.estimarEnvio(anyFloat(), any())).thenReturn(20.0f);
    }
    
    @Test
    void envioExpressCalculaCostoCorrectamente() {
        pedido.agregarItem(item);
        pedido.setMetodoEnvio(new EnvioExpress(expressMock));
        assertEquals(15.0, pedido.calcularCostoEnvio());
    }
    
    @Test
    void envioEstandarCalculaCostoCorrectamente() {
        pedido.agregarItem(item);  // item pesa 2.0
        Direccion dir = new Direccion("Calle Falsa 123");
        pedido.setMetodoEnvio(new EnvioEstandar(dir, correoMock));
        assertEquals(20.0, pedido.calcularCostoEnvio()); // 2.0 * 10 = 20.0
    }
    
    @Test
    void envioExpressEstimaUnDia() {
        assertEquals(1, new EnvioExpress(expressMock).estimarDias());
    }

    @Test
    void envioEstandarEstimaSeteDias() {
        assertEquals(7, new EnvioEstandar(new Direccion("Calle Falsa 123"), correoMock).estimarDias());
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
