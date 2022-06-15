public class Pushka3 extends Pushka {
    
    private int yMovement = 5;
    
    public Pushka3(int x, int y) {
        super(x, y);
    }
    
    public void move() {
        super.move();
        
        x -= 5;
        y += yMovement;
        
        if (y == 50) {
           yMovement = -yMovement;             
        } else if (y == 670) {
            yMovement = -yMovement;
        }
    }
}