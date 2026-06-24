package ecommerce.notificaciones;
import ecommerce.pedido.EstadoPedido;

public class NotificadorEmail implements ObserverPedido {

	private MailSender mailSender;

	public NotificadorEmail(MailSender mailSender) {
	    this.mailSender = mailSender;
	}
	
	@Override
	public void actualizar(EstadoPedido anterior, EstadoPedido nuevo) {
	    if (nuevo.esAlertable()) {
	        mailSender.enviarMail(
	            "cliente@email.com",
	            "Tu pedido cambió de estado",
	            "Tu pedido está ahora en estado: " + nuevo.getNombreEstado(),
	            "fotoEstadoPedido.png"
	        );
	    }
	}
}