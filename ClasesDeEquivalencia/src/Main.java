import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in); // enteros
        int opcion;

        BBDDLite.crearTabla();

        do {
            System.out.println("\n ------ MENU PRINCIPAL: ------");
            System.out.println(" 1. Buscar usuario");
            System.out.println(" 2. Añadir usuario");
            System.out.println(" 3. Salir");
            System.out.print(" Elija una opción: ");

            while (!leer.hasNextInt()) {
                System.out.println("\n Entrada no válida. Introduzca un número entero positivo: ");
                leer.next();
            }
            opcion = leer.nextInt();

            if (opcion < 1) {
                System.out.println("\n El número debe ser positivo. Intente nuevamente.");
                continue;
            }

            switch (opcion) {
                case 1:
                    String dniBuscar = ingresarDNI(leer);
                    Cliente cliente = BBDDLite.obtenerCliente(dniBuscar);
                    if (cliente != null) {
                        System.out.println("Usuario encontrado. Saldo: " + cliente.getSaldo());
                        mostrarSubmenu(leer);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 2:
                    String nuevoDni = ingresarDNI(leer);
                    BBDDLite.agregarUsuario(nuevoDni);
                    mostrarSubmenu(leer);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 3);
    }

    public static void mostrarSubmenu(Scanner leer) {
        int opcionSubmenu;
        do {
            System.out.println("\n ------- BIENVENIDO AL MENÚ CLIENTE: --------");
            System.out.println(" 1. Subopción A");
            System.out.println(" 2. Subopción B");
            System.out.println(" 3. Volver al menú principal");
            System.out.print(" Elija una opción: ");

            while (!leer.hasNextInt()) {
                System.out.println("\n Entrada no válida. Introduzca un número entero positivo: ");
                leer.next();
            }
            opcionSubmenu = leer.nextInt();

            if (opcionSubmenu < 1) {
                System.out.println("\n El número debe ser positivo. Intente nuevamente.");
                continue;
            }

            switch (opcionSubmenu) {
                case 1:
                    System.out.println("Ha seleccionado Subopción A.");
                    break;
                case 2:
                    System.out.println("Ha seleccionado Subopción B.");
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcionSubmenu != 3);
    }

        // Métodos para verificar los datos de entrada

    public static String ingresarDNI(Scanner leer) {
        String dni;
        do {
            System.out.print("Ingrese el DNI (8 dígitos y una letra): ");
            dni = leer.next();
        } while (!dni.matches("\\d{8}[A-Za-z]"));
        return dni;
    }

}