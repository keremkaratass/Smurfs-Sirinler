
import java.io.IOException;
import javax.swing.JButton;

public class DijkstraAlgorithm {

    static final int V = 143;
    int yol[][];
    JButton buttonyol[][][];
    int n = 0, m = 0;
    int sayi;

    public JButton[][][] getbuttonyol() {
        return buttonyol;
    }

    public DijkstraAlgorithm() {

        yol = new int[V][25];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < 25; j++) {

                yol[i][j] = 0;
            }

        }

        

    }

  public int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

  public void dijkstra(int graph[][], int src, JButton[][] btndizi) {

        buttonyol = new JButton[13][11][25];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 25; k++) {

                    buttonyol[i][j][k] = new JButton();
                }

            }

        }
        int dist[] = new int[V]; // kaynaktan i ye olan uzaklık.

        // sptSet[i] eger nokta tamamen incelendiyse true döndürür. 
        Boolean sptSet[] = new Boolean[V];

        // düğümlere max değer ve false atama. 
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // kaynagın kendine uzaklıgı her zaman 0dır.
        dist[src] = 0;

        // kaynaktan her noktaya en kısa yolu bulma
        for (int count = 0; count < V - 1; count++) {
            // en kücük maliyeti olan noktayı kaynak olarak belirleme. 
            src = minDistance(dist, sptSet);

            // kaynak olarak belirlenen noktayı trueya cevirme. 
            sptSet[src] = true;

            // daha kısa yol bulunursa güncelleme yapma. 
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[src][v] != 0
                        && dist[src] != Integer.MAX_VALUE && dist[src] + graph[src][v] < dist[v]) {
                    dist[v] = dist[src] + graph[src][v];
                    n = 0;
                    for (int i = 0; i < 25; i++) {
                        if (yol[src][i] != 0) {
                            yol[v][i] = yol[src][i];
                        } else {
                            yol[v][i] = src;
                            break;
                        }
                    }

                }
            }

        }

        //buttonyol dizisine dijkstra yolundaki butonları atıyoruz.
        for (int i = 0; i < V; i++) {
            n = 0;
            for (int j = 0; j < 25; j++) {
                buttonyol[i % 13][i / 13][n] = btndizi[yol[i][j] % 13][yol[i][j] / 13];
                n++;
            }

        }

    }

}
