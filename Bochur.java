import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Bochur extends Sprite {

    private int dx;
    private int dy;
    private ArrayList<Coin> coins;

    public Bochur(int x, int y) {
        super(x, y);

        initBochur();
    }

    private void initBochur() {
        
        coins = new ArrayList<Coin>();
        loadImage("MinyanMan_RightTrans.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;

        if (x < 1) {
            x = 2;
        }

        if (y < 35) {
            y = 35;
        }
        
        if (x > 760) {
            x = 760;
        }

        if (y > 600) {
            y = 600;
        }
    }

    public ArrayList<Coin>getCoins() {
        return coins;
    }
    
    public void give() {
        if(coins.size() < 5) {
            coins.add(new Coin(x + width, y + height / 2));
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -3;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 3;
        }
        
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        
        if (key == KeyEvent.VK_SPACE) {
            give();
        }
    }
}