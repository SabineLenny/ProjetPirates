package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities; 
import javax.swing.SwingConstants; 

import Controleurs.ControlDeplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import Controleurs.ControlVerifierPoison;
import Controleurs.ControlVerifierVie;
import Entite.Plateau;
import Entite.TypeCase;


/**
 * @author DWR4418A
 */
public class GameFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameFrame.class.getName());

    private BoardSquare[] squares = new BoardSquare[30];
    
    private String player1Name;
    private String player2Name;

    private PlayerPawn pawnPlayer1;
    private PlayerPawn pawnPlayer2;
    private boolean isPlayer1Turn = true;
    
    // TODO : removed / replaced
    private int player1Pos = 1;
    private int player2Pos = 1;
    
    // --- DICE ---
    private final DiceManager diceManager;
    
    // --- CUP ---
    private final DiceCup cup;
    private final int cupStartingX = 400;
    private final int cupStartingY = 0;  
    
    // --- PAWN ---
    private final int pawnSizeX = 80;
    private final int pawnSizeY = 50;
    
    //Lenny
    static final ControlJeuPirate CONTROL_JEU_PIRATE = new ControlJeuPirate();
    static final ControlVerifierVie CONTROL_VIE = new ControlVerifierVie();
    static final ControlVerifierPoison CONTROL_POISON = new ControlVerifierPoison();
    static final ControlPlateau CONTROL_PLATEAU = new ControlPlateau();
    static final ControlDeplacer CONTROL_DEPLACER = new ControlDeplacer();
    static final ControlFinJeu CONTROL_FIN = new ControlFinJeu();
    
    
    public GameFrame() {

        askPlayerNames();

        initComponents();
        
        setResizable(false); 
        
        diceManager = new DiceManager(dice1Label, dice2Label);

        // Elouan
        player1NameLabel.setText(player1Name);
        player2NameLabel.setText(player2Name);
        //
        createBoard();
        //Lenny
        CONTROL_JEU_PIRATE.instancierJeu(player1Name,player2Name);  
        Plateau Board =CONTROL_JEU_PIRATE.getPlateau();
        setSquareSpecialType(CONTROL_PLATEAU.positionCase(Board,TypeCase.BOMBE), BoardSquare.SquareType.BOMB);
        setSquareSpecialType(CONTROL_PLATEAU.positionCase(Board,TypeCase.ECHANGE), BoardSquare.SquareType.EXCHANGE);
        setSquareSpecialType(CONTROL_PLATEAU.positionCase(Board,TypeCase.SOIN), BoardSquare.SquareType.HEAL);
        setSquareSpecialType(CONTROL_PLATEAU.positionCase(Board,TypeCase.EMPOISONNEMENT), BoardSquare.SquareType.POISON);
        //

        pawnPlayer1 = new PlayerPawn(this, "pirateship.png", "pirateshipleft.png", 50, 50);
        pawnPlayer2 = new PlayerPawn(this, "pirateship2.png", "pirateship2left.png", 75, 75);
        
        SwingUtilities.invokeLater(() -> {
            movePlayerToSquare(pawnPlayer1, player1Pos);
            movePlayerToSquare(pawnPlayer2, player2Pos);
        });
        

        cup = new DiceCup(this, cupStartingX, cupStartingY);
        this.getLayeredPane().add(cup, JLayeredPane.DRAG_LAYER);
    }
    
    private void createBoard() {
        for(int i = 0; i < 30; i++) {
            int displayNum = getIndexFromSquareNumber(i + 1) + 1;
            
            BoardSquare square = new BoardSquare(this, displayNum);
            
            squares[i] = square;
            boardPanel.add(square);
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }
    
    private void setSquareSpecialType(int squareNumber, BoardSquare.SquareType squareType){
        squares[getIndexFromSquareNumber(squareNumber)].setSpecialType(squareType);
    }



    // to delete
    public int lancerLesDes() {
        int dice1 = (int)(Math.random() * 6) + 1;
        int dice2 = (int)(Math.random() * 6) + 1;
        int total = dice1 + dice2;

          diceManager.showResult(dice1, dice2);

        jTextArea1.append("\n" + player1Name + " a lancé un " + dice1 + " et un " + dice2 + " (Total: " + total + ")");

        return total;
    }
    
    public void playTurn(){
        int roll = lancerLesDes();
        
        PlayerPawn activePawn = isPlayer1Turn ? pawnPlayer1 : pawnPlayer2;
        
        animateMovement(activePawn, roll);
        
        isPlayer1Turn = !isPlayer1Turn;
        
    }
    
    //Elouan
    public void animateMovement(PlayerPawn playerPawn, int moveAmount) {
        
        cup.setLocked(true);
        
        int startPos;
        
        if(playerPawn == pawnPlayer1) 
            startPos = player1Pos;
        else 
            startPos = player2Pos;
        
        final int[] visualPos = { startPos };
        
        int target = startPos + moveAmount;

        if(target > 30){
            target = 30;
        }

        final int finalTarget = target;
        
        
        if(playerPawn == pawnPlayer1) 
            player1Pos = finalTarget;
        else 
            player2Pos = finalTarget;
        
        Timer timer = new Timer(300, null);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(visualPos[0] >= finalTarget){
                    timer.stop();
                  
                    
                    cup.setLocked(false);
                    
                    
                    return;
                }
                
                visualPos[0]++;
                movePlayerToSquare(playerPawn, visualPos[0]);
            }
        });

        timer.start();
    }

    public void movePlayerToSquare(PlayerPawn playerPawn, int squareNumber){
        JLabel square = squares[getIndexFromSquareNumber(squareNumber)];
        Rectangle bounds = square.getBounds();

        int x;
        int y;
        
        if(playerPawn == pawnPlayer1){           
            x = bounds.x + (bounds.width - pawnSizeX) / 2;
            y = bounds.y + (bounds.height - pawnSizeY) / 2;     
        }
        
        else {
            x = (bounds.x + (bounds.width - pawnSizeX) / 2) - 20;
            y = (bounds.y + (bounds.height - pawnSizeY) / 2) + 20;
        }
        
        int row = (squareNumber - 1) / 5; 
        
        Point globalPoint = SwingUtilities.convertPoint(boardPanel, x, y, this.getLayeredPane());
   
        
        if (row % 2 != 0) {
            playerPawn.faceLeft();
        } 
        
        else {
            playerPawn.faceRight();
        }
        
        
        playerPawn.setBounds(
            globalPoint.x,
            globalPoint.y,
            pawnSizeX,
            pawnSizeY
        );

        this.getLayeredPane().moveToFront(playerPawn);
        this.repaint();
    }

      
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
    
    
    public JPanel getTopPanel(){
        return topPanel;
    }
    
    public JPanel getDice1Panel(){
        return dice1Panel;
    }
    
    public JPanel getDice2Panel(){
        return dice2Panel;
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

        java.awt.EventQueue.invokeLater(() -> new GameFrame().setVisible(true));
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
        dice1Panel = new javax.swing.JPanel();
        dice1Label = new javax.swing.JLabel();
        dice2Panel = new javax.swing.JPanel();
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

        player1Bar.setMaximum(5);
        player1Bar.setValue(5);

        player2Bar.setMaximum(5);
        player2Bar.setValue(5);

        dice1Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dice1Panel.setMaximumSize(new java.awt.Dimension(50, 50));
        dice1Panel.setMinimumSize(new java.awt.Dimension(50, 50));

        dice1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dice1Label.setMaximumSize(new java.awt.Dimension(50, 50));
        dice1Label.setMinimumSize(new java.awt.Dimension(50, 50));
        dice1Label.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout dice1PanelLayout = new javax.swing.GroupLayout(dice1Panel);
        dice1Panel.setLayout(dice1PanelLayout);
        dice1PanelLayout.setHorizontalGroup(
            dice1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dice1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dice1PanelLayout.setVerticalGroup(
            dice1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dice1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dice2Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dice2Panel.setMaximumSize(new java.awt.Dimension(50, 50));
        dice2Panel.setMinimumSize(new java.awt.Dimension(50, 50));

        dice2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dice2Label.setMaximumSize(new java.awt.Dimension(50, 50));
        dice2Label.setMinimumSize(new java.awt.Dimension(50, 50));
        dice2Label.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout dice2PanelLayout = new javax.swing.GroupLayout(dice2Panel);
        dice2Panel.setLayout(dice2PanelLayout);
        dice2PanelLayout.setHorizontalGroup(
            dice2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(dice2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dice2Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE))
        );
        dice2PanelLayout.setVerticalGroup(
            dice2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(dice2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(dice2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dice1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(dice1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dice2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
    private javax.swing.JPanel dice1Panel;
    private javax.swing.JLabel dice2Label;
    private javax.swing.JPanel dice2Panel;
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
