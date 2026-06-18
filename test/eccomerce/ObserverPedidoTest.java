package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ecommerce.notificaciones.Fidelizacion;
import ecommerce.notificaciones.GeneradorFactura;
import ecommerce.notificaciones.MailSender;
import ecommerce.notificaciones.NotificadorEmail;
import ecommerce.envio.MetodoEnvio;
import ecommerce.items.Item;
import ecommerce.pedido.Pedido;
import ecommerce.pedido.Entregado;
import ecommerce.pedido.Cancelado;
import ecommerce.pedido.EstadoPedido;

class ObserverPedidoTest {

    private Pedido pedido;
    private Item item;
    private MailSender mailSenderMock;
    private NotificadorEmail notificadorEmail;
    private GeneradorFactura generadorFactura;
    private Fidelizacion fidelizacion;

    @BeforeEach
    void setUp() {
        pedido = new Pedido("PED-001");
        item = mock(Item.class);
        MetodoEnvio envioMock = mock(MetodoEnvio.class);
        when(item.getPrecioFinal()).thenReturn(100.0);
        pedido.setMetodoEnvio(envioMock);

        generadorFactura = mock(GeneradorFactura.class);
        fidelizacion = mock(Fidelizacion.class);
        mailSenderMock = mock(MailSender.class);
        notificadorEmail = new NotificadorEmail(mailSenderMock);

        pedido.suscribir(notificadorEmail);
        pedido.suscribir(generadorFactura);
        pedido.suscribir(fidelizacion);
    }

    @Test
    void alConfirmarSeEnviaEmail() {
        pedido.agregarItem(item);
        pedido.confirmar();
        verify(mailSenderMock).enviarMail(any(), any(), any(), any());
    }

    @Test
    void alEnviarSeEnviaEmail() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        pedido.enviar();
        verify(mailSenderMock, times(2)).enviarMail(any(), any(), any(), any());
    }

    @Test
    void alEntregarSeGeneraFactura() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        pedido.enviar();
        pedido.entregar();
        verify(generadorFactura, atLeastOnce()).actualizar(any(), any());
    }

    @Test
    void alCancelarSeEnviaCupon() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.cancelar();
        verify(fidelizacion, atLeastOnce()).actualizar(any(), any());
    }

    @Test
    void alIniciarPreparacionNoSeEnviaEmail() {
        pedido.agregarItem(item);
        pedido.confirmar();
        pedido.iniciarPreparacion();
        // confirmar envió 1 mail, iniciarPreparacion no debe enviar más
        verify(mailSenderMock, times(1)).enviarMail(any(), any(), any(), any());
    }
    
    @Test
    void alDesuscribirUnObserverYaNoRecibeNotificaciones() {
        pedido.agregarItem(item);
        pedido.desuscribir(notificadorEmail);
        pedido.confirmar();
        verify(mailSenderMock, never()).enviarMail(any(), any(), any(), any());
    }
    
    @Test
    void generadorFacturaActuaAlEntregar() {
        Pedido pedidoMock = mock(Pedido.class);
        when(pedidoMock.getItems()).thenReturn(new java.util.ArrayList<>());
        GeneradorFactura g = new GeneradorFactura();
        Entregado entregado = new Entregado(pedidoMock);
        g.actualizar(mock(EstadoPedido.class), entregado);
    }

    @Test
    void generadorFacturaNoActuaEnOtroEstado() {
        Pedido pedidoMock = mock(Pedido.class);
        when(pedidoMock.getItems()).thenReturn(new java.util.ArrayList<>());
        GeneradorFactura g = new GeneradorFactura();
        Cancelado cancelado = new Cancelado(pedidoMock);
        g.actualizar(mock(EstadoPedido.class), cancelado);
    }

    @Test
    void fidelizacionActuaAlCancelar() {
        Pedido pedidoMock = mock(Pedido.class);
        when(pedidoMock.getItems()).thenReturn(new java.util.ArrayList<>());
        Fidelizacion f = new Fidelizacion();
        Cancelado cancelado = new Cancelado(pedidoMock);
        f.actualizar(mock(EstadoPedido.class), cancelado);
    }

    @Test
    void fidelizacionNoActuaEnOtroEstado() {
        Pedido pedidoMock = mock(Pedido.class);
        when(pedidoMock.getItems()).thenReturn(new java.util.ArrayList<>());
        Fidelizacion f = new Fidelizacion();
        Entregado entregado = new Entregado(pedidoMock);
        f.actualizar(mock(EstadoPedido.class), entregado);
    }
}