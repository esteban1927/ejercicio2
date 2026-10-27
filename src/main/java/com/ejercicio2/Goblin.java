package com.ejercicio2;
import java.util.Random;

/**
 * Subclase concreta {@code Goblin}.
 *
 * <p><b>Identidad de combate:</b> criatura pequena y veloz. Su ataque es rapido
 * y suma un bonus fijo sobre la fuerza base, y su defensa se basa en la
 * agilidad: tiene un 50% de probabilidad de esquivar el ataque por completo.</p>
 *
 * <ul>
 *   <li><b>Ataque unico:</b> estocada rapida, dano = fuerza + 2.</li>
 *   <li><b>Defensa unica:</b> esquiva agil, 50% de probabilidad de evadir.</li>
 * </ul>
 *
 * @author Programacion II - G411
 * @version 1.0
 */
public class Goblin extends Criatura {

    /** Bonus fijo que suma la estocada rapida sobre la fuerza base. */
    private static final int BONUS_DAGA = 2;

    /** Generador de numeros aleatorios para la esquiva. */
    private final Random azar = new Random();

    /**
     * @param nombre nombre del goblin
     * @param vida   puntos de salud iniciales
     * @param fuerza fuerza base de ataque
     */
    public Goblin(String nombre, int vida, int fuerza) {
        super(nombre, vida, fuerza);
    }

    /**
     * Ataque unico: estocada rapida con daga. Suma un bonus a la fuerza base.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dano = fuerza + BONUS_DAGA;
        System.out.printf("%s da una estocada rapida e inflige %d de dano a %s.%n",
                nombre, dano, objetivo.getNombre());
        objetivo.defender(dano);
    }

    /**
     * Defensa unica: esquiva agil. Tiene un 50% de probabilidad de no recibir
     * ningun dano.
     */
    @Override
    public void defender(int dado) {
        if (azar.nextBoolean()) {
            System.out.printf("%s esquiva el ataque con su agilidad.%n", nombre);
            System.out.printf("   Salud de %s: %d (sin cambios).%n", nombre, vida);
        } else {
            System.out.printf("%s no logra esquivar el ataque.%n", nombre);
            recibirDanio(dado);
            System.out.printf("   Salud de %s: %d.%n", nombre, vida);
        }
    }
}
