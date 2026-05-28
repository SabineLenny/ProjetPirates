package IHM;

import Boundaries.BoundarieIHM;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.Timer;
import javax.swing.SwingUtilities; 

/**
 * @author DWR4418A
 */
public class GameFrame extends javax.swing.JFrame {
    
    private final BoundarieIHM boundary = new BoundarieIHM();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameFrame.class.getName());

    private BoardSquare[] squares = new BoardSquare[30];
    
    private String player1Name;
    private int player1ID;
    private String player2Name;
    private int player2ID;

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
    
    
    public GameFrame() {

        askPlayerNames();

        initComponents();
        
        jTextArea1.append(boundary.affichagePlateau() + "\n\n");
        
        setResizable(false); 
        
        diceManager = new DiceManager(dice1Label, dice2Label);

        // Elouan
        player1NameLabel.setText(player1Name);
        player2NameLabel.setText(player2Name);
        //
        PSNJoueur1.setVisible(false);
        PSNJoueur2.setVisible(false);
        
        createBoard();
        
        int positionBombe = boundary.getPositionCaseAvecString("bombe");
        setSquareSpecialType(positionBombe, BoardSquare.SquareType.BOMB);
        
        int positionEchange = boundary.getPositionCaseAvecString("echange");
        setSquareSpecialType(positionEchange, BoardSquare.SquareType.EXCHANGE);
        
        
        int positionSoin = boundary.getPositionCaseAvecString("soin");
        setSquareSpecialType(positionSoin, BoardSquare.SquareType.HEAL);
        
        int positionPoison = boundary.getPositionCaseAvecString("empoisonnement");
        setSquareSpecialType(positionPoison, BoardSquare.SquareType.POISON);


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

    //Lenny
    public void pVIHM(int Changement,int Joueur){

        if(Joueur==0){
             player1Bar.setValue(player1Bar.getValue()+ Changement);
        }
        else {
           player2Bar.setValue(player2Bar.getValue()+ Changement);
        }
    }
    
    public void poison(int Joueur){
        if(Joueur==0){
            player1NameLabel.setForeground(Color.MAGENTA);
            PSNJoueur1.setVisible(true);
            
                    
        }
        else{
            player2NameLabel.setForeground(Color.MAGENTA);
            PSNJoueur2.setVisible(true);
            
        }   
    }
    public void poisonHeal(int Joueur){
        if(Joueur==0){
            player1NameLabel.setForeground(Color.BLACK);
            PSNJoueur1.setVisible(false);          
        }
        else{
            player2NameLabel.setForeground(Color.BLACK);
            PSNJoueur2.setVisible(false);
        } 
    }
    //
    
    //Ulysse
    public void playTurn(){
        
        PlayerPawn activePawn;
        int pirateCourant;
        String nomPirateCourant;
        
        if (isPlayer1Turn) {
            activePawn = pawnPlayer1;
            pirateCourant = 0;
            nomPirateCourant = player1Name;
        } else {
            activePawn = pawnPlayer2;
            pirateCourant = 1;
            nomPirateCourant = player2Name;
        }

        jTextArea1.append("\n\n\nAu tour de " + nomPirateCourant+"\n");
        
        String verifierPoison = boundary.verificationPoison(pirateCourant);
        if (boundary.estEmpoisonne(pirateCourant)){
            pVIHM(-1,pirateCourant);
            
        } else {
            poisonHeal(pirateCourant);
        }
        jTextArea1.append(verifierPoison);
        
        int[] lancer = boundary.lancerDes();
        int deplacement = boundary.deplacementPirate(pirateCourant,lancer);
        jTextArea1.append(boundary.deplacementPirateAffichage(pirateCourant, deplacement));
        diceManager.showResult(lancer[0], lancer[1]);
        
        String effetCase;
        effetCase = boundary.activerCase(pirateCourant, (pirateCourant+1)%2);
        animateMovement(activePawn, deplacement);
        if (effetCase.contains("soigne") && boundary.getPirateVie(pirateCourant) >= 5) {
            pVIHM(1,pirateCourant);
            poisonHeal(pirateCourant);
        }
        if (effetCase.contains("BOMB")) {
            JOptionPane.showMessageDialog(this,"BOOM");
            pVIHM(-3,pirateCourant);
        } 
        if (effetCase.contains("empoisonne")){
            poison(pirateCourant);
        }
       
        
        if (effetCase.contains("positions")) {
            int pos1Temporaire = player1Pos;
            int pos2Temporaire = player2Pos;
            player1Pos = pos2Temporaire;
            movePlayerToSquare(pawnPlayer1, pos2Temporaire);
            player2Pos = pos1Temporaire;
            movePlayerToSquare(pawnPlayer2, pos1Temporaire);
        }
            
        jTextArea1.append(effetCase);
        
        if (!boundary.finJeu(pirateCourant)) {
            jTextArea1.append("Fin de jeu, victoire de " + nomPirateCourant + "\n");
            JOptionPane.showMessageDialog(this,nomPirateCourant + "est arrivee");
            this.dispose();
        }
        if (!boundary.verificationVie(pirateCourant)) {
            jTextArea1.append(boundary.affichageVie(pirateCourant));
            JOptionPane.showMessageDialog(this,"Victoire de " + nomPirateCourant + " par perte de vie");
            this.dispose();
        }
        
        isPlayer1Turn = !isPlayer1Turn;
        
    }
    //Ulysse
    
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
    
    // Vérifier si le joueur dépasse 30
    boolean goingBackward = false;
    if(target > 30){
        goingBackward = true;
        target = 30 - (target - 30);
    }
    
    final int finalTarget = target;
    final boolean isGoingBackward = goingBackward;
    
    // Mettre à jour la position du joueur
    if(playerPawn == pawnPlayer1) 
        player1Pos = finalTarget;
    else 
        player2Pos = finalTarget;
    
    // Pour gérer les 2 phases du rebond
    final int[] phase = { 0 }; // 0 = avancer vers 30, 1 = reculer
    
    Timer timer = new Timer(300, null);
 
    timer.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(isGoingBackward) {
                // ===== ANIMATION AVEC REBOND =====
                
                // PHASE 0: Avancer jusqu'à 30 (inclus)
                if(phase[0] == 0) {
                    if(visualPos[0] < 30) {
                        visualPos[0]++;
                        movePlayerToSquare(playerPawn, visualPos[0]);
                    }
                    // Arrivé à 30, passer à phase 1 (reculer)
                    else if(visualPos[0] == 30) {
                        phase[0] = 1;
                        // Rester à 30 pour au moins 1 frame avant de reculer
                    }
                }
                // PHASE 1: Reculer de 30 vers finalTarget
                else if(phase[0] == 1) {
                    if(visualPos[0] > finalTarget) {
                        visualPos[0]--;
                        movePlayerToSquare(playerPawn, visualPos[0]);
                    }
                    // Arrivé à finalTarget
                    else {
                        timer.stop();
                        cup.setLocked(false);
                        return;
                    }
                }
            }
            else {
                // ===== ANIMATION NORMALE (SANS REBOND) =====
                if(visualPos[0] >= finalTarget){
                    timer.stop();
                    cup.setLocked(false);
                    return;
                }
                
                visualPos[0]++;
                movePlayerToSquare(playerPawn, visualPos[0]);
            }
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
            player1ID = 0;
        }
        if(player2Name == null || player2Name.isBlank()){
            player2Name = "Joueur 2";
            player1ID = 1;
        }
        
        boundary.instancierJeu(player1Name, player2Name);
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
        PSNJoueur2 = new javax.swing.JButton();
        PSNJoueur1 = new javax.swing.JButton();
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
        player1Bar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                player1BarStateChanged(evt);
            }
        });

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

        PSNJoueur2.setBackground(new java.awt.Color(183, 0, 255));
        PSNJoueur2.setForeground(new java.awt.Color(255, 255, 255));
        PSNJoueur2.setText("PSN");
        PSNJoueur2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PSNJoueur2ActionPerformed(evt);
            }
        });

        PSNJoueur1.setBackground(new java.awt.Color(183, 0, 255));
        PSNJoueur1.setForeground(new java.awt.Color(255, 255, 255));
        PSNJoueur1.setText("PSN");
        PSNJoueur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PSNJoueur1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(player1NameLabel)
                    .addComponent(player1Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PSNJoueur1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dice2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dice1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(player2NameLabel)
                    .addComponent(player2Bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PSNJoueur2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(player2Bar, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PSNJoueur2)
                            .addComponent(PSNJoueur1)))
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

    private void player1BarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_player1BarStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_player1BarStateChanged

    private void PSNJoueur2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PSNJoueur2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSNJoueur2ActionPerformed

    private void PSNJoueur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PSNJoueur1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSNJoueur1ActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton PSNJoueur1;
    private javax.swing.JButton PSNJoueur2;
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
