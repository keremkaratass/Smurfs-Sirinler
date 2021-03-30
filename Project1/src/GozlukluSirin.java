
import javax.swing.ImageIcon;


public class GozlukluSirin extends Oyuncu{

    public GozlukluSirin(String oyuncuAdi, String oyuncuTur){
        super(oyuncuAdi, oyuncuTur);
        img= new ImageIcon(("gozluklu.png")).getImage().getScaledInstance(70, 70, 0);
        this.setOyuncuID(2);
        
    }
    
}
