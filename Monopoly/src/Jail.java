public class Jail extends Square{
    private static int jailTurn = 0;
    public Jail() {
        super("Lost Island", 11, SquareType.JAIL);
    }

    public static void handlePlayerInJail(Board board, Player player, GUIV2 gui) {
        System.out.println(player.getName() + " is in Jail.");
        

        // Check if player has rolled doubles
        boolean rolledDoubles = rollDiceForJail(gui);

        if (rolledDoubles) {
            System.out.println("Congratulations! " + player.getName() + " rolled doubles and got out of Jail.");
            player.setInJail(false);
            jailTurn = 0;
        } else {
            System.out.println("Sorry, " + player.getName() + " didn't roll doubles. You are still in Jail.");

            // Increment the jail turn
            jailTurn++;

            if (jailTurn > 3) {
                // Player has been in Jail for 3 turns, pay a fine to get out
                payJailFine(50000, board, player, gui);
                player.setInJail(false);
            }
        }
    }

    private static boolean rollDiceForJail(GUIV2 gui) {
        gui.diceRoll1.roll(gui.diceLabel1);
        gui.diceRoll2.roll(gui.diceLabel2);

        gui.diceLabel1.updateUI();
        gui.diceLabel2.updateUI();
        gui.dicePanel.updateUI();

        gui.frame.repaint();

        System.out.println("Dice 1: " + gui.diceRoll1.getDice());
        System.out.println("Dice 2: " + gui.diceRoll2.getDice());

        return gui.diceRoll1.getDice() == gui.diceRoll2.getDice();
    }

    private static void payJailFine(int amount, Board board, Player player, GUIV2 gui) {
        if (player.getCash() >= amount) {
            System.out.println("You paid the fine and got out of Jail.");
            player.setCash(player.getCash() - amount);
            jailTurn = 0;
        } else {
            System.out
                    .println("You don't have enough money to pay the fine. You must sell something to cover the fee.");
            player.sellPropertiesToCoverRent(amount, gui, board);
        }
    }
}
