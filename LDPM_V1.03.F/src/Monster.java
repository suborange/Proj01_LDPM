/**
 * @author Ethan Bonavida
 * @since March 18 2023
 * @version 1.03.F: check all other TODO's left in monster.java; cleaned up comments, and el fin;
 * DESCRIPTION: LDPM - a program that emulates a game of monsters that have types and can attack each other, with modifiers based off certain attacking and defending types.
 * much better than the famous game by nintendo
 */

/**
 * VERSIONS
 * -0.01.a: make all classes, fill out Monster class with all required vars, methods and more.
 * -0.01.b: continue with filling out the foundation to Monster.java; finished getPhrase(), attackModifer(), calculateDefensePoints(), calculateAttackPoints(), and takeDamage();
 *  need to confirm toString() and setPhrase(); cleaned up references and comments
 * -0.01.c: continue with the rest and finish Monster.java; finished setPhrase() and attack();
 * -0.02.a: start implementing the other classes and their functionalities; fully fixed setPhrase using instanceof; Ran through initial test and passed most;
 * -0.03.a: fixed toString() ( it returns all the stuff, duh!!); fixed adding negative attack, causing health overflow; fixed setting the default attackPoints with dice.roll(); all tests passed :)
 * -0.03.b: need to double check Monster(); fixed all method signatures; all tests still pass with flying colors :)
 * -1.03.F: check all other TODO's left in monster.java; cleaned up comments, and el fin;
 *
 */

import utilities.Dice;
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

    // **  my CONSTANTS **
    public static final double WEAK = 0.5;
    public static final double STRONG = 2.0;
    public static final double NORMAL = 1.0;

    // req CONSTANTS
    private static double MAX_HP = 20.0; // will probably have some value
    private List<ElementalType> elements = new ArrayList<>();
    private Double healthPoints = MAX_HP;
    private String phrase = "";
    private String name = "";
    private boolean fainted = false;
    private int attackMin = 1;
    private int attackMax = 10;
    private int defenseMin = 1;
    private int defenseMax = 10;
    protected int defensePoints = 10;
    protected int attackPoints = 10;

    // ** all my code **

    // ** CONSTRUCTOR **
    public Monster(String n, ElementalType T ) { // all good
        this.name=n;
        this.elements.add(T);
        setPhrase(this); // should be fine not using returned monster
    }

    // ** METHODS **
    // ** private **

    /** constructor to take in an object, and check the type against available object types, and then set the phrase according to the type
     * @param M the object passed in to test for the object type
     * @return tbh no clue..
     */
    private static Monster setPhrase(Monster M) {
        if ( M instanceof WeirdTurtle ) {
            M.setPhrase("'Urtle!");
        }
        else if ( M instanceof FireLizard )  {
            M.setPhrase("Deal with it.");
        }
        else if ( M instanceof ElectricRat ) {
            M.setPhrase("'Lectric!");
        }
        else if ( M instanceof FlowerDino ) {
            M.setPhrase("Flowah!");
        }
        else {
            M.setPhrase("\"No phrase for me!\"");
        }
        return M;
    }

    // ** protected **

    /** takes in an object of a monster that is being attacked. calculate this monsters defense.
     * @param M the current monster to calculate the defense for. roll the dice from its min and max defense, and see if it has courage, or not paying attention
     * @return if it has courage, + 1 and * 2 to defense; if it is not paying attention, print.
     */
    protected double calculateDefensePoints(Monster M) {
        double defense_roll = Dice.roll(M.defenseMin, M.defenseMax); // roll between min and max ; wait is this right? we roll for attack points and defense points in each monster.
        // double defense_roll =M.getDefensePoints(); TODO should we be using @defensePoints here? or re rolling? we set them up but it seems like its never used..?

        // courage is based on even number and having less than half of max defense
        if ( HasCourage(defense_roll, M) ) {
            System.out.println(M.getName() + " finds the courage in the desperate situation");
            defense_roll++; // first increment
            defense_roll *= 2; // then double defense
        }
        else if (defense_roll == M.defenseMin) {
            System.out.println(M.getName() + " is clearly not paying attention.");
        }
        return defense_roll;
    }

    /** finds if the given monster passed in has the courage to withstand the oncoming attack.
     * @param d_roll the defense roll given to the monster
     * @param _M the monster that needs to find courage (or not)
     * @return true when the monster has found courage; false when the monster is a coward
     */
    private boolean HasCourage(double d_roll, Monster _M) {
        double def_Max = (double)_M.defenseMax;       // temporary minimum defense value in float
        boolean has_def = d_roll < HalfOf(def_Max); // will they have right defense for courage?
        boolean is_Even = d_roll % 2 == 0;          // is the defense even?

        // if roll is even, and less than half of max defense
        return ( is_Even && has_def);
    }

    /** to help find half of the number passed in.
     * @param num number to divide in half
     * @return halved number
     */
    private double HalfOf(double num) {
        return (num / 2); // divide in half
    }

    /*** modifies the attack based of all the typings:
     * Electric is STRONG vs Water, WEAK vs Electric & Grass;
     * Fire is STRONG vs Grass, and WEAK vs Fire & Water;
     * Grass is STRONG vs Water, and WEAK vs Fire & Grass;
     * Water is STRONG bs Fire, WEAK vs Grass & Water
     *
     * @param defending the element type to be compared to the values stored in @this.elements; based on type attack modifier is chosen
     * ex: if @this.elements contains 'ELECTIRC' and the type passed in (defending) like water, then the value 2.0 is returned (super effective!)
     * ex: of @this.elements contains 'GRASS' and the defending type is 'ELECTRIC', then return 0.5
     * @return 2.0 if defending is weak to the attacking type (super effective); 0.5 if defending is strong to attacking type( not very effective); else return 1.0
     */
    protected double attackModifier(ElementalType defending) {
       switch (defending) { // TODO can try enhanced switch?
           case ELECTRIC: // this is electric defense
               // if electric is defending against  @ELECTRIC, then is WEAK!. there is no strengths...
               if ( this.elements.contains(ElementalType.ELECTRIC) ) {
                   // WEAK
                   return WEAK;
               }
               else {
                   return NORMAL;
               }
           case FIRE: // this is fire defense
               // if fire is defending against @WATER, then is STRONG!
               if ( this.elements.contains(ElementalType.WATER) ) {
                    return STRONG;
                }
               // else if fire is defending against @FIRE or @GRASS then is WEAK!
               // TODO is it worth trying to reduce this to else, and make this if electric then return normal??? save one comparison?
               else if ( this.elements.contains(ElementalType.FIRE) || this.elements.contains(ElementalType.GRASS) ) {
                   return WEAK;
               }
               else {
                   return  NORMAL;
               }
           case GRASS: // this is grass defense
               // if grass is defending against @FIRE, then is STRONG!
               if ( this.elements.contains(ElementalType.FIRE) ) {
                   return STRONG;
               }
               else { // everything else is just weak. not normal modifier here!
                   return  WEAK;
               }
           case WATER: // this is water defense
               // if water is defending against @ELECTRIC or @ GRASS, then is STRONG
                if ( this.elements.contains(ElementalType.ELECTRIC) || this.elements.contains(ElementalType.GRASS) ) {
                    return STRONG;
                }
               else { // else is defending against @FIRE or @WATER so is WEAK
                   return WEAK;
                }
       } // end switch

       return NORMAL; // default to 1.0 if something goes wrong?
    }

    // ** non-setter/getters **
    boolean isFainted() { // is this monster fainted or not?
        return this.fainted;
    }

    /**This method takes a Monster object as a parameter and calculates the attack points the current object will use against it. The Monster object being passed in is the Monster being attacked.
     * @param M the monster being attacked
     * @return the value calculated from takeDamage()
     */
    double attack(Monster M) {
        // has the current monster fainted or not? ( so this?)
        if ( this.isFainted() ) {
            // "{name} isn't conscious… it can't attack."
            System.out.println(this.getName() + " isn't conscious... it can't attack.");
            return 0.0;
        }
        else {
            // "{name} is attacking {monster name}"
            System.out.println(this.getName() + " is attacking " + M.getName() );
            System.out.println(this.getPhrase()+ " " + this.getPhrase() ); // the attacker phrase ( twice, for two times the fun!)
            // calculate the attack points of this monster attacking M and its types
            double temp_attack = calculateAttackPoints(this, M.getElements() );
            // "{name} is attacking with {attack value}"
            System.out.println(this.getName() + " is attacking with " + temp_attack + " attack points!");

            // calculate the damage taken for the monster?
            double taken_damage = M.takeDamage(temp_attack);

            // calculate if monster is confused while fighting
            if ( this.equals(M) && taken_damage > 0 ) {
                // "{name} hurt itself in the confusion."
                System.out.println(this.getName() + " hurt itself in the confusion.");
            }
            return taken_damage;
        }
    }

    /** given the attack, calculates the damage to take for this monster object
     * @param attackValue the attack value to be passed in to calculate how much damage the monster takes.
     * @return the damage the monster will take based on conditions
     */
    double takeDamage(double attackValue) {
        // ** ATTACK POINTS **
        double temp_defense = calculateDefensePoints(this);
        attackValue -= temp_defense; // subtract the defense from the attack

        if (attackValue > 0 ) {
            // "{name} is hit for {attack points} damage!"
            System.out.println(this.getName() + " is hit for " + attackValue + " damage!");
        }
        else if (attackValue == 0) {
            // "{name} is nearly hit!."
            System.out.println(this.getName() + " is nearly hit!");
        }
        else if (attackValue < HalfOf(temp_defense)) {
            // "{name} shrugs off the puny attack."
            System.out.println(this.getName() + " shrugs off the puny attack.");
        }

        // ** HEALTH POINTS **
        Double temp_HP = this.getHealthPoints();
        // dont add negative attack, making nigher than max health. just make it 0
        if (attackValue >= 0 ){
            this.setHealthPoints(temp_HP - attackValue); // subtract the damage taken from HP
        }

        if (this.getHealthPoints() <= 0) {
            // "{name} has faint-- passed out. It's passed out."
            System.out.println(this.getName() + " has faint-- passed out. It's passed out." );
            this.fainted = true;
        }
        else {
            // "{name} has {healthPoints} / {MAX_HP} HP remaining"
            System.out.println(this.getName() + " has " + this.getHealthPoints() + "/"+ MAX_HP + " HP remaining");
        }

        return attackValue; // this should return the attack points value back to somewhere? why?
    }

    /** Calculates the attack points of the monster based on the enemy types
     * @param M the monster to find the attack points of;
     * @param enemyT all the types of the enemy to be compared against the monster types and modify the attack points
     * @return the modified attack roll, either NORMAL, STRONG, or WEAK (depending on how many enemy types)
     */
    double calculateAttackPoints(Monster M, List<ElementalType> enemyT) {
        double attack_roll = Dice.roll(M.attackMin, M.attackMax);
        // TODO use @attackPoints here?
        double modifier = NORMAL;

        System.out.println(M.getName() + " rolls a " + attack_roll);
        // loop through each enemy Type and calculate to modifier
        for (ElementalType type : enemyT) {
            modifier *= attackModifier(type); // multiply by the attack modifier for each type from enemy
        }
        // its super effective!
        if ( modifier >= STRONG) {
            System.out.println("It's su-- *cough* very effective!");
        }

        return attack_roll * modifier; // should be assigned back to monsters attack damage
    }

    /** override toString() to display all this information when printing the object itself.
     * @return all this lovely string stuff :)
     */
    @Override
    public String toString() {
        if ( this.isFainted() ) {
            return this.getName() + " has fainted.";
        }
        else  {
            String object_info = "";
            object_info += this.getName() + " has " + this.getHealthPoints() + "/" + MAX_HP + " hp";
            object_info +="\nElemental type: [";
            // TODO optimize all this, seems it can be done much simpler, if its worth working on.
            if (elements.size() == 1 ) {
                object_info += this.elements.get(0); // get the first and only element
            }
            else {
                for (ElementalType t : elements) {
                    // if its the last one, then only add the element and not a comma
                    if (elements.get(elements.size() - 1) == t) {
                        object_info += t;
                    }
                    else { // not the last element, so add the element and then add a comma for next or last value.
                        object_info += t + ", ";
                    }
                } // DEPRECATED ~lambda expression figure this out correctly https://www.geeksforgeeks.org/arraylist-foreach-method-in-java/
            }
            object_info += "]";
            return object_info;
        }
    }

    // ** setters/getters **
    public List<ElementalType> getElements() {
        return this.elements;
    }

    /** prints out message given the type, returning corresponding code.
     * @param T the type to be passed to set, based on if type is already set in @elements, elif attackModifer() > 1.0, else set the type
     * @return 1 when already set; -1 when there is conflicting types; 0 when the type is now set to the new type
     */
    public int setType(ElementalType T) {
        if ( this.elements.contains(T) ) {
            System.out.println(T + " already set!");
            return 1;
        }
        else if (attackModifier(T) > NORMAL) {
            System.out.println("Can't have conflicting types!");
            return -1;
        }
        else {
            this.elements.add(T); // yes add type here
            System.out.println(this.getName() + " now has " + T);
            return 0;
        }
    }

    public Double getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(Double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getPhrase() {
        return this.phrase; // return {phrase} {phrase}???
    }
    public String getName() {
        return this.name;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

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
        return this.attackMin;
    }

    public void setAttackMin(int attackMin) {
        this.attackMin = attackMin;
    }

    public int getDefenseMin() {
        return this.defenseMin;
    }

    public void setDefenseMin(int defenseMin) {
        this.defenseMin = defenseMin;
    }

    public int getDefenseMax() {
        return this.defenseMax;
    }

    public void setDefenseMax(int defenseMax) {
        this.defenseMax = defenseMax;
    }

    public int getAttackMax() {
        return this.attackMax;
    }

    public void setAttackMax(int attackMax) {
        this.attackMax = attackMax;
    }


    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public abstract void setAttackPoints();

    public int getDefensePoints() {
        return this.defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public abstract void setDefensePoints();
}
