package game;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class TitleScreen extends JPanel {
    private JButton startButton;
    private JButton quitButton;
    private Font customFont;
    private Image backgroundImage;
    private Clip backgroundMusic;


    public TitleScreen(JFrame mainFrame) {
        loadCustomFont();
        loadBackgroundImage();
        loadBackgroundMusic();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);

        JLabel titleLabel = new JLabel("The Last Soldier ");
        titleLabel.setFont(customFont.deriveFont(100f)); 
        titleLabel.setForeground(Color.RED); 
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        add(titleLabel, gbc);

        startButton = new JButton("Start Game");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(startButton, gbc);

        quitButton = new JButton("Quit");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(quitButton, gbc);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBackgroundMusic();
                Main.showGameScreen();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setOpaque(false);
        titleLabel.setOpaque(false);
        startButton.setOpaque(false);
        quitButton.setOpaque(false);
        playBackgroundMusic();
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

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/assets/images/TitleScreen/titleScreenBackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load background image.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    private void loadBackgroundMusic() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("src/assets/sounds/TitleScreen/TitleMusic.wav"));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Failed to load background music.");
        }
    }
    public void playBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
        }
    }
    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
}