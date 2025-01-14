public class Cliente {

    String dni;
    double saldo = 0.0;

    public Cliente(String dni, double saldo) {
        this.dni = dni;
        this.saldo = saldo;
    }

    public String getDni() {
        return dni;
    }

    public double getSaldo() {
        return saldo;
    }
}
