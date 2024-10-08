import java.io.*;
import java.util.Scanner;
public class RegistroGastos {
    private static final String ARCHIVO_GASTOS = "gastos.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Registro de Gastos Personales ---");
            System.out.println("1. Añadir gasto");
            System.out.println("2. Ver todos los gastos");
            System.out.println("3. Calcular total de gastos");
            System.out.println("4. Ver gastos por categoría");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    anadirGasto(scanner);
                    break;
                case 2:
                    verGastos();
                    break;
                case 3:
                    calcularTotalGastos();
                    break;
                case 4:
                    verGastosPorCategoria(scanner);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }
    private static void anadirGasto(Scanner scanner) {
        System.out.print("Introduce la fecha (DD/MM/YYYY): ");
        String fecha = scanner.nextLine();
        System.out.print("Introduce la categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Introduce la descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Introduce la cantidad: ");
        double cantidad = scanner.nextDouble();
        try (PrintWriter writer = new PrintWriter(new
                FileWriter(ARCHIVO_GASTOS, true))) {
            writer.println(fecha + "," + categoria + "," + descripcion + ","
                    + cantidad);
            System.out.println("Gasto registrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al registrar el gasto: " +
                    e.getMessage());
        }
    }
    private static void verGastos() {
        try (BufferedReader reader = new BufferedReader(new
                FileReader(ARCHIVO_GASTOS))) {
            String linea;
            System.out.println("\n--- Todos los Gastos ---");
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                System.out.println("Fecha: " + partes[0] + ", Categoría: " +
                                partes[1] +
                                ", Descripción: " + partes[2] + ", cantidad: $" + partes[3]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer los gastos: " +
                    e.getMessage());
        }
    }
    private static void calcularTotalGastos() {
        double total = 0;
        try (BufferedReader reader = new BufferedReader(new
                FileReader(ARCHIVO_GASTOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                total += Double.parseDouble(partes[3]);
            }
            System.out.println("Total de gastos: $" + total);
        } catch (IOException e) {
            System.out.println("Error al calcular el total de gastos: " +
                    e.getMessage());
        }
    }
    private static void verGastosPorCategoria(Scanner scanner) {
        System.out.print("Introduce la categoría a buscar: ");
        String categoriaBuscada = scanner.nextLine().toLowerCase();
        try (BufferedReader reader = new BufferedReader(new
                FileReader(ARCHIVO_GASTOS))) {
            String linea;
            boolean encontrado = false;
            System.out.println("\n--- Gastos de la categoría '" +
                    categoriaBuscada + "' ---");
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes[1].toLowerCase().equals(categoriaBuscada)) {
                    System.out.println("Fecha: " + partes[0] + ", Descripción: " + partes[2] + ", cantidad: $" + partes[3]);
                    encontrado = true;
                }
            }
            if (!encontrado) {
            System.out.println("No se encontraron gastos en esta categoría.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar gastos por categoría: " +
                e.getMessage());
        }
    }
}