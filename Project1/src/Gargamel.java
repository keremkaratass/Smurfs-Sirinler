
import javax.swing.ImageIcon;


public class Gargamel extends Dusman{
    
    public Gargamel(String oyuncuAdi, String oyuncuTur) {
        super(oyuncuAdi, oyuncuTur);
        img=new ImageIcon("gargamel.png").getImage().getScaledInstance(70, 70, 0);
        this.damage=15;
        this.setOyuncuID(1);
    }
    
    
}
