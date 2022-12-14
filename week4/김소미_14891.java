import java.io.*;
import java.util.*;

public class Main {
    static int[][] gear;
    static int direction[];
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gear = new int[4][8];

        for(int i=0; i<4; i++) {
            gear[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(k-->0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int turn = Integer.parseInt(st.nextToken());

            direction = new int[4];
            direction[start] = turn;
            checkDir(start);
            gearTurn();
        }

        int total =0;
		for(int i=0; i<4; i++) {
			total += Math.pow(2, i) * gear[i][0];
		}
		System.out.println(total);
    }

    static void checkDir(int start) {
        //왼쪽 톱니 방향
        for(int i=start-1; i>=0; i--) {
            if(gear[i][2] != gear[i+1][6]) {
                direction[i] = -1 * direction[i+1];
            } else {
                break;
            }
        }
        //오른쪽 톱니 방향
        for(int i=start+1 ; i<4; i++) {
            if(gear[i][6] != gear[i-1][2]) {
                direction[i] = -1 * direction[i-1];
            } else{
                break;
            }
        }

    }

    static void gearTurn() {
        int temp = 0;

        for(int i=0; i<4; i++) {
            //시계방향
            if(direction[i] == 1) {
                temp = gear[i][7];
                for(int j=7; j>0; j--) {
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = temp;
            }
            // 반시계방향
            if(direction[i] == -1) {
                temp = gear[i][0];
                for(int j=0; j<7; j++) {
                    gear[i][j] = gear[i][j+1];
                }
                gear[i][7] = temp;
            }
        }
    }
}
