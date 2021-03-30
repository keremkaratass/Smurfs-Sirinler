
import java.awt.Image;
import javax.swing.ImageIcon;


public class TembelSirin extends Oyuncu{
    
    
    
    
    public TembelSirin(String oyuncuAdi, String oyuncuTur) {
        super(oyuncuAdi, oyuncuTur);
        img= new ImageIcon(("tembel.png")).getImage().getScaledInstance(70, 70, 0);
        this.setOyuncuID(1);
    }

    public Image img() {
        return img;
    }
    
    
}
