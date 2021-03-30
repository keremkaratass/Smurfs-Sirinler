
import static com.sun.webkit.graphics.WCImage.getImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.io.*;
import static java.lang.Math.random;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SmurfGame extends JFrame implements KeyListener, ActionListener {

    Timer timer = new Timer(10, this);
    Image sirine;
    JButton[][] btndizi;
    Oyuncu oyuncu;
    int delayaltin = 0, delayaltin2 = 0, delaymantar = 0, delaymantar2 = 0;
    Random random = new Random();
    Altin altin = new Altin();
    Mantar mantar = new Mantar();
    Azman azman;
    Gargamel gargamel;
    DijkstraAlgorithm t;
    DijkstraAlgorithm t2;
    Lokasyon lokasyon = new Lokasyon();
    Fonksiyonlar fonksiyonlar;
    int n = 0;
    int gx,gy,ax,ay;

    public SmurfGame() throws IOException {

        this.setResizable(false);
        this.setFocusable(true);
        this.setSize(13 * 70, 11 * 70 + 25);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.yellow);
        this.addKeyListener(this);

        fonksiyonlar = new Fonksiyonlar();
        int[][] mapdizi = fonksiyonlar.txtokuma();
        String[] satir = fonksiyonlar.secondtxtokuma();
        btndizi = fonksiyonlar.mapOlustur(mapdizi);

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {

                this.add(btndizi[i][j]);

            }
        }

        
        sirine = new ImageIcon(("sirine.png")).getImage().getScaledInstance(70, 70, 0);
        btndizi[12][7].setIcon(new ImageIcon(sirine));
        oyuncu = new TembelSirin("Tembel sirin", "tembel");
        
        System.out.println(oyuncu.getOyuncuAdi());
        timer.start();

        btndizi[oyuncu.x][oyuncu.y].setIcon(new ImageIcon(oyuncu.img));
        btndizi[11][1].setText("" + oyuncu.skor);

        if (satir[0].contains("Gargamel")) {
            gargamel = new Gargamel("Gargamel", "Düşman");
            int last = satir[0].length() - 1;
            char result = satir[0].charAt(last);
            if (result == 'A' || result == 'D') {
                if (result == 'A') {
                    btndizi[gargamel.x][gargamel.y].setIcon(new ImageIcon(gargamel.img));
                    gx=gargamel.x;
                    gy=gargamel.y;
                    lokasyon.setGargamelx(gargamel.x);
                    lokasyon.setGargamely(gargamel.y);
                }
                if (result == 'D') {
                    gx=gargamel.x4;
                    gy=gargamel.y4;
                    btndizi[gargamel.x4][gargamel.y4].setIcon(new ImageIcon(gargamel.img));
                    lokasyon.setGargamelx(gargamel.x4);
                    lokasyon.setGargamely(gargamel.y4);
                }
            }
        }

        if (satir[1].contains("Azman")) {
            azman = new Azman("Azman", "Düşman");
            int last = satir[1].length() - 1;
            char result = satir[1].charAt(last);
            if (result == 'A' || result == 'B' || result == 'C' || result == 'D') {
                if (result == 'A') {
                    ax=azman.x;
                    ay=azman.y;
                    btndizi[azman.x][azman.y].setIcon(new ImageIcon(azman.img));
                    lokasyon.setAzmanx(azman.x);
                    lokasyon.setAzmany(azman.y);
                }
                if (result == 'B') {
                    ax=azman.x2;
                    ay=azman.y2;
                    btndizi[azman.x2][azman.y2].setIcon(new ImageIcon(azman.img));
                    lokasyon.setAzmanx(azman.x2);
                    lokasyon.setAzmany(azman.y2);
                }
                if (result == 'C') {
                    ax=azman.x3;
                    ay=azman.y3;
                    btndizi[azman.x3][azman.y3].setIcon(new ImageIcon(azman.img));
                    lokasyon.setAzmanx(azman.x3);
                    lokasyon.setAzmany(azman.y3);
                }
                if (result == 'D') {
                    ax=azman.x4;
                    ay=azman.y4;
                    btndizi[azman.x4][azman.y4].setIcon(new ImageIcon(azman.img));
                    lokasyon.setAzmanx(azman.x4);
                    lokasyon.setAzmany(azman.y4);
                }
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if(c == KeyEvent.VK_SPACE){
            oyuncu = new GozlukluSirin("Gozluklu sirin", "gozluklu");
        }
        btndizi[lokasyon.getGargamelx()][lokasyon.getGargamely()].setIcon(null);
        btndizi[lokasyon.getAzmanx()][lokasyon.getAzmany()].setIcon(null);
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {

                if (btndizi[i][j].getBackground() == Color.GREEN) {
                    btndizi[i][j].setBackground(Color.white);
                }
                if (btndizi[i][j].getBackground() == Color.YELLOW) {
                    btndizi[i][j].setBackground(Color.white);
                }
                if (btndizi[i][j].getBackground() == Color.ORANGE) {
                    btndizi[i][j].setBackground(Color.white);
                }
            }

        }

        if (c == KeyEvent.VK_LEFT) {
            if (btndizi[oyuncu.x - 1][oyuncu.y].getBackground() == Color.WHITE && oyuncu.x != 1) {
                if (btndizi[oyuncu.x - 1][oyuncu.y].getIcon() == altin.imgicon) {
                    oyuncu.skor += altin.objeskor;
                }
                if (btndizi[oyuncu.x - 1][oyuncu.y].getIcon() == mantar.imgicon) {
                    oyuncu.skor += mantar.objeskor;
                }

                btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                btndizi[oyuncu.x - 1][oyuncu.y].setIcon(new ImageIcon(oyuncu.img));
                oyuncu.x--;
                if (oyuncu.getOyuncuID() == 2 && btndizi[oyuncu.x - 1][oyuncu.y].getBackground() == Color.WHITE && oyuncu.x != 1) {
                    if (btndizi[oyuncu.x - 1][oyuncu.y].getIcon() == altin.imgicon) {
                        oyuncu.skor += altin.objeskor;
                    }
                    if (btndizi[oyuncu.x - 1][oyuncu.y].getIcon() == mantar.imgicon) {
                        oyuncu.skor += mantar.objeskor;
                    }
                    btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                    btndizi[oyuncu.x - 1][oyuncu.y].setIcon(new ImageIcon(oyuncu.img));
                    oyuncu.x--;
                }
            }

        }
        if (c == KeyEvent.VK_RIGHT) {
            if (oyuncu.x != 12) {
                if (btndizi[oyuncu.x + 1][oyuncu.y].getBackground() == Color.WHITE) {
                    if (btndizi[oyuncu.x + 1][oyuncu.y].getIcon() == altin.imgicon) {
                        oyuncu.skor += altin.objeskor;
                    }
                    if (btndizi[oyuncu.x + 1][oyuncu.y].getIcon() == mantar.imgicon) {
                        oyuncu.skor += mantar.objeskor;
                    }

                    btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                    btndizi[oyuncu.x + 1][oyuncu.y].setIcon(new ImageIcon(oyuncu.img));
                    oyuncu.x++;
                    if(oyuncu.x+1!=13){
                        if (oyuncu.getOyuncuID() == 2 && btndizi[oyuncu.x + 1][oyuncu.y].getBackground() == Color.WHITE ) {
                        if (btndizi[oyuncu.x + 1][oyuncu.y].getIcon() == altin.imgicon) {
                            oyuncu.skor += altin.objeskor;
                        }
                        if (btndizi[oyuncu.x + 1][oyuncu.y].getIcon() == mantar.imgicon) {
                            oyuncu.skor += mantar.objeskor;
                        }
                        btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                        btndizi[oyuncu.x + 1][oyuncu.y].setIcon(new ImageIcon(oyuncu.img));
                        oyuncu.x++;
                    }
                    }
                    
                }

                if (oyuncu.x == 12) {
                    JOptionPane.showMessageDialog(this, oyuncu.getOyuncuAdi() + " şirineye ulaşmıştır.Tebrik ederiz:) ) Skor:" + oyuncu.skor);
                    System.exit(0);
                }
            }

        }

        if (c == KeyEvent.VK_UP) {
            if (btndizi[oyuncu.x][oyuncu.y - 1].getBackground() == Color.WHITE && oyuncu.y != 1) {
                if (btndizi[oyuncu.x][oyuncu.y - 1].getIcon() == altin.imgicon) {
                    oyuncu.skor += altin.objeskor;
                }
                if (btndizi[oyuncu.x][oyuncu.y - 1].getIcon() == mantar.imgicon) {
                    oyuncu.skor += mantar.objeskor;
                }
                btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                btndizi[oyuncu.x][oyuncu.y - 1].setIcon(new ImageIcon(oyuncu.img));
                oyuncu.y--;
                if (oyuncu.getOyuncuID() == 2 && btndizi[oyuncu.x][oyuncu.y - 1].getBackground() == Color.WHITE && oyuncu.y != 1) {
                    if (btndizi[oyuncu.x][oyuncu.y - 1].getIcon() == altin.imgicon) {
                        oyuncu.skor += altin.objeskor;
                    }
                    if (btndizi[oyuncu.x][oyuncu.y - 1].getIcon() == mantar.imgicon) {
                        oyuncu.skor += mantar.objeskor;
                    }
                    btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                    btndizi[oyuncu.x][oyuncu.y - 1].setIcon(new ImageIcon(oyuncu.img));
                    oyuncu.y--;
                }
            }

        }

        if (c == KeyEvent.VK_DOWN) {
            if (btndizi[oyuncu.x][oyuncu.y + 1].getBackground() == Color.WHITE && oyuncu.y != 9) {
                if (btndizi[oyuncu.x][oyuncu.y + 1].getIcon() == altin.imgicon) {
                    oyuncu.skor += altin.objeskor;
                }
                if (btndizi[oyuncu.x][oyuncu.y + 1].getIcon() == mantar.imgicon) {
                    oyuncu.skor += mantar.objeskor;
                }

                btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                btndizi[oyuncu.x][oyuncu.y + 1].setIcon(new ImageIcon(oyuncu.img));
                oyuncu.y++;
                if (oyuncu.getOyuncuID() == 2 && btndizi[oyuncu.x][oyuncu.y + 1].getBackground() == Color.WHITE && oyuncu.y != 9) {
                    if (btndizi[oyuncu.x][oyuncu.y + 1].getIcon() == altin.imgicon) {
                        oyuncu.skor += altin.objeskor;
                    }
                    if (btndizi[oyuncu.x][oyuncu.y + 1].getIcon() == mantar.imgicon) {
                        oyuncu.skor += mantar.objeskor;
                    }

                    btndizi[oyuncu.x][oyuncu.y].setIcon(null);
                    btndizi[oyuncu.x][oyuncu.y + 1].setIcon(new ImageIcon(oyuncu.img));
                    oyuncu.y++;
                }
            }

        }
        
        

        t = new DijkstraAlgorithm();
        t.dijkstra(fonksiyonlar.dijkstraMap(btndizi), lokasyon.getGargamelx() + lokasyon.getGargamely() * 13, btndizi);

        t2 = new DijkstraAlgorithm();
        t2.dijkstra(fonksiyonlar.dijkstraMap(btndizi), lokasyon.getAzmanx() + lokasyon.getAzmany() * 13, btndizi);

        
        
         
        
        for (int i = 1; i < 24; i++) {

            if (t.getbuttonyol()[oyuncu.x][oyuncu.y][i + 1].getBackground() == Color.white) {
                t.getbuttonyol()[oyuncu.x][oyuncu.y][i + 1].setBackground(Color.GREEN);
            } else {
                t.getbuttonyol()[oyuncu.x][oyuncu.y][i + 1].setBackground(Color.ORANGE);
            }

            if (t2.getbuttonyol()[oyuncu.x][oyuncu.y][i].getBackground() == Color.white) {
                t2.getbuttonyol()[oyuncu.x][oyuncu.y][i].setBackground(Color.YELLOW);
            } else {
                t2.getbuttonyol()[oyuncu.x][oyuncu.y][i].setBackground(Color.ORANGE);
            }

        }
        

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                if (t.getbuttonyol()[oyuncu.x][oyuncu.y][2] == btndizi[i][j]) {
                    lokasyon.setGargamelx(i);
                    lokasyon.setGargamely(j);
                    
                }

                if (t2.getbuttonyol()[oyuncu.x][oyuncu.y][1] == btndizi[i][j]) {
                    lokasyon.setAzmanx(i);
                    lokasyon.setAzmany(j);
                    
                }
               
                

            }

        }
        
        
        
        btndizi[0][0].setBackground(Color.DARK_GRAY);
        
        
        
        if(lokasyon.getGargamelx()==0 && lokasyon.getGargamely()==0){
           
            oyuncu.skor-=gargamel.damage;
            lokasyon.setGargamelx(gx);
            lokasyon.setGargamely(gy);
            
            
        }
         if(lokasyon.getAzmanx()==0 && lokasyon.getAzmany()==0){
            
            oyuncu.skor-=azman.damage;
            lokasyon.setAzmanx(ax);
            lokasyon.setAzmany(ay);
            
            
        }
        
            
        btndizi[lokasyon.getGargamelx()][lokasyon.getGargamely()].setIcon(new ImageIcon(gargamel.img));
        btndizi[lokasyon.getAzmanx()][lokasyon.getAzmany()].setIcon(new ImageIcon(azman.img));
        btndizi[11][1].setText("" + oyuncu.skor);

        if (oyuncu.skor <= 0) {
                    JOptionPane.showMessageDialog(this, oyuncu.getOyuncuAdi() + " öldü :(" );
                    System.exit(0);
                }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        delayaltin++;
        delayaltin2++;
        delaymantar++;
        delaymantar2++;
        if (delayaltin % 500 == 0) {
            altin.altinOlustur(btndizi);
            delayaltin = 0;

        }

        if (delaymantar % 1000 == 0) {
            mantar.mantarOlustur(btndizi);
            delaymantar = 0;
        }

        if (delayaltin2 % 1000 == 0) {
            altin.altinSil(btndizi);
            delayaltin2 = 0;
        }

        if (delaymantar2 % 500 == 0 && delaymantar % 1000 != 0) {
            mantar.mantarSil(btndizi);
            delaymantar2 = 0;
        }

        repaint();
    }

}
