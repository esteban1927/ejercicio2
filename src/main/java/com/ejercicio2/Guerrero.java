package com.ejercicio2;
/**
 * Subclase concreta {@code Guerrero}.
 *
 * <p><b>Identidad de combate:</b> combatiente equilibrado y resistente. Su
 * ataque es cercano a la fuerza base con un ligero bonus de espada, y su
 * defensa es la mas consistente del juego: el escudo reduce el dano recibido a
 * la mitad.</p>
 *
 * <ul>
 *   <li><b>Ataque unico:</b> golpe de espada, dano = fuerza + 1.</li>
 *   <li><b>Defensa unica:</b> bloqueo con escudo, dano reducido a la mitad.</li>
 * </ul>
 *
 * @author Programacion II - G411
 * @version 1.0
 */
public class Guerrero extends Criatura {

    /** Bonus fijo que suma el golpe de espada sobre la fuerza base. */
    private static final int BONUS_ESPADA = 1;

    /**
     * @param nombre nombre del guerrero
     * @param vida   puntos de salud iniciales
     * @param fuerza fuerza base de ataque
     */
    public Guerrero(String nombre, int vida, int fuerza) {
        super(nombre, vida, fuerza);
    }

    /**
     * Ataque unico: golpe de espada. Suma un bonus leve a la fuerza base.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dano = fuerza + BONUS_ESPADA;
        System.out.printf("%s golpea con su espada e inflige %d de dano a %s.%n",
                nombre, dano, objetivo.getNombre());
        objetivo.defender(dano);
    }

    /**
     * Defensa unica: bloqueo con escudo. Reduce el dano entrante a la mitad
     * (redondeado hacia arriba para no favorecer al defensor).
     */
    @Override
    public void defender(int dado) {
        int danoRecibido = (int) Math.ceil(dado / 2.0);
        System.out.printf("%s bloquea con su escudo: %d de dano se reduce a %d.%n",
                nombre, dado, danoRecibido);
        recibirDanio(danoRecibido);
        System.out.printf("   Salud de %s: %d.%n", nombre, vida);
    }
}
