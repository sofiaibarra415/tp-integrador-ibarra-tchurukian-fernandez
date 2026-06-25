package ecommerce;

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
	private RegistroPagos reg;
	private APIBilletera apiBilletera;
	private BilleteraVirtual billetera;
	
	@BeforeEach
	void setUp() {
		reg = new RegistroPagos();
		apiTarjeta = mock(APITarjeta.class);
		tarjeta = new TarjetaDeCredito(apiTarjeta, reg);
		apiTransferencia = mock(APIBanco.class);
		transferencia = new TransferenciaBancaria(apiTransferencia, reg);
		apiBilletera = mock(APIBilletera.class);
		billetera = new BilleteraVirtual(apiBilletera, reg);
	}
	
	@Test
	void seProcesaPagoDeBilleteraExitosamente_CuandoDatosSonCorrectos() {
		DatosBilleteraVirtual datos = new DatosBilleteraVirtual(
				100.0, "usuario");
		
		when(apiBilletera.saldoSuficienteEnLaCuenta("usuario", 100.0))
			.thenReturn(true);
		when(apiBilletera.bloquearSaldoDeLaCuenta("usuario", 100.0))
			.thenReturn("ID0101010101");
		when(apiBilletera.confirmarTransaccion("usuario", "ID0101010101"))
			.thenReturn("00112233445566");
		
		billetera.procesarPago(datos, 100.00);
		
		assertTrue(reg.getCantidadIds() == 1); //se registró el pago por ende fue exitoso
	}
	
	@Test
	void seProcesaPagoDeTransferenciaExitosamente_CuandoDatosSonCorrectos() {
		DatosTransferenciaBancaria datos = new DatosTransferenciaBancaria(
				"0123456701234567891234", "usuario");
		
		when(apiTransferencia.sonDatosValidos("0123456701234567891234", "usuario"))
			.thenReturn(true);
		when(apiTransferencia.realizarTransferencia("0123456701234567891234", "usuario", 100.00))
			.thenReturn("00112233445566");
		
		transferencia.procesarPago(datos, 100.00);
		
		assertTrue(reg.getCantidadComprobantes() == 1); //significa que el pago se realizó
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
	void transaccionCancelada_CuandoDatosSonIncorrectos() {
		DatosTransferenciaBancaria datos = new DatosTransferenciaBancaria(
				"0123456701234567891234", "usuario");
		
		when(apiTransferencia.sonDatosValidos("0123456701234567891234", "usuario"))
			.thenReturn(false);
		
		transferencia.procesarPago(datos, 100.00);
		
		assertTrue(reg.getCantidadIds() == 0); // nunca llega a registrar
	}
	
	@Test
	void lanzaExcepcion_CuandoTipoDeDatosDeTarjetaEsInvalido() {
		DatosBilleteraVirtual datos = new DatosBilleteraVirtual(100.00, "usuario123");
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	tarjeta.procesarPago(datos, 100.00);
        });
        
        assertTrue(exception.getMessage().contains("Datos inválidos"));
	}
	
	@Test
	void lanzaExcepcion_CuandoTipoDeDatosDeTransferenciaEsInvalido() {
		DatosBilleteraVirtual datos = new DatosBilleteraVirtual(100.00, "usuario123");
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	transferencia.procesarPago(datos, 100.00);
        });
        
        assertTrue(exception.getMessage().contains("Datos inválidos"));
	}
	
	@Test
	void lanzaExcepcion_CuandoTipoDeDatosDeBilleteraEsInvalido() {
		DatosTarjetaCredito datos = new DatosTarjetaCredito(
                "1234567812345678", "123", YearMonth.of(2035, 12)
        );
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        	billetera.procesarPago(datos, 100.00);
        });
        
        assertTrue(exception.getMessage().contains("Datos inválidos"));
	}
	
	
	//tests clases de datos
	
	@Test
	void lanzaExcepcion_CuandoCBUDeDatosTransferenciaEsInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new DatosTransferenciaBancaria("123", "usuario");
        });
        
        assertTrue(exception.getMessage().contains("CBU/CVU inválido"));
	}
	@Test
	void lanzaExcepcion_CuandoAliasDeDatosTransferenciaEsInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new DatosTransferenciaBancaria("0123456701234567891234", "  ");
        });
        
        assertTrue(exception.getMessage().contains("Alias inválido"));
	}
	@Test
	void lanzaExcepcion_CuandoNumeroDeDatosTarjetaEsInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new DatosTarjetaCredito("h", "123", YearMonth.of(2035, 12));
        });
        
        assertTrue(exception.getMessage().contains("Número de tarjeta inválido."));
	}
	@Test
	void lanzaExcepcion_CuandoCvvDeDatosTarjetaEsInvalido() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new DatosTarjetaCredito("1234567812345678", "12345", YearMonth.of(2035, 12));
        });
        
        assertTrue(exception.getMessage().contains("CVV inválido."));
	}
}