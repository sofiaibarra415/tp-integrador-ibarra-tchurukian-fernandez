package eccomerce.envio;

public class CorreoArgentina {
	private CorreoArgentina() {} // para el 100 de coverge en tests
	
    public static float estimarEnvio(float peso, Direccion direccionEnvio) {
        return peso * 10; //logica inventada
    }
}
