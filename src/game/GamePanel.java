package game;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public abstract class  GamePanel extends JPanel implements Runnable, KeyListener {


    boolean UP_PRESSED = false;
    boolean DN_PRESSED = false;
    boolean LT_PRESSED = false;
    boolean RT_PRESSED = false;
    boolean ZZ_PRESSED = false;
    boolean SB_PRESSED = false;

    public GamePanel() {
        init();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void init(){
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        Thread t = new Thread(this);
        t.start();
    }
    public void run() {
        System.out.println("Game is running");
        boolean running = true;
        
            while (running) {
                updateGame();
                repaint();
                try {
                    Thread.sleep(16); // Approximately 60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    
        public void startGameLoop() {
            Thread gameThread = new Thread(this);
            gameThread.start();
        }

    public abstract void updateGame();

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
        }
        if (e.getKeyCode() == e.VK_RIGHT) {
            RT_PRESSED = false;
        }
        if(e.getKeyCode() == e.VK_Z) {
            ZZ_PRESSED = false;
            onAttackStop();
        }
        if(e.getKeyCode() == e.VK_SPACE){
            SB_PRESSED = false;
        }        
    }
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    public abstract void onAttackStart();
    public abstract void onAttackStop();
}

