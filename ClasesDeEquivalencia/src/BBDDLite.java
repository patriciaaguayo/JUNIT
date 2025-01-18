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

    public static boolean clienteExiste(String dni) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Usuarios WHERE dni = '" + dni + "'")) {
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("\n Error al conectar con la base de datos: " + e.getMessage());
            return false;
        }
    }

    public static void agregarUsuario(String dni) {
        String sql = "INSERT INTO Usuarios (dni, saldo) VALUES ('" + dni + "', 0)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("\n Error al añadir usuario: " + e.getMessage());
        }
    }

    // Método para actualizar el saldo de un usuario

    public static void actualizarSaldo(String dni, double nuevoSaldo) {
        String sql = "UPDATE Usuarios SET saldo = ? WHERE dni = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, nuevoSaldo);
            pstmt.setString(2, dni);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("\n Error al actualizar el saldo: " + e.getMessage());
        }
    }

    // Método para mostrar el saldo

    public static void verSaldo(String dni) {
        String sql = "SELECT saldo FROM Usuarios WHERE dni = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n Actualmente tienes un saldo de " + rs.getDouble("saldo") + "€");

                } else {
                    System.out.println("\n No se encontró información para el DNI " + dni);
                }
            }
        } catch (SQLException e) {
            System.out.println("\n Error al ver información: " + e.getMessage());
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

    private static void main(String[] args) {
        // Crear la tabla
        BBDDLite.crearTabla();
        System.out.println("\n Tabla creada correctamente.");

        // Ver la información de la base de datos
        BBDDLite.verInformacion();
    }

}
