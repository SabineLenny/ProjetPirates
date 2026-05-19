package IHM;

import javax.swing.JLabel;

public class DiceManager {
    private JLabel dice1;
    private JLabel dice2;

    public DiceManager(JLabel dice1, JLabel dice2) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        
        this.dice1.setPreferredSize(new java.awt.Dimension(50, 50));
        this.dice2.setPreferredSize(new java.awt.Dimension(50, 50));
    }

    public void showResult(int val1, int val2) {
        dice1.setText(String.valueOf(val1));
        dice2.setText(String.valueOf(val2));
    }
}