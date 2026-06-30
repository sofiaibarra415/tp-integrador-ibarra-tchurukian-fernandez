package ecommerce.reportes8;

import ecommerce.catalogo1.Item;
import java.time.LocalDate;
import java.util.List;

public class ReporteTextoPlanoVisitor extends ItemVisitor {

    public ReporteTextoPlanoVisitor(LocalDate inicio, LocalDate fin) {
        super(inicio, fin);
    }

    @Override
    public String getResultado() {
        
        List<Item> items = getItemsOrdenados();
        
        StringBuilder sb = new StringBuilder("--- REPORTE DE MÁS VENDIDOS (TEXTO PLANO) ---\n");
        for (Item item : items) {
            sb.append(String.format("Item: %s | Unidades: %d | Precio Promedio Cobrado: $%.2f\n",
                    item.getNombre(), 
                    getUnidadesDe(item), 
                    getPrecioPromedioDe(item)));
        }
        return sb.toString();
    }
}