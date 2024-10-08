import java.io.*;
import java.util.Scanner;
public class GestorNotas {
    private static final String ARCHIVO_NOTAS = "notas.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Gestor de Notas ---");
            System.out.println("1. Añadir nota");
            System.out.println("2. Ver todas las notas");
            System.out.println("3. Buscar notas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    anadirNota(scanner);
                    break;
                case 2:
                    verNotas();
                    break;
                case 3:
                    buscarNotas(scanner);
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
    private static void anadirNota(Scanner scanner) {
        System.out.print("Introduce el título de la nota: ");
        String titulo = scanner.nextLine();
        System.out.print("Introduce el contenido de la nota: ");
        String contenido = scanner.nextLine();
        try (PrintWriter writer = new PrintWriter(new
                FileWriter(ARCHIVO_NOTAS, true))) {
            writer.println(titulo + ":" + contenido);
            System.out.println("Nota añadida correctamente.");
        } catch (IOException e) {
            System.out.println("Error al añadir la nota: " +
                    e.getMessage());
        }
    }
    private static void verNotas() {
        try (BufferedReader reader = new BufferedReader(new
                FileReader(ARCHIVO_NOTAS))) {
            String linea;
            System.out.println("\n--- Todas las Notas ---");
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":", 2);
                System.out.println("Título: " + partes[0]);
                System.out.println("Contenido: " + partes[1]);
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            System.out.println("Error al leer las notas: " +
                    e.getMessage());
        }
    }
    private static void buscarNotas(Scanner scanner) {
        System.out.print("Introduce la palabra clave a buscar: ");
        String palabraClave = scanner.nextLine().toLowerCase();
        try (BufferedReader reader = new BufferedReader(new
                FileReader(ARCHIVO_NOTAS))) {
            String linea;
            boolean encontrada = false;
            System.out.println("\n--- Resultados de la búsqueda ---");
            while ((linea = reader.readLine()) != null) {
                if (linea.toLowerCase().contains(palabraClave)) {
                    String[] partes = linea.split(":", 2);
                    System.out.println("Título: " + partes[0]);
                    System.out.println("Contenido: " + partes[1]);
                    System.out.println("-------------------------");
                    encontrada = true;
                }
            } if (!encontrada) {
                System.out.println("No se encontraron notas con esa palabra clave.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar notas: " + e.getMessage());
        }
    }
}