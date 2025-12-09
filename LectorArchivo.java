import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * LectorArchivo
 * 
 * Adaptado del Ejemplo2 del repositorio del curso (File, FileReader, BufferedReader).
 * Cambios realizados:
 * - Se agregó lógica para normalizar texto.
 * - Se dividieron las líneas en palabras.
 * - Se insertan objetos Palabra en un árbol binario de búsqueda.
 */
public class LectorArchivo {

    public static void cargarArchivo(String nombreArchivo, ArbolBinarioBusqueda<Palabra> arbol) {

        File archivo = null;
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            archivo = new File(nombreArchivo);
            reader = new FileReader(archivo);
            buffer = new BufferedReader(reader);

            String linea = buffer.readLine();

            while (linea!= null) {
                // Normalizar: pasar a minúsculas
                linea = linea.toLowerCase();

                // Reemplazar todo lo que no sea letra por espacio
                linea = linea.replaceAll("[^a-záéíóúüñ]", " ");

                // Dividir en palabras
                String[] palabras = linea.split("\\s+");

                for (String lexema : palabras) {

                    if (lexema.equals("")) {
                        continue; // evitar insertar vacíos
                    }

                    // Crear objeto Palabra
                    Palabra p = new Palabra(lexema);

                    // Insertar al árbol (incrementa frecuencia si ya existe)
                    arbol.insertar(p);
                }
            }

            System.out.println(">> Archivo cargado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e2) {
                System.out.println("Error al cerrar archivo.");
            }
        }
    }
}
