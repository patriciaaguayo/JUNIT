import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in); // enteros
        int opcion;
        double saldo;

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
                    saldo = cliente.getSaldo();
                    if (cliente != null) {
                        System.out.println("\n Usuario encontrado. Tienes un saldo de " + cliente.getSaldo() + "€");
                        mostrarMenuCliente(leer, cliente, saldo);
                    } else {
                        System.out.println("\n Usuario no encontrado.");
                    }
                    break;
                case 2:
                    String nuevoDni = ingresarDNI(leer);
                    if (BBDDLite.clienteExiste(nuevoDni)) {
                        System.out.println("\n El cliente con DNI " + nuevoDni + " ya existe.");
                    } else {
                        BBDDLite.agregarUsuario(nuevoDni);
                        Cliente nuevoCliente = BBDDLite.obtenerCliente(nuevoDni);
                        saldo = nuevoCliente.getSaldo();
                        System.out.println("\n Usuario agregado correctamente. Tienes un saldo de " + nuevoCliente.getSaldo() + "€");
                        mostrarMenuCliente(leer, nuevoCliente, saldo);
                    }
                    break;
                case 3:
                    System.out.println("\n Gracias por usar nuestros servicios");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 3);
    }

    public static void mostrarMenuCliente(Scanner leer, Cliente cliente, double saldo) {
        int opcionSubmenu;

        do {
            // Obtener el saldo actualizado del cliente desde la base de datos
            saldo = BBDDLite.obtenerCliente(cliente.getDni()).getSaldo();

            System.out.println("\n Tu saldo actual es de " + saldo + "€");

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
                    mostrarMenuIngreso(leer, cliente);
                    break;
                case 2:
                    if (saldo == 0) {
                        System.out.println("\n No tienes suficiente saldo para realizar un gasto. Primero debes realizar un ingreso.");
                    } else {
                        System.out.println("\n Ha seleccionado realizar gasto.");
                        mostrarMenuGastos(leer, cliente);
                    }
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

    public static void mostrarMenuIngreso(Scanner leer, Cliente cliente) {

        int opcionSubmenu;
        Ingreso ingreso = new Ingreso();
        double ingresoCantidad;
        double saldo = BBDDLite.obtenerCliente(cliente.getDni()).getSaldo();

        do {

            System.out.println("\n Tu saldo actual es de " + saldo + "€");

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
                    ingresoCantidad = ingreso.hacerIngreso(1);
                    saldo += ingresoCantidad;
                    BBDDLite.actualizarSaldo(cliente.getDni(), saldo);
                    System.out.println("\n Ingreso de nómina realizado.");
                    BBDDLite.verSaldo(cliente.getDni());
                    break;
                case 2:
                    ingresoCantidad = ingreso.hacerIngreso(2);
                    saldo += ingresoCantidad;
                    BBDDLite.actualizarSaldo(cliente.getDni(), saldo);
                    System.out.println("\n Ingreso de venta en páginas de segunda mano realizado.");
                    BBDDLite.verSaldo(cliente.getDni());
                    break;
                case 3:
                    System.out.println("\n Volviendo al menú de los clientes...");
                    break;
                default:
                    System.out.println("\n Opción no válida, intente nuevamente.");
            }
        } while (opcionSubmenu != 3);
    }

    // Menú para los gastos

    public static void mostrarMenuGastos(Scanner leer, Cliente cliente) {

        int opcionSubmenu;
        Gasto gasto = new Gasto();
        double gastoCantidad;
        double saldo = BBDDLite.obtenerCliente(cliente.getDni()).getSaldo();

        do {

            if (saldo == 0) {
                System.out.println("\n No tienes saldo suficiente para realizar un gasto. Debes hacer un ingreso antes.");
                break;
            }

            System.out.println("\n ------- BIENVENIDO AL MENÚ DE GASTOS: --------");
            System.out.println(" 1. Gasto Vacaciones");
            System.out.println(" 2. Gasto Alquiler");
            System.out.println(" 3. Gasto Vicios variados");
            System.out.println(" 4. Volver al menú principal");
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
                    gastoCantidad = gasto.sacarDinero(1, saldo);
                    if (gastoCantidad > saldo) {
                        System.out.println("\n No tienes saldo suficiente para realizar este gasto.");
                    } else {
                        saldo -= gastoCantidad;
                        BBDDLite.actualizarSaldo(cliente.getDni(), saldo);
                        System.out.println("\n Gasto de vacaciones realizado.");
                        BBDDLite.verSaldo(cliente.getDni());
                    }
                    break;
                case 2:
                    gastoCantidad = gasto.sacarDinero(2, saldo);
                    if (gastoCantidad > saldo) {
                        System.out.println("\n No tienes saldo suficiente para realizar este gasto.");
                    } else {
                        saldo -= gastoCantidad;
                        BBDDLite.actualizarSaldo(cliente.getDni(), saldo);
                        System.out.println("\n Gasto de alquiler realizado.");
                        BBDDLite.verSaldo(cliente.getDni());
                    }
                    break;
                case 3:
                    gastoCantidad = gasto.sacarDinero(3, saldo);
                    if (gastoCantidad > saldo) {
                        System.out.println("\n No tienes saldo suficiente para realizar este gasto.");
                    } else {
                        saldo -= gastoCantidad;
                        BBDDLite.actualizarSaldo(cliente.getDni(), saldo);
                        System.out.println("\n Gasto de vicios variados realizado.");
                        BBDDLite.verSaldo(cliente.getDni());
                    }
                    break;

                case 4:
                    System.out.println("\n Volviendo al menú de los clientes...");
                    break;
                default:
                    System.out.println("\n Opción no válida, intente nuevamente.");
            }
        } while (opcionSubmenu != 4);
    }

        // Método para verrificar el formato del DNI

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