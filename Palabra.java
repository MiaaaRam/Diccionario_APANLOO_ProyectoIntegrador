/**
 * Clase Palabra
 * Modelo de datos para el diccionario.
 * CORRECCIÓN: Ahora la limpieza de caracteres se hace DENTRO del constructor.
 */
public class Palabra implements Comparable<Palabra> {
    private String lexema;
    private int frecuencia;

    public Palabra(String lexema) {
        if (lexema == null) 
            throw new IllegalArgumentException("Lexema nulo");
        
        // 1. Normalizar: minúsculas y quitar espacios extremos
        String limpio = lexema.trim().toLowerCase();
        
        // 2. CORRECCIÓN: Quitar todo lo que no sea letra (igual que en el Lector)
        // Esto arregla el problema de insertar palabras manualmente con símbolos
        limpio = limpio.replaceAll("[^a-záéíóúüñ]", " ");
        this.lexema = limpio;
        this.frecuencia = 1;
    }

    public String getLexema() {
        return lexema;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void incrementarFrecuencia() {
        this.frecuencia= frecuencia +1;
    }

    
    public int compareTo(Palabra otra) {
        return this.lexema.compareTo(otra.lexema);
    }

   
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null) 
            return false;
        try {
            Palabra otra = (Palabra) obj;
            return this.lexema.equals(otra.lexema);
        } catch (ClassCastException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return lexema + " (" + frecuencia + ")";
    }
}