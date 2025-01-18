import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void getDni() {
        Cliente cliente = new Cliente("12345678Z", 100.0);
        String dni = cliente.getDni();
        System.out.println("Probando getDni: Se espera '12345678Z', se obtuvo: " + dni);
        assertEquals("12345678Z", dni);
    }

    @Test
    void getSaldo() {
        Cliente cliente = new Cliente("12345678Z", 100.0);
        double saldo = cliente.getSaldo();
        System.out.println("Probando getSaldo: Se espera '100.0', se obtuvo: " + saldo);
        assertEquals(100.0, saldo);
    }
}
