package eccomerce;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ecommerce.DatosTarjetaCredito;
import ecommerce.MetodoDePago;
import ecommerce.TarjetaDeCredito;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.YearMonth;
import java.math.BigDecimal;

class TarjetaDeCreditoTest {
	// reloj fijo en el año 2026
	private final Clock relojFijo = Clock.fixed(
			Instant.parse("2026-06-01T10:00:00Z"), 
	        ZoneId.of("UTC")
	);
	// crea instancia tarjeta con reloj fijo
	MetodoDePago tarjeta = new TarjetaDeCredito(relojFijo);
	
	@Test
	void validaSinErrores_CuandoDatosSonCorrectos() {
        // vence 2035
        DatosTarjetaCredito datos = new DatosTarjetaCredito(
                "1234567812345678", 
                "123", 
                YearMonth.of(2035, 12)
        );
        
        assertDoesNotThrow(() -> tarjeta.procesarPago(datos, new BigDecimal("100.00")));
    }
	
	@Test
    void lanzaExcepcion_CuandoTarjetaEstaVencida() {
        // venció 2025
		DatosTarjetaCredito datos = new DatosTarjetaCredito(
                "1234567812345678", 
                "123", 
                YearMonth.of(2025, 12)
        );
		
        Exception exception = assertThrows(Exception.class, () -> {
        	tarjeta.procesarPago(datos, new BigDecimal("100.00"));
        });
        
        assertTrue(exception.getMessage().contains("expirada"));
    }
}
