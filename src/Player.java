public class Player extends Character{
    public Player(String name, int dexterity, int strength, int hp, int gold) {
        super(name, dexterity, strength, hp, gold);
        this.critChance = 0.3;
    }
}
