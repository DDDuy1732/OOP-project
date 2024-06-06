import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Dice extends JComponent {

    private BufferedImage dice1;
    private BufferedImage dice2;
    private BufferedImage dice3;
    private BufferedImage dice4;
    private BufferedImage dice5;
    private BufferedImage dice6;

    private int diceResult;


    public Dice() {
        try {
            dice1 = ImageIO.read(new File("src/ImageIcon/dice1.png"));
            dice2 = ImageIO.read(new File("src/ImageIcon/dice2.png"));
            dice3 = ImageIO.read(new File("src/ImageIcon/dice3.png"));
            dice4 = ImageIO.read(new File("src/ImageIcon/dice4.png"));
            dice5 = ImageIO.read(new File("src/ImageIcon/dice5.png"));
            dice6 = ImageIO.read(new File("src/ImageIcon/dice6.png"));
        } catch (IOException ex){
            System.err.println("That is invalid");
        }
    }

    public int getDice() {
        return diceResult;
    }

    public void setDice(int diceNumber) {
        this.diceResult = diceNumber;
    }

    public void roll(JLabel diceLabel) {
        this.diceResult = (int)(5 * Math.random() + 1);
        diceLabel.setIcon(new ImageIcon("src/ImageIcon/dice" + diceResult + ".png"));
    }
}