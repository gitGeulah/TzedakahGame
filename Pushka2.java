public class Pushka2 extends Pushka {
    
    public Pushka2(int x, int y) {
        super(x, y);
    }
    
    public void move() {
        super.move();
        x -= 8;
    }
}