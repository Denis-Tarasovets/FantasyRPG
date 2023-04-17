public class Battle {
    interface EndFightCallback {
        void fightEnd(Player player, Character monster);
    }
    public Battle(Player player) {
        this.player = player;
    }

    Player player;
    Character monster;
    public Character spawnMonster() {
        if (Math.random() > 0.5) {
            System.out.println("Goblin appears!");
            return new Goblin("Goblin", 30,30, 100, 50);
        } else {
            System.out.println("Skeleton appears!");
            return new Skeleton("Skeleton", 30,30, 100, 50);
        }
    }

    public void startFight(EndFightCallback endFightCallback) {
        monster = spawnMonster();

        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("Turn " + turn);
                turn += 1;
                if (turn % 2 != 0) {
                    isFightEnded = makeTurn(monster, player, endFightCallback);
                } else {
                    isFightEnded = makeTurn(player, monster, endFightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean makeTurn(Character defender, Character attacker, EndFightCallback endFightCallback) {
        int dmg = attacker.attack();

        if ((attacker == player) && (player.getHp() < 30) && player.getPotions() > 0) {
            player.usePotion();
            System.out.println(String.format("%s used potion and now have %d hp and %d potions left", player.getName(), player.getHp(), player.getPotions()));
        }

        if (dmg == 0) {
            System.out.println(String.format("%s missed!", attacker.getName()));
        } else {
            defender.setHp(defender.getHp() - dmg);
            System.out.println(String.format("%s deals %d damage! %s %d hp left", attacker.getName(), dmg, defender.getName(), defender.getHp()));
        }

        if (defender.getHp() <= 0) {
            endFightCallback.fightEnd(player, monster);
            return true;
        } else {
            return false;
        }
    }
}
