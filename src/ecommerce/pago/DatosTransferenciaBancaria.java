package ecommerce.pago;

public class DatosTransferenciaBancaria implements DatosPago {
	
	private String cbuOcvu;
	private String alias;

    public DatosTransferenciaBancaria(String cbuOcvu, String alias) {
    	if (!cbuOcvu.trim().isEmpty()) {
            if (!cbuOcvu.matches("\\d{22}")) {
                throw new IllegalArgumentException("CBU/CVU inválido: debe contener exactamente 22 dígitos numéricos.");
            }
            else { this.cbuOcvu = cbuOcvu; } // si el cbu/cvu es válido lo guarda y continúa
        }
    	if (alias.trim().isEmpty()) {
			throw new IllegalArgumentException("Alias inválido: no puede ser vacío.");
		}
		else { this.alias = alias; }
    }

    public String getCbuOcvu() { return cbuOcvu; }
    public String getAlias() { return alias; }
}