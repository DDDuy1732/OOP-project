
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Monopoly extends JPanel {
    Scanner sc = new Scanner(System.in);
    private Queue<Player> playerQueue;
    private int step;
    private GUIV2 gui;

    public GUIV2 getGui() {
        return gui;
    }

    public void setGui(GUIV2 gui) {
        this.gui = gui;
    }

    private static Monopoly instance;
    int numPlayers = 0;

    public Queue<Player> getPlayerQueue() {
        return playerQueue;
    }

    public void setPlayerQueue(Queue<Player> playerQueue) {
        this.playerQueue = playerQueue;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public static void main(String[] args) {

        Board board = new Board();
        Monopoly monopoly = new Monopoly();
        monopoly.initializeGame(board, monopoly);

    }

    public void initializeGame(Board board, Monopoly monopoly) {
        new StartMenuGUI(monopoly, board);

    }

    public boolean isGameOver(Board board) {
        int activePlayers = 0;
        for (Player player : board.getPlayers()) {
            if (!player.isBankrupt()) {
                activePlayers++;

            }
        }

        if (activePlayers <= 1) {
            return true;
        }

        for (int i = 0; i < board.getPlayers().size(); i++) {
            if (board.getPlayers().get(i).totalNumberOfBeach() == 4) {
                System.out.println("Player " + board.getPlayers().get(i).getName() + " has owned four beaches.");
                return true;
            }
        }

        return false;
    }

    public static Monopoly getInstance() {
        if (instance == null) {
            instance = new Monopoly();
        }
        return instance;
    }

    public void initializePlayerQueqe(Board board) {
        playerQueue = new LinkedList<>(board.getPlayers());
    }

    public void runRound(Board board, GUIV2 gui) {
        Player currentPlayer = playerQueue.poll();
    
        if (!currentPlayer.isInJail()) {
            takeTurn(currentPlayer, board, gui);
        } else if (currentPlayer.isInJail()) {
            currentPlayer.handleJailInteraction(board, gui);
        }
    
        playerQueue.offer(currentPlayer);
        if (currentPlayer.isBankrupt()) {
            System.out.println("Player " + currentPlayer.getName() + " is bankrupt!!!");
            removePlayerFromQueue(currentPlayer);
            gui.removePlayerFromGUI(board);
        }
    }

    public void removePlayerFromQueue(Player player) {
        playerQueue.remove(player);
    }

    public void takeTurn(Player player, Board board, GUIV2 gui) {
        System.out.println("Player " + player.getName() + "'s turn");

        if (player.isBankrupt()) {
            System.out.println("Player " + player.getName() + " is bankrupt!");
            return;
        }
        
        int currentLocation = player.getLocation();
        int step = player.rollDice(gui);
        
        currentLocation += step;

        if (currentLocation > 40) {
            player.setCash(player.getCash() + 200000);
            System.out.println("Player " + player.getName() + " complete a full round and receive $200,000");
            System.out.println("Player " + player.getName() + "'s new balance: $" + player.getCash());
            currentLocation -= 40;
            player.setLocation(currentLocation);

        }

        else {
            player.setLocation(currentLocation);
        }

        gui.updatePlayerPosition(board, player);
        
        player.interactWithSquare(board, gui);
        gui.updatePlayerCash(board, player);
        
    }

    public void printWelcome(Board board) {

        System.out.println("---------Welcome to Monopoly---------");
        System.out.println("This game is made by group 4");
        System.out.println("Let's the game begin!!!!");

    }

    public void initializePlayers(Board board) {

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "How many players?", "Player number?",
                        JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    break;
                }
                numPlayers = Integer.parseInt(input);
                if (numPlayers > 1 && numPlayers <= 4) {
                    JOptionPane.showMessageDialog(null, "Number of players: " + numPlayers, "Player quantity",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                } else if (numPlayers == 1) {
                    JOptionPane.showMessageDialog(null, "There must be at least 2 players to start the game",
                            "ERROR: Invalid number of player!!!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "This game only allow maximum 4 players",
                            "ERROR: Invalid number of player!!!", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Please input an integer", "ERROR: Invalid input!!!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        for (int i = 0; i < numPlayers; i++) {
            String name = JOptionPane.showInputDialog(null, "Enter player " + (i + 1) + " name: ", "Player name?",
                    JOptionPane.QUESTION_MESSAGE);
            if (name == null) {
                System.exit(0);
            }

            Color color = JColorChooser.showDialog(null, "Pick a color", Color.BLACK);
            Player player = new Player();
            player.setName(name);
            player.setColor(color);
            board.getPlayers().add(player);
            
        }
    }

    public void createSquare(Board board) {
        Start Go = new Start();
        board.getSquares().add(Go);

        City DaLat = new City("Da Lat", 2, 50000, 0, 50000);
        board.getSquares().add(DaLat);
        City NhaTrang = new City("Nha Trang", 3, 52000, 0, 50000);
        board.getSquares().add(NhaTrang);
        City HaNoi = new City("Ha Noi", 4, 55000, 0, 50000);
        board.getSquares().add(HaNoi);
        City HoChiMinh = new City("Ho Chi Minh", 5, 60000, 0, 50000);
        board.getSquares().add(HoChiMinh);
        Card chance1 = new Card("Chance", 6);
        board.getSquares().add(chance1);
        City Seoul = new City("Seoul", 7, 65000, 0, 50000);
        board.getSquares().add(Seoul);
        City Busan = new City("Busan", 8, 80000, 0, 50000);
        board.getSquares().add(Busan);
        City Incheon = new City("Incheon", 9, 80000, 0, 50000);
        board.getSquares().add(Incheon);
        Beach Fiji = new Beach("Fiji", 10, 50000);
        board.getSquares().add(Fiji);

        Jail jail = new Jail();
        board.getSquares().add(jail);

        City Tokyo = new City("Tokyo", 12, 100000, 0, 70000);
        board.getSquares().add(Tokyo);
        City Kyoto = new City("Kyoto", 13, 110000, 0, 70000);
        board.getSquares().add(Kyoto);
        City Osaka = new City("Osaka", 14, 120000, 0, 70000);
        board.getSquares().add(Osaka);
        Company Electrical = new Company("Electrical", 15, 50000);
        board.getSquares().add(Electrical);
        Card chance2 = new Card("Chance", 16);
        board.getSquares().add(chance2);
        City Cairo = new City("Cairo", 17, 130000, 0, 70000);
        board.getSquares().add(Cairo);
        City Nairobi = new City("Nairobi", 18, 140000, 0, 70000);
        board.getSquares().add(Nairobi);
        City Marrakech = new City("Marrakech", 19, 150000, 0, 70000);
        board.getSquares().add(Marrakech);
        Beach Bahamas = new Beach("Bahamas", 20, 50000);
        board.getSquares().add(Bahamas);

        Event event = new Event("World Cup", 21, SquareType.EVENT);
        board.getSquares().add(event);

        City Paris = new City("Paris", 22, 170000, 0, 80000);
        board.getSquares().add(Paris);
        City London = new City("London", 23, 185000, 0, 80000);
        board.getSquares().add(London);
        City Rome = new City("Rome", 24, 200000, 0, 80000);
        board.getSquares().add(Rome);
        City Barcelona = new City("Barcelona", 25, 215000, 0, 80000);
        board.getSquares().add(Barcelona);
        Card chance3 = new Card("Chance", 26);
        board.getSquares().add(chance3);
        City Berlin = new City("Berlin", 27, 230000, 0, 80000);
        board.getSquares().add(Berlin);
        City Amsterdam = new City("Amsterdam", 28, 245000, 0, 80000);
        board.getSquares().add(Amsterdam);
        City Venice = new City("Venice", 29, 260000, 0, 80000);
        board.getSquares().add(Venice);
        Beach Prague = new Beach("Prague", 30, 50000);
        board.getSquares().add(Prague);

        Plane plane = new Plane();
        board.getSquares().add(plane);

        Company Water = new Company("Water", 32, 50000);
        board.getSquares().add(Water);
        City Shenzhen = new City("Shenzhen", 33, 270000, 0, 100000);
        board.getSquares().add(Shenzhen);
        City Guangzhou = new City("Guangzhou", 34, 270000, 0, 100000);
        board.getSquares().add(Guangzhou);
        City Shanghai = new City("Shanghai", 35, 270000, 0, 100000);
        board.getSquares().add(Shanghai);
        City Beijing = new City("Beijing", 36, 300000, 0, 100000);
        board.getSquares().add(Beijing);
        Tax tax = new Tax();
        board.getSquares().add(tax);
        City LosAngeles = new City("Los Angeles", 38, 360000, 0, 100000);
        board.getSquares().add(LosAngeles);
        City NewYork = new City("New York", 39, 360000, 0, 100000);
        board.getSquares().add(NewYork);
        Beach Maldives = new Beach("Maldives", 40, 50000);
        board.getSquares().add(Maldives);
    }

    public void printResult(Board board) {
        System.out.println("-------------Game Over-------------");
        System.out.println("Results:");
        Player winner = new Player();
        for (Player player : board.getPlayers()) {
            if (!player.isBankrupt()) {
                winner = player;
            } else if (player.totalNumberOfBeach() == 4) {
                winner = player;

            }
        }
        System.out.println("The winner is: " + winner.getName());

        for (Player player : board.getPlayers()) {
            int totalMoney = player.getCash() + player.calculateTotalPropertyValue();
            System.out.println("Player " + player.getName() + ": $" + totalMoney);
        }
    }
}
