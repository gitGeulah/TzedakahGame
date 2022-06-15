import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Bochur bochur;
    private ArrayList<Pushka> pushkas;
    private boolean ingame;
    private final int IBOCHUR_X = 40;
    private final int IBOCHUR_Y = 60;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 650;
    private final int DELAY = 40;
    private boolean timerStarted;
    private boolean gameEnded;
    private GameSounds gs = new GameSounds();

    private int level = 1;
    
    private final int[][] pos = {
        {700, 150},{700, 300},{700, 450},{900, 150},{800, 300},{1000, 450},
        {1300, 150},{1400, 300},{1500, 450},{1700, 150},{1900, 300},{200, 450}
    };
    
    private final int[][] pos2 = {
        {700, 150},{700, 300},{700, 450},{300, 100},{300, 250},{300, 400},
        {1200, 150},{1400, 300},{1000, 450},{2000, 100},{2400, 250},{1800, 400}
    }; 
    
    private final int[][] pos3 = {
        {700, 150},{700, 300},{700, 450},{900, 150},{800, 300},{1000, 450},
        {1200, 150},{1600, 300},{1300, 450},{1700, 150},{1900, 300},{2200, 450}
    };
    
    private int score = 0;
    private int timeLeft = 200;
    private int seconds = 20;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        Color c = new Color(49, 137, 166);
        //Color c = new Color(119, 71, 28);
        setBackground(c);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        bochur = new Bochur(IBOCHUR_X, IBOCHUR_Y);

        
        
        initPushkas();

        //timer = new Timer(DELAY, this);
        //timer.start();
    }
    
    protected void initTimer() {
    
        timer = new Timer(DELAY, this);
        timer.start();
        timerStarted = true;
        gs.Vayihi1();
        gs.mitzvaGoreres();
    }

    public boolean hasTimerStarted() {
        return timerStarted;
    }
    
    public boolean hasGameEnded() {
        return gameEnded;
    }
    
      protected void stopTimer() {    
             timer.stop();
    }
    
      protected void restartTimer() {    
             timer.start();
    }

    public void initPushkas() {
        pushkas = new ArrayList<Pushka>();

        for (int[] p : pos) {
            pushkas.add(new Pushka1(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            drawObjects(g);
        }
        else if (pushkas.size() > 0 && timeLeft > 0) {
            drawGameOver(g);
        }        
        else if (pushkas.size() == 0 && timeLeft > 0) {          
            drawGameWon(g);
        }        
        else {            
            drawTimeUp(g);
        }        
        

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (bochur.isVisible()) {
            g.drawImage(bochur.getImage(), bochur.getX(), bochur.getY(), this);
        }

        ArrayList<Coin> cs = bochur.getCoins();

        for (Coin c : cs) {
            if (c.isVisible()) {
                g.drawImage(c.getImage(), c.getX(), c.getY(), this);
            }
        }
        
        if(!(pushkas.size() == 0)) {
            for (Pushka p : pushkas) {
                if (p.isVisible()) {
                    g.drawImage(p.getImage(), p.getX(), p.getY(), this);
                }
            }
        }
        
        g.setColor(Color.WHITE);
        Font small = new Font("Verdana", Font.BOLD, 25);
        g.setFont(small);
            //if(!(pushkas.size() == 0)) {
            g.drawString("Pushkas left: " + pushkas.size()
            + "      Score: " + score +"    Time Left: " + seconds, 100, 25);
        //}

        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(0, 30, 820, 30);
        
    }

    private void drawGameOver(Graphics g) {

        String msg = " Boruch Hashem! You have your hands ";
        String msg2 = "full due to catching a pushka!";
        String msg3 = "Would you like to try to give Tzedaka again?";
        String msg4 = "Then just click Start";
        String msg5 = "MOSHIACH NOW!";
        Font small = new Font("Verdana", Font.BOLD, 25);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.yellow);
        g.setFont(small);
        
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2 - 70);
                
        g.drawString(msg2, (B_WIDTH - fm.stringWidth(msg2)) / 2,
                B_HEIGHT / 2 - 35);
                
        g.drawString(msg3, (B_WIDTH - fm.stringWidth(msg3)) / 2,
                B_HEIGHT / 2 + 35);
                
        g.drawString(msg4, (B_WIDTH - fm.stringWidth(msg4)) / 2,
                B_HEIGHT / 2 + 70);
                
        g.drawString(msg5, (B_WIDTH - fm.stringWidth(msg5)) / 2,
                B_HEIGHT / 2 + 135);
                
        gs.stopVayihi1();        
        gameEnded = true;        
    }
    
    private void drawTimeUp(Graphics g) {

        String msg = " Boruch Hashem, your time is up. ";
        String msg2 = "Tzedaka Brings the Geulah Closer!!";
        String msg3 = "Would you like to try to give Tzedaka again?";
        String msg4 = "Then just click Start";
        String msg5 = "MOSHIACH NOW!";
        Font small = new Font("Verdana", Font.BOLD, 25);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.yellow);
        g.setFont(small);
        
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2 - 70);
                
        g.drawString(msg2, (B_WIDTH - fm.stringWidth(msg2)) / 2,
                B_HEIGHT / 2 - 35);
                
        g.drawString(msg3, (B_WIDTH - fm.stringWidth(msg3)) / 2,
                B_HEIGHT / 2 + 35);
                
        g.drawString(msg4, (B_WIDTH - fm.stringWidth(msg4)) / 2,
                B_HEIGHT / 2 + 70);
                
        g.drawString(msg5, (B_WIDTH - fm.stringWidth(msg5)) / 2,
                B_HEIGHT / 2 + 135);
                
        gs.stopVayihi1();         
        gameEnded = true;          
    }
    
        private void drawGameWon(Graphics g) {

        String msg = "Boruch Hashem!";
        String msg2 = "You have put all the";
        String msg3 = "Tzedakah where it belongs!";
        String msg4 = "Points: Time Left - " + timeLeft/10 + " + " + "Score - " + score + " = " + ((timeLeft/10) + score);
        String msg5 = "MOSHIACH NOW!!";
        
        Font small = new Font("Verdana", Font.BOLD, 25);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.yellow);
        g.setFont(small);
        
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2 - 70);
                
        g.drawString(msg2, (B_WIDTH - fm.stringWidth(msg2)) / 2,
                B_HEIGHT / 2 - 35);
                
        g.drawString(msg3, (B_WIDTH - fm.stringWidth(msg3)) / 2,
                B_HEIGHT / 2);
                
        g.drawString(msg4, (B_WIDTH - fm.stringWidth(msg4)) / 2,
                B_HEIGHT / 2 + 70);                        
                
        g.drawString(msg5, (B_WIDTH - fm.stringWidth(msg5)) / 2,
                B_HEIGHT / 2 + 135);
                
        gs.stopVayihi1();         
        gameEnded = true;  
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateBochur();
        updateCoins();
        updatePushkas();
        
        checkCollisions();
        
        updateTime();

        repaint();          

    }
      
    public void updateTime() {
        timeLeft--;
        if (timeLeft == 0) {
            ingame = false;
        }
        
        if(timeLeft % 10 == 0) {
            seconds--;
        }
        
    }
    
    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateBochur() {

        if (bochur.isVisible()) {
            bochur.move();
        }
    }

    private void updateCoins() {

        ArrayList<Coin> cs = bochur.getCoins();

        for (int i = 0; i < cs.size(); i++) {

            Coin c = cs.get(i);

            if (c.isVisible()) {
                c.move();
            } else {
                cs.remove(i);
            }
        }
    }

    private void updatePushkas() {

        if (pushkas.isEmpty()) {
            
            switch(level) {
                
                case 1:
                for (int[] p : pos2) {
                    pushkas.add(new Pushka2(p[0], p[1]));
                }
                bochur.setX(IBOCHUR_X);
                bochur.setY(IBOCHUR_Y);
                gs.mitzvaGoreres();
                level = 2;
                break;
    
                case 2:                
                for (int[] p : pos3) {
                    pushkas.add(new Pushka3(p[0], p[1]));
                }
                bochur.setX(IBOCHUR_X);
                bochur.setY(IBOCHUR_Y);
                gs.mitzvaGoreres();
                level = 3;
                break;
                
                case 3:
                ingame = false;
                return;
            }
        }
        
        //if(!(pushkas.size() == 0)) {
            for (int i = 0; i < pushkas.size(); i++) {
    
                Pushka p = pushkas.get(i);
                if (p.isVisible()) {
                    p.move();
                } else {
                    pushkas.remove(i);
                }
            }
        //} 
    }

    public void checkCollisions() {

        Rectangle r3 = bochur.getBounds();

            //if(!(pushkas.size() == 0)) {
            for (Pushka pushka : pushkas) {
                Rectangle r2 = pushka.getBounds();
    
                if (r3.intersects(r2)) {
                    bochur.setVisible(false);
                    ingame = false;
                }
            }
        //} 

        ArrayList<Coin> cs = bochur.getCoins();

        for (Coin c : cs) {

            Rectangle r1 = c.getBounds();
            
            //if(!(pushkas.size() == 0)) {
                for (Pushka pushka : pushkas) {
    
                    Rectangle r2 = pushka.getBounds();
    
                    if (r1.intersects(r2)) {
                        gs.dropping();
                        score++;
                        timeLeft = timeLeft + 30;
                        seconds = seconds + 3;
                        c.setVisible(false);
                        pushka.fill();
                        if(pushka.returnFilled() > 5) {
                            score--;
                            timeLeft = timeLeft - 30;
                            seconds = seconds - 3;
                        }
                    }
                }
            //} 
            }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            bochur.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            bochur.keyPressed(e);
        }
    }
}