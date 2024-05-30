import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;

public class Dice extends JPanel{

    private ImageIcon[] diceFaces = {
            new ImageIcon("dice1.png"),
            new ImageIcon("dice2.png"),
            new ImageIcon("dice3.png"),
            new ImageIcon("dice4.png"),
            new ImageIcon("dice5.png"),
            new ImageIcon("dice6.png")
    };
    private ImageIcon currentFace;

    private int valueDice;

    public int getValueDice() {
        return valueDice;
    }

    public int roll() {
        valueDice = 1 + (int) (Math.random() * 6);
        return valueDice;
    }
public Dice() {
        currentFace = diceFaces[0];
        valueDice = 1;
       
    }

    public void rollDice() {
        valueDice = (valueDice % 6) + 1;
        currentFace = diceFaces[valueDice - 1];
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentFace.paintIcon(this, g, 30, 30);
    }
}
