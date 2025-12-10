import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *  Clase LectorArchivo
 * Adaptado del Ejemplo2 del repositorio del curso de Apaloo (File, FileReader, BufferedReader).
 * - Se agregó lógica para normalizar texto del archivo
 * - Divide las lineas en palabras
 * - Inserta objetos Palabra en un árbol binario de búsqueda.
 */
public class LectorArchivo 
{
    public static void cargarArchivo(String nombreArchivo, ArbolBinarioBusqueda arbol) 
    {
        File archivo=null;
        FileReader reader= null;
        BufferedReader buffer= null;
        try 
        {
            archivo= new File(nombreArchivo);
            reader= new FileReader(archivo);
            buffer= new BufferedReader(reader);

            String linea=buffer.readLine();
            while (linea!= null) 
            {
                // Pasa toda la linea a minusculas
                linea= linea.toLowerCase();
                //Reemplaza todo lo que no sea una letra "normal" por un espacio
                linea=linea.replaceAll("[^a-záéíóúüñ]", " ");
                // Divide la linea en palabras con el metodo Split
                String[] palabras= linea.split("\\s+");
                for (String lexema : palabras) 
                {
                    //Evita que se puedan insertar vacios
                    if (lexema.equals("")) 
                    {
                        continue; 
                    }
                    Palabra p=new Palabra(lexema);
                    //Insertar al árbol (incrementa frecuencia si ya existe)
                    arbol.insertarDato(p);
                }
            }
        System.out.println(">> Archivo cargado correctamente :D");
        } catch (Exception e) {
            System.out.println("Error al intentar leer el archivo: " + e.getMessage());
        } finally {
            //Se cierra el archivo si todo sale bien, pero si algo sale mal tira un mensaje de error
            try {
                if (reader!= null) 
                {
                    reader.close();
                }
            } catch (Exception e2) {
                System.out.println("Error al cerrar archivo");
            }
        }
    }
}