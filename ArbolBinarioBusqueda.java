import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ArbolBinarioBusqueda adaptado para usarse como diccionario de Palabra.
 *
 * ORIGEN:
 *  - Basado en: ArbolBinarioBusqueda.java (Repositorio del curso, autor Salvador Gonzalez Arellano)
 *
 * CAMBIOS / ADAPTACIONES IMPORTANTES:
 *  - Se mantienen todos los métodos originales y la estructura general (Nodo<T>, recursividad).
 *  - Se añadieron métodos específicos para trabajar con Palabra:
 *      - insertarPalabra(Palabra p): inserta o incrementa frecuencia si ya existe.
 *      - frecuencia(String lexema): devuelve la frecuencia de la palabra (0 si no existe).
 *      - getInOrdenList(): devuelve LinkedList<Palabra> con todos los elementos en orden alfabético.
 *      - rango(String a, String b): devuelve LinkedList<Palabra> con palaras entre a y b (incluyendo ambos extremos si existen).
 *      - palabrasMasFrecuentes(int k): devuelve List<Palabra> con las k palabras de mayor frecuencia.
 *  - Se añadieron utilidades privadas para recabar elementos en listas sin cambiar el comportamiento de los
 *    métodos de recorrido originales (recorrerInorden, etc).
 *
 * NOTA:
 *  - La clase sigue siendo genérica <T extends Comparable<T>> por compatibilidad con el repo original,
 *    pero los métodos añadidos asumen que el árbol se instancia como ArbolBinarioBusqueda<Palabra>.
 *
 * ADVERTENCIA:
 *  - Se usan casts internos a Palabra en los métodos nuevos; al usar la clase, instanciar el árbol con Palabra.
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> {
    private Nodo<T> raiz; // Raíz del árbol binario.

    /* ----------------------- Constructores y getters/setters (sin cambios) ----------------------- */

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public ArbolBinarioBusqueda(T dato) {
        raiz = new Nodo<T>(dato);
    }

    public ArbolBinarioBusqueda(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    /* ----------------------- Métodos originales del repo (sin cambios funcionales) ----------------------- */

    private Nodo<T> encontrarNodo(Nodo<T> actual, T buscado) {
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

    public Nodo<T> encontrarNodo(T buscado) {
        return encontrarNodo(raiz, buscado);
    }

    public Nodo<T> entontrarDescendienteMin(Nodo<T> actual) {
        if (actual == null) {
            return null;
        } else {
            while (actual.getIzquierdo() != null) {
                actual = actual.getIzquierdo();
            }
            return actual;
        }
    }

    public Nodo<T> entontrarDescendienteMax(Nodo<T> actual) {
        if (actual == null) {
            return null;
        } else {
            while (actual.getDerecho() != null) {
                actual = actual.getDerecho();
            }
            return actual;
        }
    }

    private Nodo<T> encontrarAntecesor(Nodo<T> actual, T buscado) {
        if (actual == null) {
            return null;
        }
        if (actual.getIzquierdo() != null && buscado.equals(actual.getIzquierdo().getDato())) {
            return actual;
        }
        if (actual.getDerecho() != null && buscado.equals(actual.getDerecho().getDato())) {
            return actual;
        }
        if (actual.getDato().compareTo(buscado) > 0) {
            return encontrarAntecesor(actual.getIzquierdo(), buscado);
        } else {
            return encontrarAntecesor(actual.getDerecho(), buscado);
        }
    }

    public Nodo<T> encontrarAntecesor(T buscado) {
        return encontrarAntecesor(raiz, buscado);
    }

    public int tam(Nodo<T> actual) {
        if (actual != null) {
            return tam(actual.getIzquierdo()) + tam(actual.getDerecho()) + 1;
        } else {
            return 0;
        }
    }

    public int tam(T dato) {
        Nodo<T> actual = encontrarNodo(dato);
        return tam(actual);
    }

    public int altura(Nodo<T> actual) {
        if (actual != null) {
            int alturaIzq = altura(actual.getIzquierdo());
            int alturaDer = altura(actual.getDerecho());
            if (alturaIzq > alturaDer) {
                return 1 + alturaIzq;
            } else {
                return 1 + alturaDer;
            }
        } else {
            return -1;
        }
    }

    public int altura(T dato) {
        Nodo<T> actual = encontrarNodo(dato);
        return altura(actual);
    }

    /* ----------------------- Inserción original (no considera frecuencia) ----------------------- */

    private Nodo<T> insertarDato(Nodo<T> actual, T dato) {
        if (actual == null) {
            return new Nodo<T>(dato);
        } else if (actual.getDato().compareTo(dato) > 0) {
            actual.setIzquierdo(insertarDato(actual.getIzquierdo(), dato));
        } else {
            actual.setDerecho(insertarDato(actual.getDerecho(), dato));
        }
        return actual;
    }

    public boolean insertarDato(T dato) {
        if (encontrarNodo(dato) == null) {
            raiz = insertarDato(raiz, dato);
            return true;
        } else {
            return false;
        }
    }

    /* ----------------------- Eliminación original (sin cambios) ----------------------- */

    public void liberarNodo(Nodo<T> aEliminar, Nodo<T> antecesor) {
        Nodo<T> sustituto = entontrarDescendienteMax(aEliminar.getIzquierdo());
        if (sustituto != null) {
            antecesor = encontrarAntecesor(sustituto.getDato());
            aEliminar.setDato(sustituto.getDato());
            liberarNodo(sustituto, antecesor);
        } else {
            sustituto = entontrarDescendienteMin(aEliminar.getDerecho());
            if (sustituto != null) {
                antecesor = encontrarAntecesor(sustituto.getDato());
                aEliminar.setDato(sustituto.getDato());
                liberarNodo(sustituto, antecesor);
            } else if (antecesor == null) {
                raiz = null;
            } else {
                if (antecesor.getIzquierdo() == aEliminar) {
                    antecesor.setIzquierdo(null);
                } else {
                    antecesor.setDerecho(null);
                }
            }
            aEliminar = null;
        }
    }

    public boolean eliminarDato(T dato) {
        Nodo<T> aEliminar = encontrarNodo(dato);
        if (aEliminar != null) {
            Nodo<T> antecesor = encontrarAntecesor(raiz, dato);
            liberarNodo(aEliminar, antecesor);
            return true;
        } else {
            return false;
        }
    }

    /* ----------------------- Recorridos originales (sin cambios funcionales) ----------------------- */

    private void recorrerInorden(Nodo<T> actual) {
        if (actual != null) {
            recorrerInorden(actual.getIzquierdo());
            System.out.print(actual.getDato() + " ");
            recorrerInorden(actual.getDerecho());
        }
    }

    public void recorrerInorden() {
        recorrerInorden(raiz);
        System.out.println();
    }

    private void recorrerPreorden(Nodo<T> actual) {
        if (actual != null) {
            System.out.print(actual.getDato() + " ");
            recorrerPreorden(actual.getIzquierdo());
            recorrerPreorden(actual.getDerecho());
        }
    }

    public void recorrerPreorden() {
        recorrerPreorden(raiz);
        System.out.println();
    }

    private void recorrerPostorden(Nodo<T> actual) {
        if (actual != null) {
            recorrerPostorden(actual.getIzquierdo());
            recorrerPostorden(actual.getDerecho());
            System.out.print(actual.getDato() + " ");
        }
    }

    public void recorrerPostorden() {
        recorrerPostorden(raiz);
        System.out.println();
    }

    public void recorrerNivel() {
        Queue<Nodo<T>> cola = new LinkedList<>();
        Nodo<T> aux;
        cola.add(raiz);
        while (!cola.isEmpty()) {
            int nodosEnNivel = cola.size();
            while (nodosEnNivel > 0) {
                aux = cola.peek();
                System.out.print(aux.getDato() + " ");
                if (aux.getIzquierdo() != null) {
                    cola.add(aux.getIzquierdo());
                }
                if (aux.getDerecho() != null) {
                    cola.add(aux.getDerecho());
                }
                cola.remove();
                nodosEnNivel--;
            }
            System.out.println();
        }
        System.out.println();
    }

    private void mostrarArbol(Nodo<T> actual, int espacio) {
        espacio += 10;
        if (actual == null) {
            System.out.println();
            for (int i = 0; i < espacio; i++) {
                System.out.print(" ");
            }
            System.out.println("null");
            return;
        }
        mostrarArbol(actual.getDerecho(), espacio);
        System.out.println();
        for (int i = 0; i < espacio; i++) {
            System.out.print(" ");
        }
        System.out.println(actual.getDato());
        mostrarArbol(actual.getIzquierdo(), espacio);
    }

    public void mostrarArbol() {
        System.out.println("----------------------------------------------------");
        mostrarArbol(raiz, -5);
        System.out.println("----------------------------------------------------");
    }

    /* ----------------------- MÉTODOS AÑADIDOS PARA TRABAJAR CON Palabra ----------------------- */

    /**
     * Inserta una Palabra en el árbol. Si ya existe (mismo lexema), incrementa su frecuencia.
     *
     * Observación: este método asume que el árbol fue instanciado como ArbolBinarioBusqueda<Palabra>.
     *
     * @param p palabra a insertar (no null)
     * @return true si la operación fue exitosa (inserción o incremento de frecuencia)
     */
    @SuppressWarnings("unchecked")
    public boolean insertarPalabra(Palabra p) {
        if (p == null) return false;
        // Si el árbol está vacío, simplemente asignamos la raíz
        if (raiz == null) {
            raiz = (Nodo<T>) new Nodo<Palabra>(p);
            return true;
        }
        // Buscamos si ya existe la palabra
        Nodo<T> nodoEncontrado = encontrarNodo((T) p);
        if (nodoEncontrado != null) {
            // Incrementar frecuencia en el dato existente
            Palabra existente = (Palabra) nodoEncontrado.getDato();
            existente.incrementarFrecuencia();
            return true;
        } else {
            // Insertar nuevo nodo (manteniendo la lógica original de inserción)
            raiz = insertarDato(raiz, (T) p);
            return true;
        }
    }

    /**
     * Devuelve la frecuencia de una palabra por su lexema.
     *
     * @param lexema cadena (se normaliza internamente: trim + toLowerCase)
     * @return frecuencia >= 1 si existe, 0 si no existe o entrada inválida
     */
    @SuppressWarnings("unchecked")
    public int frecuencia(String lexema) {
        if (lexema == null) return 0;
        lexema = lexema.trim().toLowerCase();
        if (lexema.isEmpty()) return 0;
        try {
            Palabra temp = new Palabra(lexema);
            Nodo<T> nodo = encontrarNodo((T) temp);
            if (nodo == null) return 0;
            Palabra encontrada = (Palabra) nodo.getDato();
            return encontrada.getFrecuencia();
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    /**
     * Devuelve una lista con todas las palabras en orden alfabético (inOrden),
     * sin imprimir nada. Útil para otras consultas.
     *
     * @return LinkedList<Palabra> (vacía si no hay elementos)
     */
    @SuppressWarnings("unchecked")
    public LinkedList<Palabra> getInOrdenList() {
        LinkedList<Palabra> lista = new LinkedList<>();
        getInOrdenRec(raiz, lista);
        return lista;
    }

    @SuppressWarnings("unchecked")
    private void getInOrdenRec(Nodo<T> actual, LinkedList<Palabra> lista) {
        if (actual != null) {
            getInOrdenRec(actual.getIzquierdo(), lista);
            lista.add((Palabra) actual.getDato());
            getInOrdenRec(actual.getDerecho(), lista);
        }
    }

    /**
     * Devuelve las palabras cuyo lexema está en el rango [a, b] (alfabético, inclusive).
     * Responde en orden alfabético.
     *
     * @param a lexema inicial (puede ser mayor que b; en ese caso se intercambian)
     * @param b lexema final
     * @return LinkedList<Palabra> en orden (vacía si no hay)
     */
    @SuppressWarnings("unchecked")
    public LinkedList<Palabra> rango(String a, String b) {
        LinkedList<Palabra> resultado = new LinkedList<>();
        if (a == null || b == null) return resultado;
        a = a.trim().toLowerCase();
        b = b.trim().toLowerCase();
        if (a.isEmpty() || b.isEmpty()) return resultado;
        // Asegurar orden a <= b
        if (a.compareTo(b) > 0) {
            String tmp = a;
            a = b;
            b = tmp;
        }
        Palabra pa = new Palabra(a);
        Palabra pb = new Palabra(b);
        rangoRec(raiz, pa, pb, resultado);
        return resultado;
    }

    @SuppressWarnings("unchecked")
    private void rangoRec(Nodo<T> actual, Palabra a, Palabra b, LinkedList<Palabra> lista) {
        if (actual == null) return;
        Palabra dato = (Palabra) actual.getDato();
        // Si dato > a, explorar izquierda (pues pueden existir elementos >= a)
        if (dato.compareTo(a) > 0) {
            rangoRec(actual.getIzquierdo(), a, b, lista);
        }
        // Si a <= dato <= b, lo agregamos
        if (dato.compareTo(a) >= 0 && dato.compareTo(b) <= 0) {
            lista.add(dato);
        }
        // Si dato < b, explorar derecha
        if (dato.compareTo(b) < 0) {
            rangoRec(actual.getDerecho(), a, b, lista);
        }
    }

    /**
     * Devuelve las k palabras más frecuentes (ordenadas de mayor a menor frecuencia).
     * Si k <= 0 devuelve lista vacía. Si k > número de palabras devuelve todas ordenadas.
     *
     * NOTA: para obtener las k más frecuentes hacemos un recorrido inorden para obtener
     *       la lista de palabras y luego ordenamos por frecuencia. Esto no reemplaza al BST;
     *       es solo una consulta que usa una lista auxiliar.
     *
     * @param k número de palabras a devolver
     * @return List<Palabra> de tamaño <= k
     */
    public List<Palabra> palabrasMasFrecuentes(int k) {
        List<Palabra> vacio = new ArrayList<>();
        if (k <= 0) return vacio;
        LinkedList<Palabra> todas = getInOrdenList();
        // Ordenar por frecuencia descendente (si empate, ordenar alfabéticamente)
        Collections.sort(todas, new Comparator<Palabra>() {
            @Override
            public int compare(Palabra p1, Palabra p2) {
                int f = Integer.compare(p2.getFrecuencia(), p1.getFrecuencia());
                if (f != 0) return f;
                return p1.getLexema().compareTo(p2.getLexema());
            }
        });
        List<Palabra> resultado = new ArrayList<>();
        int limite = Math.min(k, todas.size());
        for (int i = 0; i < limite; i++) {
            resultado.add(todas.get(i));
        }
        return resultado;
    }
}
