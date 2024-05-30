import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DicePanel extends JPanel {
    private int diceValue;
    private int x;
    private int y;
    private int width;
    private int height;
    private int diceSize;
    private int diceSpacing;
    private int diceXOffset;
    private int diceYOffset;
    private int[] diceX;
    private int[] diceY;

    public DicePanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.diceSize = 50;
        this.diceSpacing = 10;
        this.diceXOffset = (width - 3 * diceSize - 2 * diceSpacing) / 2;
        this.diceYOffset = (height - 2 * diceSize - diceSpacing) / 2;
        this.diceX = new int[6];
        this.diceY = new int[6];
        
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();
    }

    public void rollDice() {
        diceValue = (int) (Math.random() * 6) + 1;
      
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (diceValue != 0) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);
            g.setColor(Color.WHITE);
            g.drawRect(x, y, width, height);
            for (int i = 0; i < diceValue; i++) {
                g.setColor(Color.RED);
                g.fillRoundRect(diceX[i] + diceXOffset, diceY[i] + diceYOffset, diceSize, diceSize, 10, 10);
                g.setColor(Color.BLACK);
                g.drawRoundRect(diceX[i] + diceXOffset, diceY[i] + diceYOffset, diceSize, diceSize, 10, 10);
            }
        }
    }

    public void stopAnimation() {
     
        diceValue = 0;
        repaint();
    }
}
 
    

