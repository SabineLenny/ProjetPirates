package IHM;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class PlayerPawn extends JLabel {
    private GameFrame mainFrame;
    private String imageRight;
    private String imageLeft;
    private int sizeX = 80;
    private int sizeY = 50;

    public PlayerPawn(GameFrame frame, String imgRight, String imgLeft, int startX, int startY) {
        this.mainFrame = frame;
        this.imageRight = imgRight;
        this.imageLeft = imgLeft;
        
        this.setBounds(startX, startY, sizeX, sizeY);
        frame.getLayeredPane().add(this, JLayeredPane.POPUP_LAYER);
        
        faceRight(); 
    }

    public void faceRight() {
        ImageUtils.setIcon(this, imageRight, sizeX, sizeY);
    }

    public void faceLeft() {
        ImageUtils.setIcon(this, imageLeft, sizeX, sizeY);
    }
    
    public void moveTo(int x, int y, boolean isFacingLeft) {
        if (isFacingLeft) {
            faceLeft();
        } else {
            faceRight();
        }
        this.setLocation(x, y);
        mainFrame.getLayeredPane().moveToFront(this);
        mainFrame.repaint();
    }

    public int getSizeX() { return sizeX; }
    public int getSizeY() { return sizeY; }    
}