
import java.awt.Image;


public abstract class Karakterler {
    private int oyuncuID;
    private String oyuncuAdi;
    private String oyuncuTur;
    int x; int y;
     Image img;

    public Karakterler(String oyuncuAdi, String oyuncuTur) {
        
        this.oyuncuAdi = oyuncuAdi;
        this.oyuncuTur = oyuncuTur;
        
    }
    
    

    public int getOyuncuID() {
        return oyuncuID;
    }

    public String getOyuncuAdi() {
        return oyuncuAdi;
    }

    

    public String getOyuncuTur() {
        return oyuncuTur;
    }

    
    public void setOyuncuID(int oyuncuID) {
        this.oyuncuID = oyuncuID;
    }

    public void setOyuncuAdi(String oyuncuAdi) {
        this.oyuncuAdi = oyuncuAdi;
    }

   

    public void setOyuncuTur(String oyuncuTur) {
        this.oyuncuTur = oyuncuTur;
    }
    
    
    
    
}
