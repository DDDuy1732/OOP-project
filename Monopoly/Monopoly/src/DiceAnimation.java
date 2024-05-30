import javax.swing.*;
import java.awt.*;

public class DiceAnimation {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Dice(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}