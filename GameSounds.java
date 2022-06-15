import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


/* This class controls all sound effects*/
public class GameSounds{
        
        Clip mitzvaGoreres;
        Clip vayihi1;
        Clip drop;
        /* Keeps track of whether or not the eating sound is playing*/
    //    boolean stopped;
           
    
    /* Initialize audio files */ 
        public GameSounds(){
            URL url;
            AudioInputStream audioIn;
            
            try{
                        
                // MitzvaGoreres        
                url = this.getClass().getClassLoader().getResource("sounds/MitzvaGoreres.wav");
                audioIn = AudioSystem.getAudioInputStream(url);
                mitzvaGoreres = AudioSystem.getClip();
                mitzvaGoreres.open(audioIn);
                
                // Vayihi1        
                url = this.getClass().getClassLoader().getResource("sounds/Vayihi1.wav");
                audioIn = AudioSystem.getAudioInputStream(url);
                vayihi1 = AudioSystem.getClip();
                vayihi1.open(audioIn);
                
                // Drop        
                url = this.getClass().getClassLoader().getResource("sounds/Drop.wav");
                audioIn = AudioSystem.getAudioInputStream(url);
                drop = AudioSystem.getClip();
                drop.open(audioIn);
    
            }catch(Exception e){}
        }
                
    /* Play Mitzva Goreres sound */
    public void mitzvaGoreres(){
   
        mitzvaGoreres.setFramePosition(0);
        mitzvaGoreres.start();
    }
    
    /* Play Coin dropping sound */
    public void dropping(){
            do{
            drop.setFramePosition(0);
            drop.start();
        
        } while(!(drop.getMicrosecondPosition() < drop.getMicrosecondLength()));
             
    }
    
    /* Play Vayihi  sound */
    public void Vayihi1(){
    
        vayihi1.setFramePosition(0);
        vayihi1.start();
        vayihi1.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stopVayihi1 () {
        vayihi1.stop();
    }
}