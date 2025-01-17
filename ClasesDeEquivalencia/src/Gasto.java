import java.util.InputMismatchException;
import java.util.Scanner;

public class Gasto {

    private int tipo;
    private double gastoFinal;

    public Gasto() {

    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    // Metodos para obtener y establecer el tipo de ingreso

    public double sacarDinero(int tipo, double saldo) {

        this.tipo = tipo;
        double saldoActual = saldo;
        double cantidad = obtenerCantidad(saldoActual);

        switch (tipo) {
            case 1: // Vacaciones
                gastoFinal = cantidad;
                break;

            case 2: // Alquiler
                gastoFinal = cantidad;
                break;

            case 3: // Vicios variados
                gastoFinal = cantidad;
                break;

            default:
                System.out.println("\n Esa opción no existe.");
                break;
        }

        return gastoFinal;
    }

    public double obtenerCantidad(double saldo) {
        Scanner scanner = new Scanner(System.in);
        double gasto = 0;

        while (true) {
            try {
                System.out.print("\n Introduce la cantidad de gasto: ");
                gasto = scanner.nextDouble();
                if (gasto <= 0) {
                    System.out.println("\n El gasto no puede ser negativo ni cero.");
                } else if (gasto > saldo) {
                    System.out.println("\n No tienes suficiente saldo para realizar este gasto. Introduce una cantidad menor.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n No puedes introducir letras. Debe ser un número válido.");
                scanner.next();
            }
        }
        return gasto;
    }
}

