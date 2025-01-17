import java.util.InputMismatchException;
import java.util.Scanner;

public class Ingreso {

    private int tipo;
    private double ingresoFinal;

    public Ingreso() {

    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    // Metodos para obtener y establecer el tipo de ingreso

    public double hacerIngreso(int tipo) {

        this.tipo = tipo;
        double cantidad = obtenerIngreso();

        switch (tipo) {
            case 1: // Nómina
                ingresoFinal = cantidad - (cantidad * 0.15);
                break;

            case 2: // Venta en páginas de segunda mano
                ingresoFinal = cantidad;
                break;

            default:
                System.out.println("\n Esa opción no existe.");
                break;
        }

        return ingresoFinal;
    }

    public double obtenerIngreso() {
        Scanner scanner = new Scanner(System.in);
        double ingreso = 0;

        while (true) {
            try {
                System.out.print("\n Introduce la cantidad de ingreso: ");
                ingreso = scanner.nextDouble();
                if (ingreso < 0) {
                    System.out.println("\n El ingreso no puede ser negativo.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n No puedes introducir letras. Debe ser un número válido.");
                scanner.next();
            }
        }
        return ingreso;
    }
}
