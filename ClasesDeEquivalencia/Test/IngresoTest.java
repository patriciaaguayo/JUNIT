import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngresoTest {

    @Test
    void testGetTipo() {
        Ingreso ingreso = new Ingreso();
        ingreso.setTipo(1);
        System.out.println("El Tipo es: " + ingreso.getTipo());
        assertEquals(1, ingreso.getTipo());
    }

    @Test
    void testSetTipo() {
        Ingreso ingreso = new Ingreso();
        ingreso.setTipo(2);
        System.out.println("El Tipo es: " + ingreso.getTipo());
        assertEquals(2, ingreso.getTipo());
    }

    @Test
    void testHacerIngreso() {
        Ingreso ingreso = new Ingreso();
        double ingresoFinal = ingreso.hacerIngreso(1);
        System.out.println("El ingreso final es: " + ingresoFinal);
        assertNotEquals(0, ingresoFinal);
    }

    @Test
    void testObtenerIngreso() {
        Ingreso ingreso = new Ingreso();
        double ingresoObtenido = ingreso.obtenerIngreso();
        System.out.println("El ingreso obtenido es: " + ingresoObtenido);
        assertNotEquals(0, ingresoObtenido);
    }

    @Test
    void testHacerIngresoTipo1() {
        Ingreso ingreso = new Ingreso();
        double ingresoFinal = ingreso.hacerIngreso(1);
        double ingresoObtenido = ingreso.obtenerIngreso();
        System.out.println("El ingreso final es: " + ingresoFinal);
        assertEquals(ingresoObtenido * 0.85, ingresoFinal, 0.01);
    }

    @Test
    void testHacerIngresoTipo2() {
        Ingreso ingreso = new Ingreso();
        double ingresoFinal = ingreso.hacerIngreso(2);
        double ingresoObtenido = ingreso.obtenerIngreso();
        System.out.println("El ingreso final es: " + ingresoFinal);
        assertEquals(ingresoObtenido, ingresoFinal, 0.01);
    }

    @Test
    void testHacerIngresoTipoInvalido() {
        Ingreso ingreso = new Ingreso();
        double ingresoFinal = ingreso.hacerIngreso(3);
        System.out.println("El ingreso final es: " + ingresoFinal);
        assertEquals(0, ingresoFinal);
    }
}