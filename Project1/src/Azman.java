
import javax.swing.ImageIcon;


public class Azman extends Dusman{
    
    public Azman(String oyuncuAdi, String oyuncuTur) {
        super(oyuncuAdi, oyuncuTur);
        img=new ImageIcon("azman.png").getImage().getScaledInstance(70, 70, 0);
        this.damage=5;
        this.setOyuncuID(2);
    }
    
}
