package ecommerce.reportes;

import ecommerce.catalogo1.Item;
import java.time.LocalDate;
import java.util.List;

public class ReporteHTMLVisitor extends ItemVisitor {

    public ReporteHTMLVisitor(LocalDate inicio, LocalDate fin) {
        super(inicio, fin);
    }

    @Override
    public String getResultado() {
        List<Item> items = getItemsOrdenados();
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n<body>\n  <h2>Reporte de Productos y Paquetes Más Vendidos</h2>\n");
        sb.append("  <table border='1' cellpadding='5' cellspacing='0'>\n");
        sb.append("    <tr bgcolor='#cccccc'>\n");
        sb.append("      <th>Ítem / Catálogo</th>\n");
        sb.append("      <th>Unidades Vendidas</th>\n");
        sb.append("      <th>Precio Promedio Cobrado</th>\n");
        sb.append("    </tr>\n");
        
        for (Item item : items) {
            sb.append(String.format("    <tr>\n      <td>%s</td>\n      <td>%d</td>\n      <td>$%.2f</td>\n    </tr>\n",
                    item.getNombre(), 
                    getUnidadesDe(item), 
                    getPrecioPromedioDe(item)));
        }
        
        sb.append("  </table>\n</body>\n</html>");
        return sb.toString();
    }
}
