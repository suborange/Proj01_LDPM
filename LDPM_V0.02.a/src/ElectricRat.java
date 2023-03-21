public class ElectricRat extends Monster {
    private final int DEFENSE_MIN=5;
    private final int DEFENSE_MAX=8;
    private final int ATTACK_MIN=5;
    private final int ATTACK_MAX=8;


    ElectricRat (String s) {
        super(s, ElementalType.ELECTRIC);
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
