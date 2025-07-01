import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String gama;
        int cantidad;
        double precioUnitario = 0;
        final double TIPO_CAMBIO = 3.50;

        System.out.print("Ingrese la gama del celular (baja, media, alta, otro): ");
        gama = scanner.nextLine().toLowerCase();

        System.out.print("Ingrese la cantidad de celulares: ");
        cantidad = scanner.nextInt();

        switch (gama) {
            case "baja":
                precioUnitario = 400;
                break;
            case "media":
                precioUnitario = 600;
                break;
            case "alta":
                precioUnitario = 1200;
                break;
            default:
                precioUnitario = 150;
        }

        double totalDolares = precioUnitario * cantidad;

        if (cantidad >= 2) {
            totalDolares *= 0.90;
        }

        double totalSoles = totalDolares * TIPO_CAMBIO;

        System.out.printf("Total a pagar en soles: S/ %.2f\n", totalSoles);

        scanner.close();
    }
}