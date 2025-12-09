import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArbolBinarioBusqueda<Palabra> arbol = new ArbolBinarioBusqueda<>();
        Scanner sc = new Scanner(System.in);

        boolean seguir = true;

        System.out.println("===== CARGA INICIAL DE ARCHIVO =====");
        System.out.print("Ingresa el nombre del archivo .txt (ej: TextoPrueba.txt): ");
        String nombreArchivo = sc.nextLine().trim();

        cargarArchivo(nombreArchivo, arbol);   //  AQUÍ SE CARGA EL ARCHIVO

        while (seguir) {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Insertar palabra");
            System.out.println("2. Consultar frecuencia");
            System.out.println("3. Mostrar recorrido inOrden");
            System.out.println("4. Consultar rango [a, b]");
            System.out.println("5. Mostrar k palabras más frecuentes");
            System.out.println("6. Salir");
            System.out.print("Selecciona: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1 -> {
                    System.out.print("Ingresa palabra: ");
                    String w = sc.nextLine().toLowerCase().trim();
                    arbol.insertarPalabra(new Palabra(w));
                    System.out.println("Listo, insertada.");
                }

                case 2 -> {
                    System.out.print("Ingresa palabra: ");
                    String w = sc.nextLine().toLowerCase().trim();
                    int f = arbol.frecuencia(w);
                    System.out.println("Frecuencia de '" + w + "': " + f);
                }

                case 3 -> {
                    System.out.println("Recorrido inOrden:");
                    arbol.recorrerInorden();
                    System.out.println();
                }

                case 4 -> {
                    System.out.print("Palabra inicial: ");
                    String a = sc.nextLine().trim().toLowerCase();

                    System.out.print("Palabra final: ");
                    String b = sc.nextLine().trim().toLowerCase();

                    List<Palabra> lista = arbol.rango(a, b);

                    System.out.println("Resultados en el rango:");
                    for (Palabra pa : lista) {
                        System.out.println(pa.getLexema() + " (" + pa.getFrecuencia() + ")");
                    }
                }

                case 5 -> {
                    System.out.print("k = ");
                    int k = sc.nextInt();
                    sc.nextLine();

                    List<Palabra> top = arbol.palabrasMasFrecuentes(k);
                    System.out.println("Top " + k + " palabras más frecuentes:");

                    for (Palabra pa : top) {
                        System.out.println(pa.getLexema() + ": " + pa.getFrecuencia());
                    }
                }

                case 6 -> {
                    seguir = false;
                    System.out.println("Adiós :)");
                }

                default -> System.out.println("Opción inválida");
            }
        }

        sc.close();
    }

    // ==========================================================
    // MÉTODO PARA LEER ARCHIVO (adaptado del repositorio)
    // ==========================================================

    public static void cargarArchivo(String nombreArchivo, ArbolBinarioBusqueda<Palabra> arbol) {

        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            System.out.println("ERROR: No se encontró el archivo: " + nombreArchivo);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                // Normalizar la línea
                linea = linea.toLowerCase();

                // Reemplazar signos raros por espacio
                linea = linea.replaceAll("[^a-záéíóúñ]", " ");

                // Separar por espacios
                String[] palabras = linea.split("\\s+");

                // Insertar cada palabra válida
                for (String w : palabras) {
                    if (!w.isBlank()) {
                        arbol.insertarPalabra(new Palabra(w));
                    }
                }
            }

            System.out.println("Archivo cargado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al leer el archivo.");
            e.printStackTrace();
        }
    }
}