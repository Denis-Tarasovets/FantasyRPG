import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class World {
    BufferedReader buff;
    Player player;

    public Player getPlayer() {
        return player;
    }

    public World() {
        this.player = null;
        this.buff = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mainMenu(String command) {

        String action = "";
        try {
            if (player == null) {
                if (command.equals("")) {
                    System.out.println("Enter character name:");
                } else {
                    player = new Player(command, 40, 40, 100, 100);
                    System.out.println("Welcome " + player.getName());
                    printMenu();
                }
            } else {
                if (command.equals("")) {
                    printMenu();
                } else {
                    action = command;
                }
            }

            switch (action) {
                case "1": {
                    Shop.printMenu();
                    Shop.trade(player, buff.readLine());
                    printMenu();
                    break;
                }
                case "2": {
                     new Battle(player).startFight(new Battle.EndFightCallback() {
                                                       @Override
                                                       public void fightEnd(Player player, Character monster) {
                                                           if (player.getHp() == 0) {
                                                               System.out.println("You loose!");
                                                               mainMenu("3");
                                                           } else {
                                                               player.loot(monster);
                                                               System.out.println("Continue fighting? (y/n)");
                                                           }
                                                       }
                                                   });
                     break;
                }
                case "y": {
                    mainMenu("2");
                    break;
                }
                case "n": {
                    printMenu();
                    break;
                    //mainMenu(buff.readLine());
                }
                case "3": {
                    System.out.println("Exit");
                    System.exit(1);
                    break;
                }
                default:
                    break;
            }

            mainMenu(buff.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printMenu() {
        System.out.println("Choose action:");
        System.out.println("1. Shop");
        System.out.println("2. Dark forest");
        System.out.println("3. Exit");
    }
}
