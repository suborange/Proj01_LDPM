/**
 * VERSIONS
 * -0.01.a: make all classes, fill out Monster class with all required vars, methods and more.
 * -0.01.b: continue with filling out the foundation to Monster.java; finished getPhrase(), attackModifer(), calculateDefensePoints(), calculateAttackPoints(), and takeDamage();
 *  need to confirm toString() and setPhrase(); cleaned up references and comments
 * -0.01.c: continue with the rest and finish Monster.java; finished setPhrase() and attack();
 * -0.02.a: start implementing the other classes and their functionalities; fully fixed setPhrase using instanceof; Ran through initial test and passed most;
 * -0.03.a: fixed toString() ( it returns all the stuff, duh!!); fixed adding negative attack, causing health overflow; fixed setting the default attackPoints with dice.roll(); all tests passed :)
 * -0.03.b: need to double check Monster(); fixed all method signatures; all tests still pass with flying colors :)
 * -1.03.F: check all other TODO's left in monster.java; fixed small typo and line in toString(); cleaned up comments, and el fin;
 *
 */
import utilities.Dice;

public class ElectricRat extends Monster {
    private final int DEFENSE_MIN=5;
    private final int DEFENSE_MAX=8;
    private final int ATTACK_MIN=5;
    private final int ATTACK_MAX=8;


    public ElectricRat (String s) {
        super(s, ElementalType.ELECTRIC);
        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
    }

    public void setAttackPoints() {
        attackPoints = Dice.roll(ATTACK_MIN, ATTACK_MAX);
    }

    public void setDefensePoints() {
        defensePoints = Dice.roll(DEFENSE_MIN, DEFENSE_MAX);
    }
}
