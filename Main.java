import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class Main 
{

    public static void main(String[] args) 
    {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        Scanner sc = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~DICCIONARIO CON ARBOL BINARIO~~~~~~~~~~~~~~~~~~~");
        System.out.print("Nombre del archivo con terminacion .txt : ");
        String nombreArchivo = sc.nextLine();
        cargarArchivo(nombreArchivo, arbol);

        boolean salir = false;
        while (salir==false) 
        {
            System.out.println("--------------------MENU--------------------");
            System.out.println("INGRESA EL NUMERO DE LA OPCION DESEADA");
            System.out.println("\n1.Insertar Palabra");
            System.out.println("2.Frecuencia de una palabra");
            System.out.println("3.Ver Todo");
            System.out.println("4.Top K en frecuencia");
            System.out.println("5.Ver Arbol");
            System.out.println("6.Salir");
            System.out.print("------->");
            
            try 
            {
                int op = Integer.parseInt(sc.nextLine());
                switch (op) 
                {
                    case 1:
                        System.out.print("Palabra: ");
                        arbol.insertarDato(new Palabra(sc.nextLine()));
                        break;
                    case 2:
                        System.out.print("Palabra a busacar: ");
                        System.out.println("Frecuencia: " + arbol.obtenerFrecuencia(sc.nextLine()));
                        break;
                    case 3:
                        arbol.recorrerInorden();
                        break;
                    case 4:
                        System.out.print("k: ");
                        int k=Integer.parseInt(sc.nextLine());
                        List<Palabra> tops = arbol.palabrasMasFrecuentes(k);
                        for(Palabra p : tops) 
                            System.out.println(p);
                        break;
                    case 5:
                        arbol.mostrarArbol();
                        break;
                    case 6: 
                        salir= true; 
                        break;
                }
            } catch (Exception e) 
            {
                System.out.println("Error de entrada.");
            }
        }
        sc.close();
    }

    public static void cargarArchivo(String ruta, ArbolBinarioBusqueda arbol) 
    {
        File archivo = new File(ruta);
        if (!archivo.exists()) 
        {
            System.out.println("Archivo no encontrado :(");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) 
        {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null) 
            {
                linea = linea.toLowerCase().replaceAll("[^a-záéíóúüñ]", " ");
                for (String w : linea.split("\\s+")) 
                {
                    if (!w.isEmpty()) 
                    {
                        arbol.insertarDato(new Palabra(w));
                        contador= contador+1;
                    }
                }
            }
            System.out.println("Archivo cargado. " + contador + " palabras procesadas.");
        } catch (Exception e) {
            System.out.println("Error leyendo archivo.");
        }
    }
}