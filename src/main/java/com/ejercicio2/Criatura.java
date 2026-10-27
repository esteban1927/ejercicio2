package com.ejercicio2;
/**
 * Clase abstracta que representa una criatura dentro del juego de batalla.
 *
 * <p>Es la superclase de toda la jerarquia. Define los tres atributos comunes
 * (nombre, vida y fuerza) y obliga a cada subclase a implementar su propia
 * forma de atacar y de defenderse, lo que garantiza el polimorfismo del
 * sistema.</p>
 *
 * <p><b>Nota:</b> "vida" y "salud" se refieren al mismo concepto: los puntos
 * que le quedan a la criatura. Se mantiene el atributo {@code vida} y se
 * expone {@link #getSalud()} para que el nombre del concepto quede claro.</p>
 *
 * @author Programacion II - G411
 * @version 1.0
 */
public abstract class Criatura {

    /** Nombre identificador de la criatura. */
    protected String nombre;

    /** Puntos de vida (salud) actuales de la criatura. */
    protected int vida;

    /** Fuerza base que define el poder de ataque de la criatura. */
    protected int fuerza;

    /**
     * Constructor unico de la clase Criatura.
     *
     * @param nombre nombre de la criatura
     * @param vida   puntos de salud iniciales
     * @param fuerza fuerza base de ataque
     */
    public Criatura(String nombre, int vida, int fuerza) {
        this.nombre = nombre;
        this.vida = vida;
        this.fuerza = fuerza;
    }

    // ==================================================================
    //  METODOS ABSTRACTOS
    //  Cada subclase debe definir su forma unica de atacar y defenderse.
    // ==================================================================

    /**
     * Ataca a otra criatura. Cada subclase calcula su propio dano.
     *
     * @param objetivo criatura que recibira el ataque
     */
    public abstract void atacar(Criatura objetivo);

    /**
     * Recibe un ataque.
     *
     * <p>El parametro {@code dado} representa el dano entrante que llega contra
     * esta criatura. Segun la habilidad defensiva propia de cada subclase, ese
     * dano puede reducirse, absorberse o evadirse.</p>
     *
     * @param dado dano entrante que llega a la criatura
     */
    public abstract void defender(int dado);

    // ==================================================================
    //  METODOS CONCRETOS
    // ==================================================================

    /**
     * Indica si la criatura sigue viva.
     *
     * @return {@code true} si la salud (vida) es mayor que cero
     */
    public boolean estaViva() {
        return this.vida > 0;
    }

    /**
     * Aplica dano a la criatura sin dejar que la salud baje de cero.
     * Metodo auxiliar para que las subclases no repitan logica comun.
     *
     * @param cantidad puntos de salud a descontar
     */
    protected void recibirDanio(int cantidad) {
        this.vida = Math.max(0, this.vida - cantidad);
    }

    /**
     * @return puntos de salud actuales de la criatura
     */
    public int getSalud() {
        return vida;
    }

    /**
     * @return nombre de la criatura
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return fuerza base de la criatura
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * @return representacion en texto del estado actual de la criatura
     */
    @Override
    public String toString() {
        return String.format("  %-9s | salud: %3d | fuerza: %2d | %s",
                nombre, vida, fuerza, estaViva() ? "viva" : "derrotada");
    }
}
