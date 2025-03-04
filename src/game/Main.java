package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class Main {

    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static Game game;
    private static TitleScreen titleScreen;
    public static void main(String[] args) {
        JFrame window = new JFrame("The Last Soldier");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(800, 600); 

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        titleScreen = new TitleScreen(window);
        game = new Game();

        mainPanel.add(titleScreen, "TitleScreen");
        mainPanel.add(game, "GameScreen");

        window.add(mainPanel);
        
        window.setLocationRelativeTo(null); 
        window.setVisible(true);
        
        cardLayout.show(mainPanel, "TitleScreen");
    }

    public static void showGameScreen() {
        game.resetGame();
        cardLayout.show(mainPanel, "GameScreen");
        game.requestFocusInWindow();
        game.startGameLoop();
        // game.currentLevel.playBackgroundMusic("src/assets/sounds/Rob Dougan - Clubbed to Death (Kurayamino Variation) [ ezmp3.cc ].wav");
    }

    public static void showTitleScreen() {
        cardLayout.show(mainPanel, "TitleScreen");
        // titleScreen.playBackgroundMusic(); 
    }

    
}
