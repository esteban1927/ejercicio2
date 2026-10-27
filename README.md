# Batalla de Criaturas

> **Parcial II — Programación II (G411)**
> Puntos 1 y 2: Jerarquía de clases, clases abstractas y polimorfismo.

Simulador de batallas entre criaturas. El objetivo del ejercicio es modelar una
**superclase abstracta** `Criatura` y varias **subclases concretas** que
implementan su propia forma de atacar y de defenderse, todo resuelto a través
del **polimorfismo**.

---

## 1. Estructura del proyecto

```
batalla-criaturas/
├── README.md
└── src/
    ├── Criatura.java      ← Clase ABSTRACTA (Punto 1)
    ├── Dragon.java        ← Subclase concreta (Punto 2)
    ├── Goblin.java        ← Subclase concreta (Punto 2)
    ├── Guerrero.java      ← Subclase concreta (Punto 2)
    └── BatallaMain.java   ← Clase principal / demostración
```

---

## 2. Diagrama de la jerarquía

```
                    ┌──────────────────────────┐
                    │   «abstract» Criatura    │
                    ├──────────────────────────┤
                    │ # nombre  : String       │
                    │ # vida    : int          │
                    │ # fuerza  : int          │
                    ├──────────────────────────┤
                    │ + Criatura(nombre,vida,  │
                    │            fuerza)       │
                    │ + «abstract» atacar(     │
                    │        Criatura objetivo)│
                    │ + «abstract» defender(   │
                    │        int dado)         │
                    │ + estaViva() : boolean   │
                    │ # recibirDanio(int)      │
                    │ + getSalud() : int       │
                    │ + getNombre() : String   │
                    │ + getFuerza() : int      │
                    │ + toString()  : String   │
                    └────────────┬─────────────┘
                                 │  extends
          ┌──────────────────────┼──────────────────────┐
          │                      │                      │
┌─────────▼────────┐  ┌──────────▼─────────┐  ┌─────────▼────────┐
│     Dragon       │  │      Goblin        │  │    Guerrero      │
├──────────────────┤  ├────────────────────┤  ├──────────────────┤
│ MULTIPLICADOR_   │  │ BONUS_DAGA = 2     │  │ BONUS_ESPADA = 1 │
│  FUEGO = 2       │  │ azar : Random      │  │                  │
├──────────────────┤  ├────────────────────┤  ├──────────────────┤
│ atacar()         │  │ atacar()           │  │ atacar()         │
│ defender()       │  │ defender()         │  │ defender()       │
└──────────────────┘  └────────────────────┘  └──────────────────┘
```

---

## 3. Punto 1 — Clase abstracta `Criatura`

### Atributos requeridos

| Atributo  | Tipo     | Descripción                                    |
|-----------|----------|------------------------------------------------|
| `nombre`  | `String` | Representa el nombre de la criatura.           |
| `vida`    | `int`    | Representa la salud de la criatura.            |
| `fuerza`  | `int`    | Representa la fuerza de la criatura.           |

> **Nota sobre `vida` / `salud`:** el enunciado usa ambos términos para el
> mismo concepto. Se mantiene el atributo `vida` (como lo pide la lista de
> atributos) y se expone el método `getSalud()`, de modo que ambos nombres
> quedan disponibles y `estaViva()` opera sobre la salud.

### Métodos

```java
// Métodos ABSTRACTOS: sin cuerpo, cada subclase los implementa a su manera
public abstract void atacar(Criatura objetivo);
public abstract void defender(int dado);

// Método CONCRETO: indicador de vida
public boolean estaViva() {
    return this.vida > 0;
}
```

La clase está marcada con `abstract`, por lo que **no se puede instanciar
directamente** — solo sus subclases concretas.

---

## 4. Punto 2 — Subclases concretas

Se implementaron **tres** subclases (mínimo requerido), cada una con una forma
única de atacar y de defenderse:

| Subclase  | Ataque único                        | Defensa única                              |
|-----------|-------------------------------------|--------------------------------------------|
| `Dragon`  | Aliento de fuego: `fuerza × 2`      | Piel escamosa: recibe el daño completo     |
| `Goblin`  | Estocada rápida: `fuerza + 2`       | Esquiva ágil: 50% de probabilidad de evadir|
| `Guerrero`| Golpe de espada: `fuerza + 1`       | Escudo: el daño se reduce a la mitad       |

```java
public class Dragon extends Criatura {
    @Override public void atacar(Criatura objetivo) { ... }  // fuerza × 2
    @Override public void defender(int dado)        { ... }  // sin reducción
}
```

> `defender(int dado)` recibe el **daño entrante** que llega contra la criatura.
> Cada subclase decide si lo absorbe, lo reduce o lo evade.

---

## 5. Polimorfismo

El polimorfismo se demuestra en tres puntos clave de `BatallaMain.java`:

**1. Referencia de la superclase guardando subclases distintas**

```java
List<Criatura> ejercito = new ArrayList<>();
ejercito.add(new Dragon("Ignis", 120, 15));
ejercito.add(new Goblin("Nim", 70, 8));
ejercito.add(new Guerrero("Aldric", 110, 10));
```

**2. Método que solo conoce la superclase**

```java
private static void ejecutarAtaque(Criatura atacante, Criatura defensor) {
    atacante.atacar(defensor);   // el JVM elige la implementación en runtime
}
```

**3. Resolución dinámica en el bucle de combate**

```java
while (a.estaViva() && b.estaViva()) {
    ejecutarAtaque(a, b);   // a puede ser Dragon, Goblin o Guerrero
    ejecutarAtaque(b, a);   // b igual: el código no cambia
}
```

Ninguna de estas líneas contiene un `if (x instanceof Dragon)`. El mismo
código sirve para las tres criaturas porque la decisión se toma **en tiempo de
ejecución**.

---

## 6. Cómo compilar y ejecutar

Desde la carpeta `batalla-criaturas/`:

```bash
# Compilar (carpeta out/ se crea sola)
javac -encoding UTF-8 -d out src/*.java

# Ejecutar
java -cp out BatallaMain
```

Requisito: JDK 8 o superior (probado con OpenJDK 25).

---

## 7. Correspondencia con los criterios de evaluación

| Criterio                                        | Puntos | Dónde se cumple |
|-------------------------------------------------|:------:|-----------------|
| **Manejo de repositorio** (git flow, commits de característica, merge request) | 0.5 | Sección 8 de este README |
| **Jerarquía de Clases** (herencia correcta de clases abstractas) | 1.0 | `Dragon/Goblin/Guerrero extends Criatura` |
| **Uso de Polimorfismo**                         | 0.5 | Sección 5 — `List<Criatura>` y `ejecutarAtaque()` |
| **Implementación de Clases Abstractas** (métodos abstractos correctamente implementados en las subclases) | 0.5 | `@Override` de `atacar()` y `defender()` en las 3 subclases |
| **Organización y Legibilidad** (código ordenado, comentarios que expliquen el propósito) | 0.5 | Javadoc en las 5 clases, estructura `src/`, `.gitignore` |
| **TOTAL**                                       | **3.0** | |

---

## 8. Manejo del repositorio (git flow)

Para cumplir el criterio de repositorio, el proyecto debe versionarse siguiendo
**git flow** con commits de característica y un merge request:

```bash
cd batalla-criaturas
git init
git checkout -b develop

# Crear la rama de característica para este ejercicio
git checkout -b feature/parcial2-punto1-y-2

git add src/ README.md .gitignore
git commit -m "feat: agregar clase abstracta Criatura con jerarquia de criaturas"
git commit -m "feat: implementar subclases Dragon, Goblin y Guerrero"
git commit -m "feat: agregar BatallaMain con demostracion de polimorfismo"

git push -u origin feature/parcial2-punto1-y-2
```

Luego se abre el **merge request** (GitLab) o **pull request** (GitHub) desde
`feature/parcial2-punto1-y-2` hacia `develop`, y se pide la revisión del
compañero de pareja.

Una vez aprobado:

```bash
git checkout develop
git merge --no-ff feature/parcial2-punto1-y-2
git push origin develop
```

---

## 9. Autores

- Programación II — Grupo G411
- Parcial II, ejercicio en parejas
 Esteban Muñoz Ordpñez
 Samuel Andres Gomez Taborda
