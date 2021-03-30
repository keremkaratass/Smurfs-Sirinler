
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Fonksiyonlar {

    public int[][] txtokuma() throws IOException {
        String[] str = new String[13];
        FileInputStream fStream;
        try {

            fStream = new FileInputStream("harita.txt");
            DataInputStream dStream = new DataInputStream(fStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));

            for (int i = 0; i < 13; i++) {

                str[i] = bReader.readLine();

            }

        } catch (FileNotFoundException ex) {
            System.out.println("The file is not exist.");
        }

        String str1 = '\t' + "";

        String[] parts;
        int[][] a = new int[11][13];
        for (int i = 2; i < 13; i++) {
            parts = str[i].split(str1);
            for (int j = 0; j < 13; j++) {

                a[i - 2][j] = Integer.parseInt(parts[j]);

            }

        }

        return a;

    }

    public JButton[][] mapOlustur(int[][] dizi) {

        JButton[][] btndizi = new JButton[13][11];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                btndizi[i][j] = new JButton();
                btndizi[i][j].setBounds(i * 70, j * 70, 70, 70);

                if (dizi[j][i] == 0) {
                    btndizi[i][j].setBackground(Color.DARK_GRAY);
                } else {
                    btndizi[i][j].setBackground(Color.white);
                }

            }
        }


        return btndizi;
    }
    
    public int[][] dijkstraMap(JButton[][] btndizi){
        
        int map[][]=new int[143][143];
        int n=0,m=0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {
                for (int k = 0; k < 11; k++) {
                    for (int l = 0; l < 13; l++) {
                       
                        if((btndizi[j][i].getBackground()==Color.white && btndizi[l][k].getBackground()==Color.white) && (((i-k==1 || i-k==-1)&&(j==l))||((j-l==1||j-l==-1)&&(i==k))))
                            map[n][m]=1;
                        else
                            map[n][m]=0;
                        
                        m++;
                        if(m==143){
                            n++;
                            m=0;
                        }
                            
                    }
                    
                }
                
            }
            
        }
        return map;
    }

    public String[] secondtxtokuma() throws IOException {
        String[] str2 = new String[2];
        FileInputStream fStream;
        try {

            fStream = new FileInputStream("harita.txt");
            DataInputStream dStream = new DataInputStream(fStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));

            for (int i = 0; i<2; i++) {

                str2[i] = bReader.readLine();

            }
     } catch (FileNotFoundException ex) {
            System.out.println("The file is not exist.");
        }
        
        return str2;

    }

}
