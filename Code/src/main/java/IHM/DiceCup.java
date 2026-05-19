package IHM;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class DiceCup extends JLabel {
    private final GameFrame mainFrame;
    private final int startX, startY;
    private boolean isAnimating = false; 
    private boolean isCoveringDice = false;
    private boolean isLocked = false;
    private Point initialClick;
    private int totalDistance = 0;
    private int lastX, lastY;
    private final int SHAKE_THRESHOLD = 300; 

    public DiceCup(GameFrame frame, int startX, int startY) {
        this.mainFrame = frame;
        this.startX = startX;
        this.startY = startY;
        this.setBounds(startX, startY, 80, 80);
        
        ImageUtils.setIcon(this, "dicecup.png", 150, 150);
        setupMouseListeners();
    }
    
    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    private void setupMouseListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isAnimating || isLocked) return; 
                if (isCoveringDice) {
                    revealDiceSequence();
//                    mainFrame.animateMovement(mainFrame.lancerLesDes());
                      mainFrame.playTurn();
                    return; 
                }
                initialClick = e.getPoint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isAnimating || isCoveringDice || isLocked) return; 
                if (totalDistance > SHAKE_THRESHOLD) {
                    animateCupSequence(); 
                } else {
                    setLocation(startX, startY); 
                }
                totalDistance = 0; 
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isAnimating || isCoveringDice || isLocked) return; 
                int X = getLocation().x + e.getX() - initialClick.x;
                int Y = getLocation().y + e.getY() - initialClick.y;
                setLocation(X, Y);
                
                totalDistance += Math.abs(e.getX() - lastX) + Math.abs(e.getY() - lastY);
                lastX = e.getX();
                lastY = e.getY();
            }
        });
    }
    
     private void animateCupSequence() {
        isAnimating = true; 
        
        Point dice1Pos = SwingUtilities.convertPoint(mainFrame.getTopPanel(), mainFrame.getDice1Panel().getX(), mainFrame.getDice1Panel().getY(), mainFrame.getLayeredPane());
        Point dice2Pos = SwingUtilities.convertPoint(mainFrame.getTopPanel(), mainFrame.getDice2Panel().getX(), mainFrame.getDice2Panel().getY(), mainFrame.getLayeredPane());

        int targetX = ((dice1Pos.x + dice2Pos.x) / 2) - 45; 
        int targetY = dice1Pos.y - 27;  

        Timer slideInTimer = new Timer(20, null);
        slideInTimer.addActionListener(e -> {
            int curX = this.getX();
            int curY = this.getY();
            int dx = targetX - curX;
            int dy = targetY - curY;
            
            if (Math.abs(dx) <= 6 && Math.abs(dy) <= 6) {
                this.setLocation(targetX, targetY); 
                slideInTimer.stop(); 
                                
                isCoveringDice = true; 
                isAnimating = false; 
            } else {
                this.setLocation(curX + (dx / 6), curY + (dy / 6));
            }
        });
        slideInTimer.start(); 
    }

    private void revealDiceSequence() {
        isAnimating = true; 
        isCoveringDice = false; 
        
        int homeX = startX;   
        int homeY = startY;

        Timer slideOutTimer = new Timer(20, null);
        slideOutTimer.addActionListener(e -> {
            int cx = this.getX();
            int cy = this.getY();
            int homedx = homeX - cx;
            int homedy = homeY - cy;
            
            if (Math.abs(homedx) <= 6 && Math.abs(homedy) <= 6) {
                this.setLocation(homeX, homeY);
                slideOutTimer.stop();
                
                isAnimating = false; 
            } else {
                this.setLocation(cx + (homedx / 6), cy + (homedy / 6));
            }
        });
        slideOutTimer.start();
    }

}