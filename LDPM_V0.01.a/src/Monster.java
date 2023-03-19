/**
 * @author Ethan Bonavida
 * @since March 18 2023
 * @version 0.01.a: start and setup of all files, vars, and methods
 * DESCRIPTION: LDPM
 */

/**
 * VERSIONS
 * -0.01.a: make all classes, fill out Monster class with all required vars, methods and more.
 * TODO-0.01.b: continue with filling out the foundation to Monster.java, start at bookmarked TODO
 */

import java.util.ArrayList;
import java.util.List;

public abstract class Monster {

    // ** FROM DR.C SETUP FILE **
    protected enum ElementalType {
        ELECTRIC,
        FIRE,
        GRASS,
        WATER,
    }

    // ** all my code **
    private static double MAX_HP=20.0; // will probably have some value
    private List<ElementalType> elements= new ArrayList<>();
    private Double healthPoints= MAX_HP;
    private String phrase="";
    private String name="";
    private boolean fainted=false;
    private int attackMin=1;
    private int attackMax=10;
    private int defenseMin=1;
    private int defenseMax=10;

    protected int defensePoints=10;
    protected int attackPoints=10;

    // ** CONSTRUCTOR **
    Monster(String n, ElementalType T ) { // TODO check this definition
        this.name=n;
        this.elements.add(T);
        setPhrase(this);
    }

    // ** METHODS **

    // ** private **
    // TODO why is this like this and not just an overrideable method? whats the point this seems complex?
    public Monster setPhrase(Monster M) {
        if ( M.equals(WeirdTurtle.class)) {
            M.setPhrase("\'Urtle!");
        }
        else if ( M.equals(FireLizard.class) ) {
            M.setPhrase("Deal with it");
        }
        else if ( M.equals(ElectricRat.class) ) {
            M.setPhrase("\'Lectric!");
        }
        else if ( M.equals(FlowerDino.class) ) {
            M.setPhrase("Flowah!");
        }
        else {
            M.setPhrase("\"No phrase for me!\"");
        }


        return M;
    }
    // ** protected **
    protected double calculateDefensePoints( Monster M ) {
        // *** TESTING PURPOSES ***
        return 0;
    }

    protected double attackModifier(ElementalType T) {
        // *** TESTING PURPOSES ***
        return 0;
    }
    // ** non-setter/getters **
    boolean isFainted() {
        // *** TESTING PURPOSES ***
        return false;
    }

    double attack(Monster M) {

        // *** TESTING PURPOSES ***
        return 0;
    }
    double takeDamage (double d) {

        // *** TESTING PURPOSES ***
        return 0;
    }

    double calculateAttackPoints(Monster M, List<ElementalType> T) {

        // *** TESTING PURPOSES ***
        return 0;

    }


    // ** setters/getters **

    public List<ElementalType> getElements() {
        return elements;
    }

    /**
     * @param T the type to be passed to set, based on if type is already set in @elements, elif attackModifer() > 1.0, else set the type
     * @return 1 when already set; -1 when there is conflicting types; 0 when the type is now set to the new type
     */
    public int setType(ElementalType T) {
        if ( this.elements.contains(T)) {
            System.out.println(T + " already set!");
            return 1;
        }
        else if (attackModifier(T) > 1.0) {
            System.out.println("Can't have conflicting types!");
            return -1;
        }
        else {
            System.out.println(this.getName() + " now has " + T);
            return 0;
        }

    }

    public Double getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getPhrase() {
        return phrase;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if ( this.isFainted() ) {
            System.out.println(this.getName() + " has fainted.");
        }
        else  {
            System.out.println(this.getName() + " has " + this.getHealthPoints() + "/"+MAX_HP);
            System.out.print("Elemental type: [");
            this.elements.forEach(Type -> System.out.print(Type + ", ")); // TODO figure this out correctly https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
            System.out.println("]");
        }

        // *** TESTING PURPOSES ***
        return null; // TODO what do we return here?
    }
    // TODO continue from here
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }


    // TODO double check and delete this one or ^
    public void setName(String name) {
        this.name = name;
    }

    public static double getMaxHp() {
        return MAX_HP;
    }

    public static void setMaxHp(double maxHp) {
        MAX_HP = maxHp;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }

    public int getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getDefenseMin() {
        return defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public int getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }
}
