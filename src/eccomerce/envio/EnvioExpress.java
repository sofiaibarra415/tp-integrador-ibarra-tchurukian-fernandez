package eccomerce.envio;

import eccomerce.pedido.Pedido;

public class EnvioExpress extends MetodoEnvio {
    
    private EnvioExpressAPI envioExpressAPI;
    
    public EnvioExpress(EnvioExpressAPI envioExpressAPI) {
        this.envioExpressAPI = envioExpressAPI;
    }
    
    @Override
    public double calcularCosto(Pedido p) {
        return (double) envioExpressAPI.calcularCosto((float) p.calcularTotal());
    }
    
    @Override
    public int estimarDias() {
        return 1;
    }
}