
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Mantar extends Obje{
     
    
     
    

    public Mantar() {
        img= new ImageIcon(("mantar.jpg")).getImage().getScaledInstance(70, 70, 0);
        imgicon= new ImageIcon(img);
        objeskor=50;
    }
    
    
    
  public void mantarOlustur(JButton[][] btndizi){
         for (int i = 0; i < 1; i++) {
                int mantarx=random.nextInt(12),mantary=random.nextInt(10);
                
                if(btndizi[mantarx][mantary].getBackground()==Color.WHITE)
                btndizi[mantarx][mantary].setIcon(imgicon);
                else i--;
           }
    }
    
   public void mantarSil(JButton[][] btndizi){
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                
                   if(btndizi[i][j].getIcon()==imgicon){
                        btndizi[i][j].setIcon(null);
                        
                   }                                  
            }  
        }
        
    }

  
}
