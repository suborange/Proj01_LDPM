public class WeirdTurtle extends Monster {

    private final int DEFENSE_MIN=3;
    private final int DEFENSE_MAX=8;
    private final int ATTACK_MIN=3;
    private final int ATTACK_MAX=8;

    WeirdTurtle(String s) {
        super(s, ElementalType.WATER);

        setDefenseMin(DEFENSE_MIN);
        setDefenseMax(DEFENSE_MAX);
        setAttackMin(ATTACK_MIN);
        setAttackMax(ATTACK_MAX);
    }

    public void setAttackPoints() {
        Dice.roll(ATTACK_MIN, ATTACK_MAX);
    }

    public void setDefensePoints() {
        Dice.roll(DEFENSE_MIN, DEFENSE_MAX);
    }
}
