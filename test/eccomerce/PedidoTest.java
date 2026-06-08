package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eccomerce.pedido.Pedido;
import eccomerce.pedido.EstadoPedido;
import eccomerce.envio.MetodoEnvio;
import eccomerce.pedido.Borrador;
import eccomerce.pedido.Confirmado;
import eccomerce.pedido.EnPreparacion;
import eccomerce.pedido.Enviado;
import eccomerce.pedido.Entregado;
import eccomerce.pedido.Cancelado;
import eccomerce.pedido.OperacionInvalidaException;



class PedidoTest {

	private Pedido pedido;
    private Item item;
   
    @BeforeEach
    void setUp() {
        pedido = new Pedido("PED-001");
        item = mock(Item.class);
        MetodoEnvio envioMock = mock(MetodoEnvio.class);
        when(item.getPrecioFinal()).thenReturn(100.0);
        pedido.setMetodoEnvio(envioMock);
    }
    
    @Test
    void unPedidoNuevoEstaEnBorrador() {
        assertEquals("BORRADOR", pedido.getEstado().getNombreEstado());
    }
    
    @Test
    void alConfirmarElPedidoCambiaAConfirmado() {
        pedido.agregarItem(item);
        pedido.confirmar();
        assertEquals("CONFIRMADO", pedido.getEstado().getNombreEstado());
    }
    
    @Test
    void alConfirmarSeDecrementaElStockDeLoItems() {
        pedido.agregarItem(item);
        pedido.confirmar();
        verify(item).decrementarStock();
    }
    
    @Test
    void flujoCompletoHastaEntregado() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        pedido.enviar();
        pedido.entregar();
        assertEquals("ENTREGADO", pedido.getEstado().getNombreEstado());
    }
    
    @Test
    void alCancelarDesdeBorradorCambiaACancelado() {
        pedido.cancelar();
        assertEquals("CANCELADO", pedido.getEstado().getNombreEstado());
    }

    @Test
    void alCancelarDesdeConfirmadoSeIncrementaElStock() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.cancelar();
        verify(item).incrementarStock();
    }
    
    @Test
    void alCancelarDesdeEnviadoSoloReembolsaProductos() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        pedido.enviar();
        pedido.cancelar();
        
        assertEquals("CANCELADO", pedido.getEstado().getNombreEstado());
        assertEquals(100.0, pedido.getMontoReembolsado());
    }
    
    @Test
    void confirmarUnPedidoYaConfirmadoLanzaExcepcion() {
        pedido.agregarItem(item);
        pedido.confirmar();
        assertThrows(OperacionInvalidaException.class, () -> pedido.confirmar());
    }
    
    @Test
    void agregarItemDesdeConfirmadoLanzaExcepcion() {
        pedido.agregarItem(item);
        pedido.confirmar();
        assertThrows(OperacionInvalidaException.class, () -> pedido.agregarItem(item));
    }
    
    @Test
    void enviarDesdeBorradorLanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> pedido.enviar());
    }

    @Test
    void quitarItemDesdeConfirmadoLanzaExcepcion() {
        pedido.agregarItem(item);
        pedido.confirmar();
        assertThrows(OperacionInvalidaException.class, () -> pedido.quitarItem(item));
    }
    
    @Test
    void iniciarPreparacionDesdeBorradorLanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> pedido.iniciarPreparacion());
    }

    @Test
    void entregarDesdeBorradorLanzaExcepcion() {
        assertThrows(OperacionInvalidaException.class, () -> pedido.entregar());
    }
    
    @Test
    void cancelarDesdeEntregadoLanzaExcepcion() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        pedido.enviar();
        pedido.entregar();
        assertThrows(OperacionInvalidaException.class, () -> pedido.cancelar());
    }
    
    @Test
    void quitarItemDesdeBorradorLoQuita() {
        pedido.agregarItem(item);
        pedido.quitarItem(item);
        assertEquals(0, pedido.getItems().size());
    }
    
    @Test
    void pedidoEnPreparacionTieneEstadoCorrecto() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        assertEquals("EN_PREPARACION", pedido.getEstado().getNombreEstado());
    }
    
    @Test
    void pedidoEnviadoTieneEstadoCorrecto() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        pedido.enviar();
        assertEquals("ENVIADO", pedido.getEstado().getNombreEstado());
    }
    
    @Test
    void confirmarSinMetodoEnvioLanzaExcepcion() {
        Pedido pedidoSinEnvio = new Pedido("PED-002");
        pedidoSinEnvio.agregarItem(item);
        assertThrows(OperacionInvalidaException.class, () -> pedidoSinEnvio.confirmar());
    }
}
