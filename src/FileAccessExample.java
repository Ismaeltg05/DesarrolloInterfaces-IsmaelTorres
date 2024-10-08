import java.io.*;
public class FileAccessExample {
    public static void main(String[] args) {
        writeTextFile();
        readTextFile();
    }
    public static void writeTextFile() {
        String fileName = "example.txt";
        try (FileWriter fileWriter = new FileWriter(fileName);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println("Esta es la primera línea del fichero.");
            printWriter.println("Esta es la segunda línea del fichero.");
            System.out.println("Fichero escrito correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " +
                    e.getMessage());
        }
    }
    public static void readTextFile() {
        String fileName = "example.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new
                FileReader(fileName))) {
            String line;
            System.out.println("Contenido del fichero:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}