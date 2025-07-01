import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el monto total a repartir: ");
        double monto = scanner.nextDouble();

        double zarela = monto * 0.25;
        double carlos = monto * 0.15;
        double raul = monto * 0.47;
        double esther = monto * 0.13;

        System.out.println("Zarela recibe: S/ " + zarela);
        System.out.println("Carlos recibe: S/ " + carlos);
        System.out.println("Raul recibe: S/ " + raul);
        System.out.println("Esther recibe: S/ " + esther);
    }
}