package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ecommerce.pago.*;

import java.time.YearMonth;

class MetodoDePagoTest {
	
	private APITarjeta apiTarjeta;
	
	private TarjetaDeCredito tarjeta;
	
	private APIBanco apiTransferencia;
	
	private TransferenciaBancaria transferencia;
	
	@BeforeEach
	void setUp() {
		apiTarjeta = mock(APITarjeta.class);
		tarjeta = new TarjetaDeCredito(apiTarjeta);
		apiTransferencia = mock(APIBanco.class);
		transferencia = new TransferenciaBancaria(apiTransferencia);
	}
	
	@Test
	void seProcesaPagoDeTransferenciaExitosamente_CuandoDatosDeTarjetaSonCorrectos() {
		DatosTransferenciaBancaria datos = new DatosTransferenciaBancaria(
				"0123456701234567891234", "usuario");
		
		when(apiTransferencia.sonDatosValidos("0123456701234567891234", "usuario"))
			.thenReturn(true);
		when(apiTransferencia.realizarTransferencia("0123456701234567891234", "usuario", 100.00))
			.thenReturn("00112233445566");
		
		assertDoesNotThrow(() -> transferencia.procesarPago(datos, 100.00));
	}
	@Test
	void validaCorrectamente_CuandoDatosDeTarjetaSonCorrectos() {
        DatosTarjetaCredito datos = new DatosTarjetaCredito(
                "1234567812345678", "123", YearMonth.of(2035, 12)
        );
        
        when(apiTarjeta.sonDatosValidos("1234567812345678", "123", YearMonth.of(2035, 12)))
        	.thenReturn(true);
        when(apiTarjeta.preAutorizar("1234567812345678", "123", 100.00))
        	.thenReturn("0001123456789");
        when(apiTarjeta.ejecutarTransaccion("0001123456789"))
    		.thenReturn("111222333444555666");
        
        tarjeta.procesarPago(datos, 100.00);
        //si se ejecuta preAutorizar, significa que la tarjeta es válida
        verify(apiTarjeta, times(1)).preAutorizar("1234567812345678", "123", 100.00);
    }
	
	@Test
	void lanzaExcepcion_CuandoDatosDeTarjetaSonInvalidos() {
		DatosBilleteraVirtual datos = new DatosBilleteraVirtual(100.00, "usuario123");
		
		Exception exception = assertThrows(Exception.class, () -> {
        	tarjeta.procesarPago(datos, 100.00);
        });
        
        assertTrue(exception.getMessage().contains("Datos inválidos"));
	}
}