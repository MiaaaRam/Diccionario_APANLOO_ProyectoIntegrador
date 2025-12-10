/**
 * Clase nodo para un arbol binario adaptado para Palabra.
 * * @param dato      El objeto Palabra que se guardará en este nodo
 * @param izquierdo El subarbol/hijo izquierdo del nodo (null si no tiene)
 * @param derecho   El subarbol/hijo derecho del nodo (null si no tiene).
 * @author Salvador Gonzalez Arellano (Adaptado)
 * @version 1.0
 */
public class Nodo {
    private Palabra dato; // Dato del nodo.
    private Nodo izquierdo, derecho; // Referencia a los subarboles.

    /**
     * Constructor que inicializa el nodo con el dato y los subarboles a null
     * * @param dato que almacenara el nodo
     */
    public Nodo(Palabra dato) {
        this.dato = dato;
        izquierdo = null;
        derecho = null;
    }

    /**
     * Devuelve el dato del nodo
     * * @return el dato del nodo
     */
    public Palabra getDato() {
        return dato;
    }

    /**
     * Cambia el dato del nodo
     * * @param dato
     */
    public void setDato(Palabra dato) {
        this.dato = dato;
    }

    /**
     * Devuelve la referencia al subarbol izquierdo
     * * @return Referencia al subarbol izquierdo
     */
    public Nodo getIzquierdo() {
        return izquierdo;
    }

    /**
     * Establece el subarbol izquierdo
     * * @param izquierdo
     */
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * Devuelve la referencia al subarbol derecho
     * * @return Referencia al subarbol derecho
     */
    public Nodo getDerecho() {
        return derecho;
    }
    /**
     * Establece el subarbol derecho
     * * @param derecho
     */
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}