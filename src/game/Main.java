package game;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("The Last Soldier");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setSize(800, 600); //set the dimensions of the window
        
        Game Game = new Game(); // Game handles rendering and updates
        window.add(Game);
        
        window.setLocationRelativeTo(null); // Center the window
        window.setVisible(true);
        
        Game.startGameLoop(); // Start the game loop
    }
}
