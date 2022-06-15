public class Coin extends Sprite {

    private final int BOARD_WIDTH = 800;
    private final int COIN_SPEED = 5;

    public Coin(int x, int y) {
        super(x, y);

        initCoin();
    }
    
    private void initCoin() {
        
        loadImage("Tzedakacoin_trans.png");
        getImageDimensions();        
    }

    public void move() {
        
        x += COIN_SPEED;
        
        if (x > BOARD_WIDTH)
            vis = false;
    }
}