package game;
import javax.swing.JPanel;

import entities.mainCharacter;

import java.awt.*;
import java.awt.event.*;
import engine.Camera;

public abstract class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {

    int mx = -1;
    int my = -1;
    boolean UP_PRESSED = false;
    boolean DN_PRESSED = false;
    boolean LT_PRESSED = false;
    boolean RT_PRESSED = false;
    boolean ZZ_PRESSED = false;
    boolean SB_PRESSED = false;
    boolean RR_PRESSED = false;
    boolean QQ_PRESSED = false;
    boolean N1_PRESSED = false;
    boolean N2_PRESSED = false;
    boolean N3_PRESSED = false;
    boolean PP_PRESSED = false;
    private boolean running;
    private boolean paused;
    private PauseMenu pauseMenu;
    public mainCharacter Zack;


    public GamePanel() {
        init();
        this.LT_PRESSED = false;
        this.RT_PRESSED = false;
        running = false;
        paused = false;
        pauseMenu = new PauseMenu(this);
        pauseMenu.setVisible(false);
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(pauseMenu);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (paused) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(0, 0, 0, 150)); 
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }

    public void init(){
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        addMouseListener(this);
        Thread t = new Thread(this);
        t.start();
    }
    public void run() {
        System.out.println("Game is running");
        running = true;
        
            while (running) {
                if (!paused) {
                    updateGame();
                }
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    
    public void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }



    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_UP) {
            UP_PRESSED = true;
        }
        if (e.getKeyCode() == e.VK_DOWN) {
            DN_PRESSED = true;
        }
        if (e.getKeyCode() == e.VK_LEFT) {
            LT_PRESSED = true;
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            RT_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_Z){
            ZZ_PRESSED = true;
            onAttackStart();
        }
        if(e.getKeyCode() == e.VK_SPACE){
            SB_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_R){
            RR_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_Q){
            QQ_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_1){
            N1_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_2){
            N2_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_3){
            N3_PRESSED = true;
        }
        if(e.getKeyCode() == e.VK_P){
            PP_PRESSED = true;
            togglePause();
        }

    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_UP) {
            UP_PRESSED = false;
        }
        if (e.getKeyCode() == e.VK_DOWN) {
            DN_PRESSED = false;
        }
        if (e.getKeyCode() == e.VK_LEFT) {
            LT_PRESSED = false;
            Zack.setVelocity(0, Zack.vy);
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            RT_PRESSED = false;
            Zack.setVelocity(0, Zack.vy);
        }
        if(e.getKeyCode() == e.VK_Z) {
            ZZ_PRESSED = false;
            onAttackStop();
        }
        if(e.getKeyCode() == e.VK_SPACE){
            SB_PRESSED = false;
        }
        if(e.getKeyCode() == e.VK_R){
            RR_PRESSED = false;
        }  
        if(e.getKeyCode() == e.VK_Q){
            QQ_PRESSED = false;
        }
        if(e.getKeyCode() == e.VK_1){
            N1_PRESSED = false;
        }
        if(e.getKeyCode() == e.VK_2){
            N2_PRESSED = false;
        }
        if(e.getKeyCode() == e.VK_3){
            N3_PRESSED = false;
        } 
        if(e.getKeyCode() == e.VK_P){
            PP_PRESSED = false;
        }     
        
    }
    

    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        System.out.println("Mouse Pressed at " + (mx + Camera.x) + ", " + (my + Camera.y));
    }
    

    public void togglePause() {
        paused = !paused;
        pauseMenu.setVisible(paused);
        if (paused) {
            System.out.println("Game paused");
            this.requestFocusInWindow();
        } else {
            System.out.println("Game resumed");
        }
    }
    
    public void resumeGame() {
        paused = false;
        pauseMenu.setVisible(false);
        System.out.println("Game resumed");
    }

    public void backToMainMenu() {
        resumeGame();
        Main.showTitleScreen();
        System.out.println("Back to main menu");
    }

    

    


    public abstract void updateGame();
    public abstract void onAttackStart();
    public abstract void onAttackStop();

    // Unused methods
    public void keyTyped(KeyEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

