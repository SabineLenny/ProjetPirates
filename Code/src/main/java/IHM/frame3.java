package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.SwingUtilities; // Added for Global Coordinates
import javax.swing.SwingConstants; // Added for Dice Label centering

/**
 * @author DWR4418A
 */
public class frame3 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frame3.class.getName());

    private JLabel[] squares = new JLabel[30];
    
    // --- PLAYER & GAME STATE VARIABLES ---
    private String player1Name;
    private String player2Name;
    private JLabel player1PawnLabel;
    private JLabel player2PawnLabel;
    private int playerPos = 1; 
    
    // --- CUP ANIMATION VARIABLES ---
    private JLabel cup;
    private boolean isAnimating = false; 
    private boolean isCoveringDice = false;
    Point initialClick;
    private int totalDistance = 0;
    private int lastX, lastY;
    private final int SHAKE_THRESHOLD = 300; 
    
    private int cupStartingX = 400;
    private int cupStartingY = 0;
    
    private int pawnSizeX = 80;
    private int pawnSizeY = 50;
    
    // --- UI DICE VARIABLES ---


    public frame3() {

        askPlayerNames();

        initComponents();
        
        setResizable(false); 
        
        dice1Label = new JLabel("?", SwingConstants.CENTER);
        dice1Label.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(dice1Label, java.awt.BorderLayout.CENTER);
        
        dice2Label = new JLabel("?", SwingConstants.CENTER);
        dice2Label.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(dice2Label, java.awt.BorderLayout.CENTER);
        
        
        dice1Label.setPreferredSize(new Dimension(50, 50));
        dice1Label.setMinimumSize(new Dimension(50, 50));
        dice2Label.setPreferredSize(new Dimension(50, 50));
        dice2Label.setMinimumSize(new Dimension(50, 50));
        

        // Elouan
        player1NameLabel.setText(player1Name);
        player2NameLabel.setText(player2Name);
        //
        createBoard();

        for(int i = 1; i <= 30; i++){
            setSquareImage(i, "island.png");
        }

        initBoardNumber();

        setSquareImage(9, "bomb.png");
        setSquareImage(11, "exchange.png");
        setSquareImage(13, "heal.png");
        setSquareImage(15, "poison.png");

        player1PawnLabel = new JLabel(); // removed JLabel declaration here
        player1PawnLabel.setBounds(50, 50, pawnSizeX, pawnSizeY);

        this.getLayeredPane().add(player1PawnLabel, JLayeredPane.POPUP_LAYER);
        setLabelIcon(player1PawnLabel, "pirateship.png", pawnSizeX, pawnSizeY);
        
        SwingUtilities.invokeLater(() -> {
            movePlayerToSquare(player1PawnLabel, playerPos);
        });
        
        player2PawnLabel = new JLabel(); // removed JLabel declaration here
        player2PawnLabel.setBounds(75, 75, pawnSizeX, pawnSizeY);

        this.getLayeredPane().add(player2PawnLabel, JLayeredPane.POPUP_LAYER);
        setLabelIcon(player2PawnLabel, "pirateship2.png", pawnSizeX, pawnSizeY);
        
        SwingUtilities.invokeLater(() -> {
            movePlayerToSquare(player2PawnLabel, playerPos);
        });
        
        initCup();
    }

    private void initCup(){
        cup = new JLabel(); 
        cup.setBounds(cupStartingX, cupStartingY, 80, 80); 
        
        this.getLayeredPane().add(cup, JLayeredPane.DRAG_LAYER);
        setLabelIcon(cup, "dicecup.png", 150, 150);

        cup.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isAnimating) return; 

                if (isCoveringDice) {
                    revealDiceSequence();
                    animateMovement(lancerLesDes());
                    return; 
                }
                initialClick = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                if (isAnimating || isCoveringDice) return; 
                
                if (totalDistance > SHAKE_THRESHOLD) {
                    animateCupSequence(); 
                } else {
                    cup.setLocation(cupStartingX, cupStartingY); 
                }
                totalDistance = 0; 
            }
        });

        cup.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isAnimating || isCoveringDice) return; 
                
                int thisX = cup.getLocation().x;
                int thisY = cup.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int X = thisX + xMoved;
                int Y = thisY + yMoved;

                cup.setLocation(X, Y);
                
                int deltaX = e.getX() - lastX;
                int deltaY = e.getY() - lastY;
                totalDistance += Math.abs(deltaX) + Math.abs(deltaY);

                lastX = e.getX();
                lastY = e.getY();
            }
        });
    }

    public void animateCupSequence() {
        isAnimating = true; 
        
        Point dice1Pos = SwingUtilities.convertPoint(topPanel, jPanel3.getX(), jPanel3.getY(), this.getLayeredPane());
        Point dice2Pos = SwingUtilities.convertPoint(topPanel, jPanel4.getX(), jPanel4.getY(), this.getLayeredPane());

        int targetX = ((dice1Pos.x + dice2Pos.x) / 2) - 45; 
        int targetY = dice1Pos.y - 27;  

        Timer slideInTimer = new Timer(20, null);
        slideInTimer.addActionListener(e -> {
            int curX = cup.getX();
            int curY = cup.getY();
            int dx = targetX - curX;
            int dy = targetY - curY;
            
            if (Math.abs(dx) <= 6 && Math.abs(dy) <= 6) {
                cup.setLocation(targetX, targetY); 
                slideInTimer.stop(); 
                
                lancerLesDes(); 
                
                isCoveringDice = true; 
                isAnimating = false; 
            } else {
                cup.setLocation(curX + (dx / 6), curY + (dy / 6));
            }
        });
        slideInTimer.start(); 
    }

    public void revealDiceSequence() {
        isAnimating = true; 
        isCoveringDice = false; 
        
        int homeX = cupStartingX;   
        int homeY = cupStartingY;

        Timer slideOutTimer = new Timer(20, null);
        slideOutTimer.addActionListener(e -> {
            int cx = cup.getX();
            int cy = cup.getY();
            int homedx = homeX - cx;
            int homedy = homeY - cy;
            
            if (Math.abs(homedx) <= 6 && Math.abs(homedy) <= 6) {
                cup.setLocation(homeX, homeY);
                slideOutTimer.stop();
                
                isAnimating = false; 
            } else {
                cup.setLocation(cx + (homedx / 6), cy + (homedy / 6));
            }
        });
        slideOutTimer.start();
    }

    public int lancerLesDes() {
        int dice1 = (int)(Math.random() * 6) + 1;
        int dice2 = (int)(Math.random() * 6) + 1;
        int total = dice1 + dice2;

        dice1Label.setText(String.valueOf(dice1));
        dice2Label.setText(String.valueOf(dice2));

        jTextArea1.append("\n" + player1Name + " a lancé un " + dice1 + " et un " + dice2 + " (Total: " + total + ")");

        return total;
    }

    public void animateMovement(int moveAmount) {
        int target = playerPos + moveAmount;

        if(target > 30){
            target = 30;
        }

        final int finalTarget = target;
        Timer timer = new Timer(300, null);

        timer.addActionListener(e -> {
            if(playerPos >= finalTarget){
                timer.stop();
                return;
            }

            playerPos++;
            movePlayerToSquare(player1PawnLabel, playerPos);
        });

        timer.start();
    }

    public void movePlayerToSquare(JLabel playerLabel, int squareNumber){
        JLabel square = squares[getIndexFromSquareNumber(squareNumber)];
        Rectangle bounds = square.getBounds();

        int x = bounds.x + (bounds.width - pawnSizeX) / 2;
        int y = bounds.y + (bounds.height - pawnSizeY) / 2;

        Point globalPoint = SwingUtilities.convertPoint(boardPanel, x, y, this.getLayeredPane());
        
        if(playerLabel == player1PawnLabel){
            playerLabel.setBounds(
                globalPoint.x,
                globalPoint.y,
                pawnSizeX,
                pawnSizeY
            );
        }
        
        else {
            playerLabel.setBounds(
                globalPoint.x - 20,
                globalPoint.y + 20,
                pawnSizeX,
                pawnSizeY
            );
            
        }
        


        this.getLayeredPane().moveToFront(playerLabel);
        this.repaint();
    }

   
    
    //Elouan
    private void askPlayerNames(){
        player1Name = JOptionPane.showInputDialog(this, "Nom du Joueur 1 :");
        player2Name = JOptionPane.showInputDialog(this, "Nom du Joueur 2 :");

        if(player1Name == null || player1Name.isBlank()){
            player1Name = "Joueur 1";
        }
        if(player2Name == null || player2Name.isBlank()){
            player2Name = "Joueur 2";
        }
    }

    private int getIndexFromSquareNumber(int squareNumber){
        int totalCols = 5;
        int n = squareNumber - 1;
        int row = n / totalCols;
        int col = n % totalCols;
        
        if(row % 2 != 0){
            col = (totalCols - 1) - col;
        }
        
        return (row * totalCols) + col;
    }

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(!isAnimating && !isCoveringDice){
           lancerLesDes();
        }
    }                                        

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new frame3().setVisible(true));
    }
    
    private void createBoard(){
        for(int i = 0; i < 30; i++){
            JLabel square = new JLabel();
            square.setHorizontalAlignment(JLabel.CENTER);
            square.setPreferredSize(new Dimension(50,50));
            square.setMinimumSize(new Dimension(50,50));
            square.setMaximumSize(new Dimension(50,50));

            squares[i] = square;
            boardPanel.add(square);
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }
        
    private void initBoardNumber(){
        for(int i = 0; i < squares.length; i++){
            squares[i].setText(String.valueOf(getIndexFromSquareNumber(i+1) + 1));
            squares[i].setForeground(Color.BLACK);
        }
    }
    
    private void setLabelIcon(JLabel label, String imagePath, int width, int height){
        URL resource = getClass().getResource(imagePath);
        ImageIcon icon = new ImageIcon(resource);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
        label.setPreferredSize(new Dimension(width, height));
        label.setSize(new Dimension(width, height));
    }
    
    private void setLabelIcon(JLabel label, String imagePath){
        setLabelIcon(label, imagePath, 60, 60);
    }    
    
    private void setSquareImage(int squareNumber, String imagePath){
        JLabel label = squares[getIndexFromSquareNumber(squareNumber)];
        setLabelIcon(label, imagePath);
        
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setVerticalAlignment(JLabel.CENTER);

        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setVerticalTextPosition(JLabel.CENTER);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerPanel = new javax.swing.JLayeredPane();
        topLayeredPanel = new javax.swing.JLayeredPane();
        topPanel = new javax.swing.JPanel();
        player1NameLabel = new javax.swing.JLabel();
        player2NameLabel = new javax.swing.JLabel();
        player1Bar = new javax.swing.JProgressBar();
        player2Bar = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        dice1Label = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dice2Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        boardPanel = new javax.swing.JPanel() {
            private java.awt.Image bg = new javax.swing.ImageIcon(
                getClass().getResource("ocean3.jpg")
            ).getImage();
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout(playerPanel);
        playerPanel.setLayout(playerPanelLayout);
        playerPanelLayout.setHorizontalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 699, Short.MAX_VALUE)
        );
        playerPanelLayout.setVerticalGroup(
            playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        player1NameLabel.setText("Joueur 1");

        player2NameLabel.setText("Joueur 2");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setMaximumSize(new java.awt.Dimension(50, 50));
        jPanel3.setMinimumSize(new java.awt.Dimension(50, 50));

        dice1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dice1Label.setMaximumSize(new java.awt.Dimension(50, 50));
        dice1Label.setMinimumSize(new java.awt.Dimension(50, 50));
        dice1Label.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dice1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dice1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMaximumSize(new java.awt.Dimension(50, 50));
        jPanel4.setMinimumSize(new java.awt.Dimension(50, 50));

        dice2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dice2Label.setMaximumSize(new java.awt.Dimension(50, 50));
        dice2Label.setMinimumSize(new java.awt.Dimension(50, 50));
        dice2Label.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dice2Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dice2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player1NameLabel)
                    .addComponent(player1Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player2NameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(player2Bar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player1NameLabel)
                            .addComponent(player2NameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(player1Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(player2Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        topLayeredPanel.setLayer(topPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout topLayeredPanelLayout = new javax.swing.GroupLayout(topLayeredPanel);
        topLayeredPanel.setLayout(topLayeredPanelLayout);
        topLayeredPanelLayout.setHorizontalGroup(
            topLayeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayeredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        topLayeredPanelLayout.setVerticalGroup(
            topLayeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topLayeredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("qsdqsf\nqsf\nqsf\nqsf\nazrzarazr\nqsf\nqsf\ngezezrazzz\nar\nzra\naz\nrzarazrzarzarr");
        jScrollPane1.setViewportView(jTextArea1);

        boardPanel.setLayout(new java.awt.GridLayout(6, 5));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addComponent(playerPanel)
                    .addComponent(topLayeredPanel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addGap(9, 9, 9)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topLayeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(121, 121, 121)
                    .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                    .addGap(122, 122, 122)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JLabel dice1Label;
    private javax.swing.JLabel dice2Label;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JProgressBar player1Bar;
    private javax.swing.JLabel player1NameLabel;
    private javax.swing.JProgressBar player2Bar;
    private javax.swing.JLabel player2NameLabel;
    private javax.swing.JLayeredPane playerPanel;
    private javax.swing.JLayeredPane topLayeredPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
