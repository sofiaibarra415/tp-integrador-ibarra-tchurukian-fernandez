package eccomerce;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.YearMonth;

class TarjetaDeCreditoTest {
	// Reloj fijo en el año 2026.
	private final Clock relojFijo = Clock.fixed(
			Instant.parse("2026-06-01T10:00:00Z"), 
	        ZoneId.of("UTC")
	);
	
	@Test
	void validaSinErrores_CuandoDatosSonCorrectos() {
        // Vence 2035
        YearMonth vencimientoFuturo = YearMonth.of(2035, 12);
        
        TarjetaDeCredito tarjeta = new TarjetaDeCredito(
            "0011212110305575", 
            "123", 
            vencimientoFuturo, 
            relojFijo
        );

        assertDoesNotThrow(() -> tarjeta.validarDatos());
    }
	
	@Test
    void lanzaExcepcion_CuandoTarjetaEstaVencida() {
        // Venció 2025.
        YearMonth vencimientoPasado = YearMonth.of(2025, 1);
        
        TarjetaDeCredito tarjeta = new TarjetaDeCredito(
            "0011212110305575", 
            "123", 
            vencimientoPasado, 
            relojFijo
        );

        assertThrows(IllegalArgumentException.class, () -> tarjeta.validarDatos());
    }
}
