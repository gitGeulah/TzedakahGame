import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

public class MainMenuPic extends JPanel// implements ActionListener
{
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 650;
    private Image image;
    private URL url = getClass().getResource("MenuPic.png");
    
    public MainMenuPic () {
        initMMP();
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }

    public void initMMP() {
        image = Toolkit.getDefaultToolkit().getImage(url);
        repaint();
    }
    
    //    @Override
    //public void actionPerformed(ActionEvent e) {
    //        repaint();
    //}
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    
    
}
