import java.io.*;
import java.util.StringTokenizer;
 
public class Main {
    static int n, m, k;
    static int r, c;
    static int[][] notebook;
    static int[][] sticker;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        notebook = new int[n][m];
 
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            sticker = new int[10][10];
 
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
 
                for (int j = 0; j < c; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            for (int dir = 0; dir < 4; dir++) {
                boolean isAttach = false;
 
                for (int i = 0; i <= n - r; i++) {
                    if (isAttach) {
                        break;
                    }
 
                    for (int j = 0; j <= m - c; j++) {
                        if (isPossible(i, j)) {
                            attach(i, j);
                            isAttach = true;
                            break;
                        }
                    }
                }
 
                if (isAttach) {
                    break;
                }
 
                rotate();
            }
        }
 
        int cnt = 0;
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (notebook[i][j] == 1) {
                    cnt++;
                }
            }
        }
 
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
 
    static boolean isPossible(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (notebook[x + i][y + j] == 1 && sticker[i][j] == 1) {
                    return false;
                }
            }
        }
 
        return true;
    }
 
    static void attach(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (sticker[i][j] == 1) {
                    notebook[x + i][y + j] = 1;
                }
            }
        }
    }
 
    static void rotate() {
        int[][] tmp = new int[10][10];
 
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tmp[i][j] = sticker[i][j];
            }
        }
 
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                sticker[i][j] = tmp[r - 1 - j][i];
            }
        }
 
        int temp = r;
        r = c;
        c = temp;
    }
}
