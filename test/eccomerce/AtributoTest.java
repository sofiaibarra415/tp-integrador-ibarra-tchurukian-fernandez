package eccomerce;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AtributoTest {

    @Test
    void test001_ConstructorFunciona_GettersFuncionan() {
        
        Atributo atributo = new Atributo("Ancho", "30 cm");	//instanciamos
        assertEquals("Ancho", atributo.getNombre());      	//verificamos el nombre
        assertEquals("30 cm", atributo.getValor());			//verificamos el valor
    }
}

    