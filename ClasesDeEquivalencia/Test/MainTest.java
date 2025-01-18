import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
    }

    @Test
    public void testMostrarMenuCliente() {
        BBDDLite bbdd = new BBDDLite();
        Scanner leer = new Scanner(System.in);
        String dni = "12345678Z";
        bbdd.agregarUsuario(dni);
        Cliente cliente = bbdd.obtenerCliente(dni);
        Main.mostrarMenuCliente(leer, cliente, 100.0);

        assertEquals(100.0, bbdd.obtenerCliente(cliente.getDni()).getSaldo(), 0.01);
    }

    @Test
    void mostrarMenuIngreso() {

        Scanner leer = new Scanner(System.in);
        Cliente cliente = new Cliente("77873460Z", 50);
        BBDDLite bbdd = new BBDDLite();
        bbdd.crearCliente(cliente);
        bbdd.actualizarSaldo(cliente.getDni(), 100.0);

        Main.mostrarMenuIngreso(leer, cliente);
        assertEquals(200.0, bbdd.obtenerCliente(cliente.getDni()).getSaldo(), 0.01);
    }

    @Test
    void mostrarMenuGastosConSaldoSuficiente() {
        // Arrange
        Scanner leer = new Scanner(System.in);
        Cliente cliente = new Cliente("dni", 50);
        BBDDLite bbdd = new BBDDLite();
        bbdd.crearCliente(cliente);
        bbdd.actualizarSaldo(cliente.getDni(), 100.0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Main.mostrarMenuGastos(leer, cliente);

        String output = outputStream.toString();
        assertTrue(output.contains("BIENVENIDO AL MENÚ DE GASTOS"));
        assertTrue(output.contains("1. Gasto Vacaciones"));
        assertTrue(output.contains("2. Gasto Alquiler"));
        assertTrue(output.contains("3. Gasto Vicios variados"));
        assertTrue(output.contains("4. Volver al menú principal"));

        assertEquals(50.0, bbdd.obtenerCliente(cliente.getDni()).getSaldo(), 0.01);
    }

    @Test
    void ingresarDNIValido() {
        Scanner leer = new Scanner(System.in);
        String dniEsperado = "12345678T";

        String dni = Main.ingresarDNI(leer);

        assertEquals(dniEsperado, dni);
    }

    @Test
    void ingresarDNINoValidoFormato() {
        // Arrange
        Scanner leer = new Scanner(System.in);
        leer.nextLine();
        String dniEsperado = "12345678T";

        String dni = Main.ingresarDNI(leer);

        assertEquals(dniEsperado, dni);
    }

    @Test
    void ingresarDNINoValidoLetra() {

        Scanner leer = new Scanner(System.in);
        leer.nextLine();
        String dniEsperado = "12345678T";

        String dni = Main.ingresarDNI(leer);

        assertEquals(dniEsperado, dni);
    }

    @Test
    void ingresarDNINoValidoLetraYNumeros() {
        Scanner leer = new Scanner(System.in);
        leer.nextLine();
        String dniEsperado = "12345678T";

        String dni = Main.ingresarDNI(leer);

        assertEquals(dniEsperado, dni);
    }
}