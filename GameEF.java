import java.awt.event.*;
import javax.swing.*;

public class GameEF extends JFrame
{
    
    private Board board;
    private static JMenuItem m;
    private MainMenuPic mmp;
    private int flag = 0;
    
    public GameEF()
    {
       
        makeFrame();
    } 

    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    // ---- Swing stuff to build the frame and all its components and menus ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
       
        //*******
       setTitle("Tzedakah RUSH!!!");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setJMenuBar(createMenuBar());
        
       mmp = new MainMenuPic();
       add(mmp);
       setResizable(false);    
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
            
       
       //Create a new board merely to check the hasTimerStarted method in the pause and continue
       //menu items
       board = new Board();
       
        /*
        //place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
        */
    }
    
    
    //creates Game menu and submenus
    public JMenuBar createMenuBar()
    {
        JMenu menu;//menu
      
        // create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // build the Game menu
        menu = new JMenu("Game");
        menuBar.add(menu);
        
        m = new JMenuItem("Start");
                      m.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) { 
           if (flag == 0) {
               remove(mmp);
               mmp = null;
               
               board = new Board();
               add(board);
               setResizable(false);    
               pack();
               setLocationRelativeTo(null);
               setVisible(true);
               
               flag = 1;
           }

           if(!(board.hasTimerStarted())) {
               board.requestFocusInWindow();
               board.initTimer();            
           }
           
           if(board.hasGameEnded()){
               remove(board);
               
               board = new Board();
               add(board);
               setResizable(false);    
               pack();
               setLocationRelativeTo(null);                
               setVisible(true);
               board.requestFocusInWindow();
               board.initTimer();                 
            }}
                           });
                           
        menu.add(m);
        
        m = new JMenuItem("Pause");
                      m.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) { 
               if(board.hasTimerStarted()) {
               board.stopTimer();
            }}
                           });
        menu.add(m);
        
        
         m = new JMenuItem("Continue");
                      m.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if(board.hasTimerStarted()) {
               board.restartTimer();
            }}
                           });
        menu.add(m);
        
        m = new JMenuItem("Exit");
                      m.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(m);
        
        return menuBar;
    }
        
    public static void main(String []args) {
        GameEF ef = new GameEF();
        ef.setVisible(true);
       }
}
