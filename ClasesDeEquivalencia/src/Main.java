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
                        System.out.println("\n Usuario encontrado. Saldo: " + cliente.getSaldo() + "€");
                        mostrarMenuCliente(leer);
                    } else {
                        System.out.println("\n Usuario no encontrado.");
                    }
                    break;
                case 2:
                    String nuevoDni = ingresarDNI(leer);
                    BBDDLite.agregarUsuario(nuevoDni);
                    System.out.println("\n Usuario agregado correctamente. Tiene un saldo de 0€.");
                    mostrarMenuCliente(leer);
                    break;
                case 3:
                    System.out.println("\n Gracias por usar nuestros servicios");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 3);
    }

    public static void mostrarMenuCliente(Scanner leer) {
        int opcionSubmenu;
        do {
            System.out.println("\n ------- BIENVENIDO AL MENÚ CLIENTE: --------");
            System.out.println(" 1. Realizar ingreso");
            System.out.println(" 2. Realizar gasto");
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
                    System.out.println("\n Ha seleccionado realizar ingreso.");
                    mostrarMenuIngreso(leer);
                    break;
                case 2:
                    System.out.println("\n Ha seleccionado realizar gasto.");
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcionSubmenu != 3);
    }

    // Menú para los ingresos

    public static void mostrarMenuIngreso(Scanner leer) {
        int opcionSubmenu;
        do {
            System.out.println("\n ------- BIENVENIDO AL MENÚ DE INGRESOS: --------");
            System.out.println(" 1. Ingresar Nómina");
            System.out.println(" 2. Ingresar Venta en páginas de segunda mano");
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
                    System.out.println("Volviendo al menú de los clientes...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcionSubmenu != 3);
    }

        // Métodos para verificar los datos de entrada

    public static String ingresarDNI(Scanner leer) {
        String dni;
        String[] letras = null;
        do {
            System.out.print("Ingrese el DNI (8 dígitos y una letra): ");
            dni = leer.next().toUpperCase();
            if (!dni.matches("\\d{8}[A-Za-z]")) {
                System.out.println("El formato del DNI no es correcto.");
            } else {
                letras = new String[]{"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
                int numero = Integer.parseInt(dni.substring(0, 8));
                int resto = numero % 23;
                String letraCorrecta = letras[resto];
                if (!dni.substring(8).equals(letraCorrecta)) {
                    System.out.println("La letra del DNI no corresponde con el número.");
                }
            }
        } while (!dni.matches("\\d{8}[A-Za-z]") || !dni.substring(8).equals(letras[Integer.parseInt(dni.substring(0, 8)) % 23]));
        return dni;
    }

}