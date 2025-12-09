import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        Scanner sc = new Scanner(System.in);

        // 1. CARGA AUTOMÁTICA O MANUAL DEL ARCHIVO
        System.out.println("=== DICCIONARIO ===");
        System.out.print("Nombre del archivo (ej: texto.txt): ");
        String nombreArchivo = sc.nextLine();
        cargarArchivo(nombreArchivo, arbol);

        // 3. MENÚ INTERACTIVO
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Insertar Palabra");
            System.out.println("2. Ver Frecuencia");
            System.out.println("3. Ver Todo (A-Z)");
            System.out.println("4. Rango [a-b]");
            System.out.println("5. Top K Frecuentes");
            System.out.println("6. Ver Arbol (Gráfico)");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
            
            try {
                int op = Integer.parseInt(sc.nextLine());
                switch (op) {
                    case 1:
                        System.out.print("Palabra: ");
                        arbol.insertarDato(new Palabra(sc.nextLine()));
                        break;
                    case 2:
                        System.out.print("Buscar: ");
                        System.out.println("Frecuencia: " + arbol.obtenerFrecuencia(sc.nextLine()));
                        break;
                    case 3:
                        arbol.recorrerInorden();
                        break;
                    case 4:
                        System.out.print("Inicio: "); String a = sc.nextLine();
                        System.out.print("Fin: "); String b = sc.nextLine();
                        List<Palabra> ran = arbol.rango(a, b);
                        for(Palabra p : ran) System.out.println(p);
                        break;
                    case 5:
                        System.out.print("k: ");
                        int k = Integer.parseInt(sc.nextLine());
                        List<Palabra> tops = arbol.palabrasMasFrecuentes(k);
                        for(Palabra p : tops) System.out.println(p);
                        break;
                    case 6:
                        arbol.mostrarArbol();
                        break;
                    case 7: 
                        salir = true; 
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error de entrada.");
            }
        }
        sc.close();
    }

    // Lógica de lectura adaptada
    public static void cargarArchivo(String ruta, ArbolBinarioBusqueda arbol) {
        File f = new File(ruta);
        if (!f.exists()) {
            System.out.println("Archivo no encontrado.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null) {
                linea = linea.toLowerCase().replaceAll("[^a-záéíóúüñ]", " ");
                for (String w : linea.split("\\s+")) {
                    if (!w.isEmpty()) {
                        arbol.insertarDato(new Palabra(w));
                        count++;
                    }
                }
            }
            System.out.println("Archivo cargado. " + count + " palabras procesadas.");
        } catch (Exception e) {
            System.out.println("Error leyendo archivo.");
        }
    }
}