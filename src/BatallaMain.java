import java.util.ArrayList;
import java.util.List;


public class BatallaMain {

    /** Limite de rondas para evitar combates infinitos. */
    private static final int MAX_RONDAS = 30;

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("   BATALLA DE CRIATURAS - Programacion II");
        System.out.println("=================================================");

        // --- 1. POBLACION: se usa la referencia de la superclase -------------
        // La lista es de tipo Criatura, pero guarda objetos de tres subclases
        // distintas. Este es el primer paso del polimorfismo.
        List<Criatura> ejercito = crearEjercito();

        System.out.println("\n[1] EJERCITO CREADO (lista de tipo Criatura)");
        for (Criatura criatura : ejercito) {
            // se resuelve dinamicamente en cada subclase.
            System.out.println(criatura);
        }

        // --- 2. HABILIDADES UNICAS -------------------------------------------
        System.out.println("\n[2] HABILIDADES UNICAS DE CADA CRIATURA");
        demostrarHabilidades();

        // --- 3. TORNEO ROUND-ROBIN -------------------------------------------
        System.out.println("\n[3] TORNEO (todos contra todos)");
        ejecutarTorneo();

        System.out.println("\n=================================================");
        System.out.println("   FIN DEL SIMULADOR");
        System.out.println("=================================================");
    }

    /**
     * Crea una nueva tropa con una criatura de cada subclase.
      */
    private static List<Criatura> crearEjercito() {
        List<Criatura> ejercito = new ArrayList<>();
        ejercito.add(new Dragon("Ignis", 120, 15));
        ejercito.add(new Goblin("Nim", 70, 8));
        ejercito.add(new Guerrero("Aldric", 110, 10));
        return ejercito;
    }

    /**
     * Punto de entrada polimorfico.
     *
     * <p>Este metodo solo conoce la superclase No sabe si
     * recibira un Dragon, un Goblin o un Guerrero: la maquina virtual decide
     * en tiempo de ejecucion que implementacion de y
     *  debe ejecutar. Ese es exactamente el polimorfismo.</p>
     *
     * @param atacante criatura que inicia el ataque
     * @param defensor criatura que recibe el ataque
     */
    private static void ejecutarAtaque(Criatura atacante, Criatura defensor) {
        atacante.atacar(defensor);
    }

    /**
     * Muestra que cada subclase ataca y defiende a su manera.
     */
    private static void demostrarHabilidades() {
        System.out.println("\n  -- Dragon (fuerza x2, sin reduccion de dano) --");
        Criatura dragon = new Dragon("Ignis", 120, 15);
        Criatura objetivo = new Guerrero("Practicante", 60, 4);
        ejecutarAtaque(dragon, objetivo);
        objetivo.atacar(dragon);

        System.out.println("\n  -- Goblin (fuerza +2, esquiva del 50%) --");
        Criatura goblin = new Goblin("Nim", 70, 8);
        objetivo = new Guerrero("Practicante", 60, 4);
        ejecutarAtaque(goblin, objetivo);
        objetivo.atacar(goblin);

        System.out.println("\n  -- Guerrero (fuerza +1, dano reducido a la mitad) --");
        Criatura guerrero = new Guerrero("Aldric", 110, 10);
        objetivo = new Guerrero("Practicante", 60, 4);
        ejecutarAtaque(guerrero, objetivo);
        objetivo.atacar(guerrero);
    }

    /**
     * Torneo round-robin: todos contra todos. Cada combate usa criaturas
     * recien creadas para que los encuentros sean independientes.
     */
    private static void ejecutarTorneo() {
        List<Criatura> plantilla = crearEjercito();
        int total = plantilla.size();

        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {

                // Criaturas nuevas para cada combate.
                List<Criatura> duelistas = crearEjercito();
                Criatura primero = duelistas.get(i);
                Criatura segundo = duelistas.get(j);

                System.out.printf("%n  >>> Combate: %s vs %s%n",
                        primero.getNombre(), segundo.getNombre());

                Criatura ganador = simularCombate(primero, segundo);

                if (ganador == null) {
                    System.out.println("      Resultado: sin ganador (se alcanzo el limite de rondas).");
                } else {
                    System.out.printf("      Resultado: gana %s (%s).%n",
                            ganador.getNombre(), ganador.getClass().getSimpleName());
                }
            }
        }
    }

    /**
     * Simula un combate por turnos hasta que una criatura quede sin salud.
     *
     * <p>Tambien aqui el polimorfismo es el protagonista: el ciclo solo usa la
     * referencia y aun asi cada turno se ejecuta con la
     * logica propia de la subclase correspondiente.</p>
     *
     *  a primera criatura del combate
     *  b segunda criatura del combate
     *  la criatura ganadora, o si se alcanzo el limite de rondas
     */
    private static Criatura simularCombate(Criatura a, Criatura b) {
        int ronda = 1; 

        while (a.estaViva() && b.estaViva() && ronda <= MAX_RONDAS) {
            System.out.printf("      Ronda %d%n", ronda++);

            ejecutarAtaque(a, b);
            if (!b.estaViva()) {
                break;
            }

            ejecutarAtaque(b, a);
            if (!a.estaViva()) {
                break;
            }
        }

        if (a.estaViva() && !b.estaViva()) {
            return a;
        }
        if (b.estaViva() && !a.estaViva()) {
            return b;
        }
        return null;
    }
}
