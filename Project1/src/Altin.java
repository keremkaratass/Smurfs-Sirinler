
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Altin extends Obje{
    
    

    public Altin() {
        img= new ImageIcon(("gold.jpg")).getImage().getScaledInstance(70, 70, 0);
        imgicon = new ImageIcon(img);
        objeskor=5;
    }
    
    
    
  public void altinOlustur(JButton[][] btndizi){
         for (int i = 0; i < 5; i++) {
                int altinx=random.nextInt(12),altiny=random.nextInt(10);
                
                if(btndizi[altinx][altiny].getBackground()==Color.WHITE)
                btndizi[altinx][altiny].setIcon(imgicon);
                else i--;
           }
    }
    
    public void altinSil(JButton[][] btndizi){
        
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 10; j++) {
                
                   if(btndizi[i][j].getIcon()==imgicon){
                        btndizi[i][j].setIcon(null);
                        
                   }                                  
            }  
        }
        
    }

    
}
