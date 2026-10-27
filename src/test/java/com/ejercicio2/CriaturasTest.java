package com.ejercicio2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CriaturasTest {

    @Test
    public void testAtaqueLetalCambiaEstadoAVivaFalse() {
        Dragon dragon = new Dragon("Destructor", 100, 200); 
        Guerrero guerrero = new Guerrero("Soldado", 50, 10);

        assertTrue(guerrero.estaViva(), "El guerrero inicia vivo.");
        dragon.atacar(guerrero); // Ataque masivo
        assertFalse(guerrero.estaViva(), "El guerrero debe morir tras recibir daño letal.");
    }

    @Test
    public void testSupervivenciaTrasAtaqueLeve() {
        Goblin goblin = new Goblin("Debilucho", 50, 2);
        Guerrero tanque = new Guerrero("Tanque", 200, 10);

        goblin.atacar(tanque);
        assertTrue(tanque.estaViva(), "El guerrero debe sobrevivir a un ataque débil.");
    }
}