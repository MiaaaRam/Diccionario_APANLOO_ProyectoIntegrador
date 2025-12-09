/**
 * Clase Palabra
 * Modelo de datos para el diccionario.
 *
 * Requisitos: lexema (minúsculas, no vacío), frecuencia (>=1),
 * compareTo por lexema, equals consistente con lexema.
 */
public class Palabra implements Comparable<Palabra> {
    private String lexema;
    private int frecuencia;

    /**
     * Constructor: normaliza (trim + toLowerCase) y valida lexema.
     * Inicializa frecuencia en 1.
     *
     * @param lexema la palabra (no null, no vacía)
     */
    public Palabra(String lexema) {
        if (lexema == null) {
            throw new IllegalArgumentException("El lexema no puede ser null.");
        }
        lexema = lexema.trim().toLowerCase();
        if (lexema.isEmpty()) {
            throw new IllegalArgumentException("El lexema no puede estar vacío.");
        }
        this.lexema = lexema;
        this.frecuencia = 1;
    }

    public String getLexema() {
        return lexema;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void incrementarFrecuencia() {
        frecuencia++;
    }

    @Override
    public int compareTo(Palabra otra) {
        return this.lexema.compareTo(otra.lexema);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Palabra)) return false;
        Palabra otra = (Palabra) obj;
        return this.lexema.equals(otra.lexema);
    }

    @Override
    public int hashCode() {
        return lexema.hashCode();
    }

    @Override
    public String toString() {
        return lexema + " (" + frecuencia + ")";
    }
}
