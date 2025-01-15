import java.sql.*;

public class BBDDLite {

    // Crear la base de datos

    public static void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "dni TEXT PRIMARY KEY, " +
                "saldo REAL" +
                ")";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("\n Error al crear la tabla: " + e.getMessage());
        }
    }

    public static Cliente obtenerCliente(String dni) {
        String sql = "SELECT dni, saldo FROM Usuarios WHERE dni = '" + dni + "'";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return new Cliente(rs.getString("dni"), rs.getDouble("saldo"));
            }
        } catch (SQLException e) {
            System.out.println("\n Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    public static void agregarUsuario(String dni) {
        String sql = "INSERT INTO Usuarios (dni, saldo) VALUES ('" + dni + "', 0)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("\n Usuario añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("\n Error al añadir usuario: " + e.getMessage());
        }
    }

    // Método para ver toda la información de la BBDD

    public static void verInformacion() {
        String sql = "SELECT * FROM Usuarios";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("\n DNI: " + rs.getString("dni"));
                System.out.println(" Saldo: " + rs.getDouble("saldo") + "€");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error al ver información: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Crear la tabla
        BBDDLite.crearTabla();
        System.out.println("\n Tabla creada correctamente.");

        // Agregar un usuario
        BBDDLite.agregarUsuario("12345678Z");
        System.out.println("\n Usuario agregado correctamente.");

        // Ver la información de la base de datos
        BBDDLite.verInformacion();
    }

}
