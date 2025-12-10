import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase ArbolBinarioBusqueda (Adaptado para el proyecto)
 * Almacena los nodos de tipo Palabra 
 * se le agrego el metodo para mostrar el top k palabras mas frecuentes, un metodo para obtener la
 * frecuencia del nodo, otro para llenar una lista con los datos en InOrden y otro que los regresa.
 * Se eliminaron metodos que no se utilizan y se modificaron los que se utilizan para que funcionaran 
 * con el nodo que contiene la palabra.
 */
public class ArbolBinarioBusqueda 
{
    private Nodo raiz; 
    //Constructor que asigna a la raiz el valor de null
    public ArbolBinarioBusqueda() 
    {
        raiz= null;
    }
    public ArbolBinarioBusqueda(Palabra dato) 
    {
        raiz=new Nodo(dato);
    }
    //Metodo get del nodo
    public Nodo getRaiz() 
    {
        return raiz;
    }
    //Metodo que revisa si el arbol esta vacio (si la raiz es nula esta vacio)
    public boolean esVacio() 
    {
        return raiz== null;
    }
    //Metodo que encuentra un Nodo en el arbol a partir de la palabra que contiene
    private Nodo encontrarNodo(Nodo actual, Palabra buscado) 
    {
        if (actual== null) 
        { 
            return null;
        } else if (buscado.equals(actual.getDato())) { 
            return actual;
        } else if (actual.getDato().compareTo(buscado)>0) {
            return encontrarNodo(actual.getIzquierdo(), buscado);
        } else {
            return encontrarNodo(actual.getDerecho(), buscado);
        }
    }
    public Nodo encontrarNodo(Palabra buscado) 
    {
        return encontrarNodo(raiz, buscado);
    }
     // Método auxiliar para obtener frecuencia directamente
    public int obtenerFrecuencia(String lexema) 
    {
        try 
        {
            Nodo res= encontrarNodo(new Palabra(lexema));
            if (res!=null) 
                return res.getDato().getFrecuencia();
        } catch (Exception e) {}
        return 0;
    }
    //Metodo que inserta un dato, pero si el dato ya esta en el arbol solo aumenta el numero en la frecuencia 
    private Nodo insertarDato(Nodo actual, Palabra dato) 
    {
        if (actual==null) 
        {
            return new Nodo(dato);
        } 
        int comparacion = actual.getDato().compareTo(dato);
        if (comparacion> 0) 
        {
            actual.setIzquierdo(insertarDato(actual.getIzquierdo(),dato));
        } else if (comparacion< 0) {
            actual.setDerecho(insertarDato(actual.getDerecho(),dato));
        } else {
            actual.getDato().incrementarFrecuencia();
        }
        return actual;
    }
    public boolean insertarDato(Palabra dato) 
    {
        if (dato==null) 
            return false;
        raiz=insertarDato(raiz, dato);
        return true;
    }
    //Metodo que recorre el arbol en InOrden
    private void recorrerInorden(Nodo actual) 
    {
        if (actual!= null) 
        {
            recorrerInorden(actual.getIzquierdo());
            System.out.print(actual.getDato() + " ");
            recorrerInorden(actual.getDerecho());
        }
    }
    public void recorrerInorden() 
    {
        recorrerInorden(raiz);
        System.out.println();
    }
    //Metodo que genera una lista ligada con los datos en InOrden
    private void llenarListaInOrden(Nodo actual, List<Palabra> lista) 
    {
        if (actual!= null) 
        {
            llenarListaInOrden(actual.getIzquierdo(), lista);
            lista.add(actual.getDato());
            llenarListaInOrden(actual.getDerecho(), lista);
        }
    }
    //El metodo get para acceder a la lista
    public List<Palabra> getListaInOrden() 
    {
        List<Palabra> lista= new LinkedList<>();
        llenarListaInOrden(raiz, lista);
        return lista;
    }
    //Metodo que devuelve una lista con las k palabras más frecuentes
    public List<Palabra> palabrasMasFrecuentes(int k) 
    {
        List<Palabra> todas= getListaInOrden();
        // Ordenamiento Burbuja (Lo sacamos del repositorio de Apaloo y lo adaptamos) por frecuencia descendente
        int n= todas.size();
        for (int i = 0; i < n - 1; i++) 
            {
            for (int j = 0; j < n - i - 1; j++) 
                {
                Palabra p1=todas.get(j);
                Palabra p2=todas.get(j+1);
                if (p2.getFrecuencia()> p1.getFrecuencia()) 
                {
                    todas.set(j, p2);
                    todas.set(j+1, p1);
                }
            }
        }
        //Regresa solo los primeros k
        List<Palabra> top=new ArrayList<>();
        for (int i= 0; i< Math.min(k, n); i++) top.add(todas.get(i));
        return top;
    }

    //Metodo que muestra el arbol y su estructura
    public void mostrarArbol() 
    {
        System.out.println("----------------------------------------------------");
        mostrarArbol(raiz, -5);
        System.out.println("----------------------------------------------------");
    }
    private void mostrarArbol(Nodo actual, int espacio) 
    {
        espacio+=10;
        if (actual == null) 
        {
            System.out.println();
            for (int i=0; i<espacio;i++) 
                System.out.print(" ");
            System.out.println("null");
            return;
        }
        mostrarArbol(actual.getDerecho(), espacio);
        System.out.println();
        for (int i = 0; i < espacio; i++) 
            System.out.print(" ");
        System.out.println(actual.getDato());
        mostrarArbol(actual.getIzquierdo(), espacio);
    }
}