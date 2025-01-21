package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PauseMenu extends JPanel {
    private JButton resumeButton;
    private JButton helpButton;
    private JButton mainMenuButton;
    private Font customFont;


    public PauseMenu(GamePanel gamePanel) {
        loadCustomFont();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel pauseLabel = new JLabel("Paused ");
        pauseLabel.setFont(customFont.deriveFont(100f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(pauseLabel, gbc);

        resumeButton = new JButton("Resume");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(resumeButton, gbc);

        helpButton = new JButton("Help");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(helpButton, gbc);

        mainMenuButton = new JButton("Back to Main Menu");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(mainMenuButton, gbc);

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.resumeGame();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String helpMessage = "            WELCOME TO HELL SOLDIER \n"
                                   + "-----------------------------------\n"

                                   + "Press 'LEFT'     to move.\n"
                                   + "Press 'Z'        to attack.\n"
                                   + "Press 'R'        to reload.\n"
                                   + "Press 'P'        to pause.\n"
                                   + "Press 'SPACEBAR' to jump up to 2 times.";
                JOptionPane.showMessageDialog(gamePanel, helpMessage);
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.backToMainMenu();
            }
        });
        setOpaque(false); 
    }

    private void loadCustomFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/bloodlustacadital.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            System.out.println("Custom font loaded successfully.");
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); 
            System.out.println("Failed to load custom font. Using default font.");
        }
    }
}