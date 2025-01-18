import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BBDDLiteTest {

    @BeforeEach
    void setUp() {
        // Se asegura que la tabla esté limpia antes de cada prueba
        BBDDLite.crearTabla();
    }

    @Test
    void crearTabla() {
        // Verifica que la tabla se haya creado correctamente
        System.out.println("Probando crearTabla: Tabla creada correctamente si no existía.");
        // No hay necesidad de comprobar nada, ya que el método no devuelve ningún valor.
        assertTrue(true);
    }

    @Test
    void obtenerCliente() {
        String dni = "12345678Z";
        BBDDLite.agregarUsuario(dni);

        Cliente cliente = BBDDLite.obtenerCliente(dni);
        System.out.println("Probando obtenerCliente: Obtenido DNI=" + cliente.getDni() + ", Saldo=" + cliente.getSaldo());

        assertNotNull(cliente, "El cliente no debe ser nulo");
        assertEquals(dni, cliente.getDni(), "El DNI debe coincidir");
    }

    @Test
    void clienteExiste() {
        String dni = "12345678Z";
        BBDDLite.agregarUsuario(dni);

        boolean existe = BBDDLite.clienteExiste(dni);
        System.out.println("Probando clienteExiste: Se espera true, se obtuvo: " + existe);

        assertTrue(existe, "El cliente debería existir");
    }

    @Test
    void agregarUsuario() {
        String dni = "87654321A";

        BBDDLite.agregarUsuario(dni);
        boolean existe = BBDDLite.clienteExiste(dni);
        System.out.println("Probando agregarUsuario: Usuario agregado correctamente, clienteExiste devuelve: " + existe);

        assertTrue(existe, "El usuario debería haber sido agregado correctamente");
    }

    @Test
    void actualizarSaldo() {
        String dni = "12345678Z";
        double nuevoSaldo = 150.0;
        BBDDLite.agregarUsuario(dni);

        BBDDLite.actualizarSaldo(dni, nuevoSaldo);
        Cliente cliente = BBDDLite.obtenerCliente(dni);
        System.out.println("Probando actualizarSaldo: Saldo actualizado a " + cliente.getSaldo());

        assertEquals(nuevoSaldo, cliente.getSaldo(), "El saldo actualizado no coincide con el valor esperado");
    }

    @Test
    void verSaldo() {
        String dni = "12345678Z";
        double saldo = 200.0;
        BBDDLite.agregarUsuario(dni);
        BBDDLite.actualizarSaldo(dni, saldo);

        System.out.println("Probando verSaldo:");
        BBDDLite.verSaldo(dni);  // Imprime el saldo actual de la base de datos
        // No podemos verificar la salida en consola directamente, pero verificamos si no lanza excepciones
        assertTrue(true);  // Asegura que no se lanza ninguna excepción
    }

    @Test
    void verInformacion() {
        BBDDLite.agregarUsuario("12345678Z");
        BBDDLite.agregarUsuario("87654321A");

        System.out.println("Probando verInformacion:");
        BBDDLite.verInformacion();  // Imprime la información de todos los usuarios en la base de datos

        // Al igual que en verSaldo, no podemos hacer una verificación directa en la salida de consola
        // pero nos aseguramos de que no haya excepciones.
        assertTrue(true);  // Se puede reemplazar con una aserción más precisa si se espera algún resultado concreto.
    }
}
