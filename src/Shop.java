public class Shop {
    public static void printMenu() {
        System.out.println("Choose action:");
        System.out.println("1. Buy potion (50g)");
        System.out.println("2. Exit shop");
    }
    public static void trade(Character player, String action) {
        switch (action) {
            case "1": {
                if (player.getGold() < 50) {
                    System.out.println("Not enough gold!");
                } else {
                    player.setGold(player.getGold() - 50);
                    player.setPotions(player.getPotions() + 1);
                    System.out.println(String.format("%s have %d potions now and %d gold left", player.getName(), player.getPotions(), player.getGold()));
                }
            }
            default:
                break;
        }
    }
}
