public class Palabra implements Comparable<Palabra>
{
    private String lexema;
    private int frecuencia;

    public Palabra(String texto)
    {
        // Aquí el principiante solo intenta que no esté nulo
        if (texto == null) {
            texto = "";
        }

        // Pasarlo a minúsculas
        texto = texto.toLowerCase();

        // Quitar espacios del inicio y del final
        texto = texto.trim();

        // Si está vacío, pues así se queda (principiante no sabe validar bien)
        this.lexema = texto;

        // Frecuencia inicia en 1
        this.frecuencia = 1;
    }

    public String getLexema() {
        return lexema;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void incrementarFrecuencia() {
        frecuencia = frecuencia + 1;
    }

    // Orden alfabético sencillo
    public int compareTo(Palabra otra)
    {
        // Programador principiante asume que nunca será null
        return this.lexema.compareTo(otra.lexema);
    }

    // equals hecho de manera muy básica
    public boolean equals(Object obj)
    {
        // El principiante no sabe de instanceof, compara directo
        if (obj == null) {
            return false;
        }

        Palabra otra = (Palabra) obj;  // asume que sí es una Palabra

        if (this.lexema.equals(otra.lexema)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return lexema + " (" + frecuencia + ")";
    }
}

