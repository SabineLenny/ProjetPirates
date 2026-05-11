/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
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

/**
 *
 * @author DWR4418A
 */
public class frame3 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frame3.class.getName());

    private JLabel[] squares = new JLabel[30];
    //Elouan
    private String player1Name;
    private String player2Name;
    
    JLabel playerPawn;
    //
   
    /**
     * 
     * Creates new form frame3
     */
    
        
    
    public frame3() {

        askPlayerNames();

        initComponents();
        //Elouan
        jLabel1.setText(player1Name);
        jLabel2.setText(player2Name);
        //
        
        setResizable(false);
        createBoard();

        for(int i = 1; i <= 30; i++){
            setSquareImage(i, "island.png");
        }

        initBoardNumber();

        setSquareImage(9, "bomb.png");
        setSquareImage(11, "exchange.png");
        setSquareImage(13, "heal.png");
        setSquareImage(15, "poison.png");

        playerPawn = new JLabel();
        playerPawn.setBounds(50, 50, 50, 50);

        playerPanel.add(playerPawn, JLayeredPane.POPUP_LAYER);

        setLabelIcon(playerPawn, "pirateship.png");

        initCup();
    }
    
    
    
    Point initialClick;

    
    private int totalDistance = 0;
    private int lastX, lastY;
    private final int SHAKE_THRESHOLD = 500; // Adjust this sensitivity
    
    
    private int number = 1; // remove this
    private void initCup(){
        JLabel cup = new JLabel();

        cup.setBounds(400, 10, 80, 80); // adjust to your UI
        topLayeredPanel.add(cup, JLayeredPane.DRAG_LAYER);
        setLabelIcon(cup, "dicecup.png", 100, 100);
        

        cup.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            public void mouseReleased(MouseEvent e) {
                // When user releases → roll dice
                
                if (totalDistance > 300) {
//                    moveEntityToSquare(playerPawn, number);
//                    number++;
                }
                totalDistance = 0; // Reset for next turn
                cup.setLocation(400, 10);
            }
        });

        cup.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
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
    
    
    //Elouan
    private void askPlayerNames(){

        player1Name = JOptionPane.showInputDialog(
                this,
                "Nom du Joueur 1 :"
        );

        player2Name = JOptionPane.showInputDialog(
                this,
                "Nom du Joueur 2 :"
        );

        // sécurité si utilisateur clique Annuler
        if(player1Name == null || player1Name.isBlank()){
            player1Name = "Joueur 1";
        }

        if(player2Name == null || player2Name.isBlank()){
            player2Name = "Joueur 2";
        }
    }

    
    public void moveEntityToSquare(
            JLabel entityLabel,
            int squareNumber
    ){

        JLabel square =
                squares[getIndexFromSquareNumber(squareNumber)];

        Rectangle bounds = square.getBounds();

        int pawnSize = 100;

        int x = bounds.x + (bounds.width - pawnSize) / 2;
        int y = bounds.y + (bounds.height - pawnSize) / 2;

        entityLabel.setBounds(
                x,
                y,
                pawnSize,
                pawnSize
        );

        playerPanel.moveToFront(entityLabel);
        playerPanel.repaint();
    }
    
//    public void animateMovement(
//            JLabel entityLabel,
//            PositionComponent position,
//            int moveAmount
//    ){
//
//        int start = position.getBoardPosition();
//        int target = start + moveAmount;
//
//        // limite max du plateau
//        if(target > 30){
//            target = 30;
//        }
//
//        final int finalTarget = target;
//
//        Timer timer = new Timer(300, null);
//
//        timer.addActionListener(e -> {
//
//            int current = position.getBoardPosition();
//
//            // arrivé à destination
//            if(current >= finalTarget){
//                timer.stop();
//                return;
//            }
//
//            // avance d'une case
//            current++;
//
//            position.setBoardPosition(current);
//
//            moveEntityToSquare(
//                    entityLabel,
//                    current
//            );
//
//        });
//
//        timer.start();
//    }
//    
    
    
    
    
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playerPanel = new javax.swing.JLayeredPane();
        topLayeredPanel = new javax.swing.JLayeredPane();
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        lblDie1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblDie2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        boardPanel = new javax.swing.JPanel() {
            private java.awt.Image bg = new javax.swing.ImageIcon(
                getClass().getResource("ocean2.jpg")
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
            .addGap(0, 543, Short.MAX_VALUE)
        );

        jLabel1.setText("Joueur 1");

        jLabel2.setText("Joueur 2");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDie1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDie1.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDie1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblDie1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDie2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDie2.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblDie2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblDie2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(playerPanel)
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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new frame3().setVisible(true));
    }
    
        private void createBoard(){
                    
            
        for(int i = 0; i < 30; i++){
            JLabel square = new JLabel();
            square.setHorizontalAlignment(JLabel.CENTER);
            square.setPreferredSize(new Dimension(50,50));
            square.setMinimumSize(new Dimension(50,50));
            square.setMaximumSize(new Dimension(50,50));
            //square.setBorder(javax.swing.BorderFactory.createLineBorder(Color.yellow));

            squares[i] = square;
            boardPanel.add(square);
            
        }
        
                
        boardPanel.revalidate();
        boardPanel.repaint();
    }
        
    private void initBoardNumber(){
        for(int i = 0; i < squares.length; i++){
            squares[i].setText(String.valueOf(getIndexFromSquareNumber(i+1) + 1));
        }
    }
    
    private void setLabelIcon(JLabel label, String imagePath, int width, int height){

        // DEBUG: check if the resource exists
        URL resource = getClass().getResource(imagePath);


        // Load the icon and scale
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
        // DEBUG: check if the resource exists
        //URL resource = getClass().getResource(imagePath);


        // Load the icon and scale
        //ImageIcon icon = new ImageIcon(resource);
        //Image img = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        //label.setIcon(new ImageIcon(img));
        
    // KEEP the text (number)

    // Center everything
    label.setHorizontalAlignment(JLabel.LEFT);
    label.setVerticalAlignment(JLabel.CENTER);

    // THIS is the key part 👇
    label.setHorizontalTextPosition(JLabel.LEFT);
    label.setVerticalTextPosition(JLabel.CENTER);

    // Make text visible on image
    label.setForeground(Color.WHITE);
    }
    
    
    public void animateDiceAndRoll(int de1, int de2) {
        // How fast the dice change faces (50 milliseconds)
        Timer rollTimer = new Timer(50, null);
        
        // We use an array of size 1 so we can modify the counter inside the timer
        int[] ticks = {0}; 
        int maxTicks = 15; // Total number of "spins" before stopping

        rollTimer.addActionListener(e -> {
            if (ticks[0] >= maxTicks) {
                rollTimer.stop();
                
                lblDie1.setText(String.valueOf(de1));
                lblDie2.setText(String.valueOf(de2));
            } else {
                int temp1 = (int)(Math.random() * 6) + 1;
                int temp2 = (int)(Math.random() * 6) + 1;
                
                lblDie1.setText(String.valueOf(temp1));
                lblDie2.setText(String.valueOf(temp2));
                
                ticks[0]++;
            }
        });

        rollTimer.start();
    }
    
    public void lancerLesDes() {
        int die1 = (int)(Math.random() * 6) + 1;
        int die2 = (int)(Math.random() * 6) + 1;
        int total = die1 + die2;

        // Set the FINAL numbers on the screen
        lblDie1.setText(String.valueOf(die1));
        lblDie2.setText(String.valueOf(die2));

        // Log the result
        jTextArea1.append("\n" + player1Name + " a lancé un " + total + " !");

        // Move the ship
//        animateMovement(total);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblDie1;
    private javax.swing.JLabel lblDie2;
    private javax.swing.JLayeredPane playerPanel;
    private javax.swing.JLayeredPane topLayeredPanel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
