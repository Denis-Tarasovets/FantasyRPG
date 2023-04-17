public class Character implements  Fighter{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0)
            this.hp = 0;
        else
            this.hp = hp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public void usePotion() {
        if (potions > 0) {
            hp = 100;
            potions = potions - 1;
        }
    }

    String name;
    int dexterity;
    int strength;
    int hp;
    int xp;
    int gold;
    double critChance;
    int potions;

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public Character(String name, int dexterity, int strength, int hp, int gold) {
        this.name = name;
        this.dexterity = dexterity;
        this.strength = strength;
        this.hp = hp;
        this.gold = gold;
        this.xp = 0;
        this.potions = 0;
    }

    public void loot(Character enemy) {
        this.potions += enemy.getPotions();
        this.gold    += enemy.getGold();
        this.xp      += enemy.getXp();
        System.out.println(String.format("%s looted %d gold and %d potions and get %d exp!", getName(), enemy.getGold(), enemy.getPotions(), enemy.getXp()));
    }

    @Override
    public int attack() {
        if (dexterity * 3 > Math.random() * 100)
            return calcDmg();
        else
            return 0;
    }

    protected int calcDmg() {
        if (Math.random() < getCritChance())
            return strength * 2;
        else
            return strength;
    }
}
