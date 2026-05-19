package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class BoardSquare extends JLabel {
    
    public enum SquareType {
        NORMAL, BOMB, HEAL, POISON, EXCHANGE
    }
    
    private int squareNumber;
    private GameFrame mainFrame;
    private SquareType type;

    public BoardSquare(GameFrame frame, int number) {
        this.mainFrame = frame;
        this.squareNumber = number;
        this.type = SquareType.NORMAL;
        
        this.setPreferredSize(new Dimension(50, 50));
        this.setMinimumSize(new Dimension(50, 50));
        this.setMaximumSize(new Dimension(50, 50));
        
        this.setFont(new Font("Segoe UI", Font.BOLD, 14));
        this.setForeground(Color.WHITE);
        this.setText(String.valueOf(number));
        
        setSquareIcon("island.png");
    }
    
    public void setSpecialType(SquareType newType) {
        this.type = newType;
        
        switch (newType) {
            case BOMB:
                setSquareIcon("bomb.png");
                break;
            case HEAL:
                setSquareIcon("heal.png");
                break;
            case POISON:
                setSquareIcon("poison.png");
                break;
            case EXCHANGE:
                setSquareIcon("exchange.png");
                break;
            case NORMAL:
            default:
                setSquareIcon("island.png");
                break;
        }
    }

    public void setSquareIcon(String imagePath) {
        ImageUtils.setIcon(this, imagePath, 60, 60);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getIcon() != null) {
            int iconX = (getWidth() - getIcon().getIconWidth()) / 2;
            int iconY = (getHeight() - getIcon().getIconHeight()) / 2;
            getIcon().paintIcon(this, g, iconX, iconY);
        }
        
        if (getText() != null && !getText().isEmpty()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setFont(getFont());
            
            FontMetrics fm = g2.getFontMetrics();
            int x = 4; 
            int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent(); 
            
            g2.setColor(Color.BLACK);
            g2.drawString(getText(), x - 1, y - 1);
            g2.drawString(getText(), x - 1, y + 1);
            g2.drawString(getText(), x + 1, y - 1);
            g2.drawString(getText(), x + 1, y + 1);
            
            g2.setColor(getForeground());
            g2.drawString(getText(), x, y);
            
            g2.dispose();
        }
    }
}