public abstract class Pushka extends Sprite {

    protected final int INITIAL_X = 800;
    protected int filled = 0;

    public Pushka(int x, int y) {
        super(x, y);
        initPushka();
    }

    public void initPushka() {

        loadImage("Tzedakabox_Trans.png");
        getImageDimensions();
    }

    public void fill() {
        filled++;
        if(filled >= 5) {
            setVisible(false);
        }
    }
    
    public int returnFilled() {
        return filled;
    }
    
    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

    }
}