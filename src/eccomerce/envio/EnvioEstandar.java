package eccomerce.envio;

import eccomerce.pedido.Pedido;

public class EnvioEstandar extends MetodoEnvio {
    
    private Direccion direccion;
    private CorreoArgentinaAPI correoArgentina;
    
    public EnvioEstandar(Direccion direccion, CorreoArgentinaAPI correoArgentina) {
        this.direccion = direccion;
        this.correoArgentina = correoArgentina;
    }
    
    @Override
    public double calcularCosto(Pedido p) {
        return (double) correoArgentina.estimarEnvio((float) p.calcularPeso(), direccion);
    }
    
    @Override
    public int estimarDias() {
        return 7;
    }
}