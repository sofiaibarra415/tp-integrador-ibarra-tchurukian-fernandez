package ecommerce.reportes;

import ecommerce.catalogo1.Item;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ReporteCSVVisitor extends ItemVisitor {

    public ReporteCSVVisitor(LocalDate inicio, LocalDate fin) {
        super(inicio, fin);
    }

    @Override
    public String getResultado() {
        List<Item> items = getItemsOrdenados();
        
        StringBuilder sb = new StringBuilder("Nombre;UnidadesVendidas;PrecioPromedioCobrado\n");
        for (Item item : items) {
            sb.append(item.getNombre()).append(";")
              .append(getUnidadesDe(item)).append(";")
              // Usamos Locale.US para forzar que los decimales se separen con punto (.) en vez de coma
              .append(String.format(Locale.US, "%.2f", getPrecioPromedioDe(item))).append("\n");
        }
        return sb.toString();
    }
}
