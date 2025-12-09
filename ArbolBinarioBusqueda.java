import java.util.LinkedList;
//import java.util.Queue;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase ArbolBinarioBusqueda, almacena los nodos de tipo Palabra.
 * * @author Salvador Gonzalez Arellano (Adaptado para Proyecto Diccionario)
 * @version 1.1
 */
public class ArbolBinarioBusqueda {
    private Nodo raiz; // Raíz del árbol binario.

    /**
     * Constructor por defecto, asigna la raiz a null
     */
    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public ArbolBinarioBusqueda(Palabra dato) {
        raiz = new Nodo(dato);
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    /* -----------------------------------------------------------------
     * MÉTODOS DE BÚSQUEDA (Originales adaptados a Palabra)
     * ----------------------------------------------------------------- */

    private Nodo encontrarNodo(Nodo actual, Palabra buscado) {
        if (actual == null) { 
            return null;
        } else if (buscado.equals(actual.getDato())) { 
            return actual;
        } else if (actual.getDato().compareTo(buscado) > 0) {
            return encontrarNodo(actual.getIzquierdo(), buscado);
        } else {
            return encontrarNodo(actual.getDerecho(), buscado);
        }
    }

    public Nodo encontrarNodo(Palabra buscado) {
        return encontrarNodo(raiz, buscado);
    }
    
    // Método auxiliar para obtener frecuencia directo (Requisito)
    public int obtenerFrecuencia(String lexema) {
        try {
            Nodo res = encontrarNodo(new Palabra(lexema));
            if (res != null) return res.getDato().getFrecuencia();
        } catch (Exception e) {}
        return 0;
    }

    /* -----------------------------------------------------------------
     * MÉTODOS DE INSERCIÓN (MODIFICADO PARA FRECUENCIA)
     * ----------------------------------------------------------------- */

    /**
     * Intenta insertar un dato. Si ya existe, INCREMENTA LA FRECUENCIA.
     */
    private Nodo insertarDato(Nodo actual, Palabra dato) {
        if (actual == null) {
            return new Nodo(dato);
        } 
        
        int comparacion = actual.getDato().compareTo(dato);
        
        if (comparacion > 0) {
            actual.setIzquierdo(insertarDato(actual.getIzquierdo(), dato));
        } else if (comparacion < 0) {
            actual.setDerecho(insertarDato(actual.getDerecho(), dato));
        } else {
            // IGUALES: El dato ya existe. Requisito del proyecto: aumentar frecuencia.
            actual.getDato().incrementarFrecuencia();
        }
        return actual;
    }

    public boolean insertarDato(Palabra dato) {
        if (dato == null) 
            return false;
        // Nota: Modificamos la lógica original. Siempre devolvemos true porque
        // o se inserta nuevo o se actualiza la frecuencia.
        raiz = insertarDato(raiz, dato);
        return true;
    }

    /* -----------------------------------------------------------------
     * MÉTODOS DE RECORRIDO (Originales + getList para el proyecto)
     * ----------------------------------------------------------------- */

    private void recorrerInorden(Nodo actual) {
        if (actual != null) {
            recorrerInorden(actual.getIzquierdo());
            System.out.print(actual.getDato() + " "); // Usa toString de Palabra
            recorrerInorden(actual.getDerecho());
        }
    }

    public void recorrerInorden() {
        recorrerInorden(raiz);
        System.out.println();
    }
    
    // Auxiliar para obtener lista (necesario para reportes del main)
    public List<Palabra> getListaInOrden() {
        List<Palabra> lista = new LinkedList<>();
        llenarListaInOrden(raiz, lista);
        return lista;
    }
    
    private void llenarListaInOrden(Nodo actual, List<Palabra> lista) {
        if (actual != null) {
            llenarListaInOrden(actual.getIzquierdo(), lista);
            lista.add(actual.getDato());
            llenarListaInOrden(actual.getDerecho(), lista);
        }
    }

    /* -----------------------------------------------------------------
     * NUEVOS MÉTODOS REQUERIDOS (Rango y Top K)
     * ----------------------------------------------------------------- */

    public List<Palabra> rango(String a, String b) {
        List<Palabra> resultado = new LinkedList<>();
        if (a == null || b == null) return resultado;
        try {
            // Asegurar orden
            if (a.compareTo(b) > 0) { String t = a; a = b; b = t; }
            Palabra pMin = new Palabra(a);
            Palabra pMax = new Palabra(b);
            rangoRec(raiz, pMin, pMax, resultado);
        } catch(Exception e){}
        return resultado;
    }

    private void rangoRec(Nodo actual, Palabra min, Palabra max, List<Palabra> lista) {
        if (actual == null) return;
        Palabra d = actual.getDato();
        
        if (d.compareTo(min) > 0) rangoRec(actual.getIzquierdo(), min, max, lista);
        if (d.compareTo(min) >= 0 && d.compareTo(max) <= 0) lista.add(d);
        if (d.compareTo(max) < 0) rangoRec(actual.getDerecho(), min, max, lista);
    }

    public List<Palabra> palabrasMasFrecuentes(int k) {
        List<Palabra> todas = getListaInOrden();
        // Ordenamiento Burbuja Manual (por frecuencia descendente)
        int n = todas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Palabra p1 = todas.get(j);
                Palabra p2 = todas.get(j + 1);
                if (p2.getFrecuencia() > p1.getFrecuencia()) {
                    todas.set(j, p2);
                    todas.set(j + 1, p1);
                }
            }
        }
        // Retornar solo k
        List<Palabra> top = new ArrayList<>();
        for (int i = 0; i < Math.min(k, n); i++) top.add(todas.get(i));
        return top;
    }

    /* -----------------------------------------------------------------
     * OTROS MÉTODOS ORIGINALES DEL REPO (Sin cambios de lógica)
     * ----------------------------------------------------------------- */
    
    public void mostrarArbol() {
        System.out.println("----------------------------------------------------");
        mostrarArbol(raiz, -5);
        System.out.println("----------------------------------------------------");
    }

    private void mostrarArbol(Nodo actual, int espacio) {
        espacio += 10;
        if (actual == null) {
            System.out.println();
            for (int i = 0; i < espacio; i++) System.out.print(" ");
            System.out.println("null");
            return;
        }
        mostrarArbol(actual.getDerecho(), espacio);
        System.out.println();
        for (int i = 0; i < espacio; i++) System.out.print(" ");
        System.out.println(actual.getDato());
        mostrarArbol(actual.getIzquierdo(), espacio);
    }
}