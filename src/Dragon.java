/**
 * Subclase concreta {@code Dragon}.
 *
 * <p><b>Identidad de combate:</b> criatura de fuego. Su ataque es el mas
 * fuerte del juego, pero su defensa es practicamente nula: absorbe el dano
 * completo con su piel escamosa y depende de su gran fuerza para ganar antes
 * de que le alcance la vida.</p>
 *
 * <ul>
 *   <li><b>Ataque unico:</b> aliento de fuego, dano = fuerza x 2.</li>
 *   <li><b>Defensa unica:</b> piel escamosa, no reduce el dano recibido.</li>
 * </ul>
 *
 * @author Programacion II - G411
 * @version 1.0
 */
public class Dragon extends Criatura {

    /** Multiplicador del aliento de fuego sobre la fuerza base. */
    private static final int MULTIPLICADOR_FUEGO = 2;

    /**
     * @param nombre nombre del dragon
     * @param vida   puntos de salud iniciales
     * @param fuerza fuerza base de ataque
     */
    public Dragon(String nombre, int vida, int fuerza) {
        super(nombre, vida, fuerza);
    }

    /**
     * Ataque unico: aliento de fuego. Inflige el doble de la fuerza base.
     */
    @Override
    public void atacar(Criatura objetivo) {
        int dano = fuerza * MULTIPLICADOR_FUEGO;
        System.out.printf("%s escupe fuego e inflige %d de dano a %s.%n",
                nombre, dano, objetivo.getNombre());
        objetivo.defender(dano);
    }

    /**
     * Defensa unica: piel escamosa. No reduce el dano, lo recibe completo.
     */
    @Override
    public void defender(int dado) {
        System.out.printf("La piel escamosa de %s absorbe el golpe sin reduccion.%n",
                nombre);
        recibirDanio(dado);
        System.out.printf("   Salud de %s: %d.%n", nombre, vida);
    }
}
