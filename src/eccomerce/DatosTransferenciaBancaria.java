package eccomerce;

public class DatosTransferenciaBancaria implements DatosPago {
	private final String cbuOcvu;
	private final String alias;

    public DatosTransferenciaBancaria(String cbuOcvu, String alias) {
        this.cbuOcvu = cbuOcvu;
        this.alias = alias;
    }

    public String getCbuOcvu() { return cbuOcvu; }
    public String getAlias() { return alias; }
}