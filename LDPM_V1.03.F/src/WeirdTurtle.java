import utilities.Dice;

public class WeirdTurtle extends Monster {
    private final int DEFENSE_MIN=3;
    private final int DEFENSE_MAX=8;
    private final int ATTACK_MIN=3;
    private final int ATTACK_MAX=8;

    public WeirdTurtle (String s) {
        super(s, ElementalType.WATER);

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
