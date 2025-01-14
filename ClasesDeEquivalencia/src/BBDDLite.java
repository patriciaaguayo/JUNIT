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
            System.out.println("Error al crear la tabla: " + e.getMessage());
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
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    public static void agregarUsuario(String dni) {
        String sql = "INSERT INTO Usuarios (dni, saldo) VALUES ('" + dni + "', 0)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Usuario añadido correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al añadir usuario: " + e.getMessage());
        }
    }
}
