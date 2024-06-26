public class Event extends Square {


    public Event(String name, int location, SquareType type) {
        super(name, location, type);
    }

    public void setWorldCup(Board board, Player player, GUIV2 gui) {
        int choice;

        while (true) {
            try {
                System.out.println("Please choose a city below to hold World Cup event: ");
                printCity(player);
                System.out.println("Your choice (input a number): ");
                choice = gui.getUserInputNumber();
                if (!board.getEventCity().isEmpty()) {
                    // Remove World Cup event from the previous player's chosen city
                    City previousEventCity = board.getEventCity().get(0);
                    previousEventCity.setWorldCup(false);
                    previousEventCity.calculateVisitCost(false);
                    System.out.println("Player " + previousEventCity.getOwner().getName() +
                            " removed World Cup from " + previousEventCity.getName());
                    board.getEventCity().clear();
                }

                if (isValidChoice(choice, player.getOwnedProperty().size())) {
                    Square chosenSquare = player.getOwnedProperty().get(choice - 1);
                    if (chosenSquare instanceof City) {
                        City chosenCity = (City) chosenSquare;
                        // Now you can safely work with chosenCity
                        chosenCity.setWorldCup(true);
                        chosenCity.calculateVisitCost(true);
                
                        System.out.println("Player " + player.getName() + " successfully set World Cup at " +
                                chosenCity.getName());
                        System.out.println(chosenCity.getName() + " visit cost increased to: $" + chosenCity.getVisitCost());
                
                        if (!board.getEventCity().isEmpty()) {
                            // Remove World Cup event from the previous player's chosen city
                            City previousEventCity = board.getEventCity().get(0);
                            previousEventCity.setWorldCup(false);
                            previousEventCity.calculateVisitCost(false);
                            System.out.println("Player " + previousEventCity.getOwner().getName() +
                                    " removed World Cup from " + previousEventCity.getName());
                            board.getEventCity().clear();
                        }
                
                        board.getEventCity().add(chosenCity);
                
                        break;
                    } else {
                        // Handle the case where the chosen property is not a city
                        System.out.println("You must choose a city to hold the World Cup event!");
                    }
                } else {
                    System.out.println("Please input a valid property number!!!");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer!!!");
            }
        }
    }

    private boolean isValidChoice(int choice, int maxProperty) {

        if (choice >= 1 && choice <= maxProperty) {
            return true;
        } else {
            System.out.println("Please input a valid number between 1 and " + maxProperty + "!!!");
            return false;
        }
    }

    private void printCity(Player player) {
        for (int i = 0; i < player.getOwnedProperty().size(); i++) {
            if (player.getOwnedProperty().get(i).getType() == SquareType.CITY) {
                System.out.println((i + 1) + ". " + player.getOwnedProperty().get(i).getName() + "|Current visit cost: "
                        + player.getOwnedProperty().get(i).getVisitCost());
            }

        }
    }

}
