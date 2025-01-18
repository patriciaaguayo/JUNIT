import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GastoTest {

    @Test
    void getTipo() {
        Gasto gasto = new Gasto();
        gasto.setTipo(2); // Simular tipo "Alquiler"
        System.out.println("Probando getTipo con tipo 2 (Alquiler)");
        assertEquals(2, gasto.getTipo(), "El tipo de gasto debería ser 2");
    }

    @Test
    void setTipo() {
        Gasto gasto = new Gasto();
        gasto.setTipo(1); // Simular tipo "Vacaciones"
        System.out.println("Probando setTipo con tipo 1 (Vacaciones)");
        assertEquals(1, gasto.getTipo(), "El tipo de gasto debería ser 1");
    }

    @Test
    void sacarDinero() {
        Gasto gasto = new Gasto();
        double saldoInicial = 500;
        double gastoCantidad = 100;

        System.out.println("Probando sacarDinero con tipo 1 (Vacaciones) y saldo inicial 500, gasto 100");
        double resultado = gasto.sacarDinero(1, saldoInicial - gastoCantidad);
        assertEquals(gastoCantidad, resultado, "El gasto para Vacaciones debería ser igual a 100");

        System.out.println("Probando sacarDinero con tipo 2 (Alquiler) y saldo inicial 500, gasto 100");
        resultado = gasto.sacarDinero(2, saldoInicial - gastoCantidad);
        assertEquals(gastoCantidad, resultado, "El gasto para Alquiler debería ser igual a 100");
    }

    @Test
    void obtenerCantidad() {
        Gasto gasto = new Gasto();

        double saldo = 500;
        double gastoSimulado = 100;

        System.out.println("Probando obtenerCantidad con saldo 500 y gasto simulado 100");
        double resultado = gasto.obtenerCantidad(saldo);
        assertTrue(resultado > 0 && resultado <= saldo, "La cantidad de gasto debería ser válida y menor o igual al saldo disponible");
    }
}
