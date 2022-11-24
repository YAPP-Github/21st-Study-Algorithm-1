import java.util.*;
import java.io.*;

public class Main {
    private static int[] arr;
    private static boolean[] visit;

    public static void main(String[ ] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       	StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        arr = new int[m];
        visit = new boolean[n];
        dfs(n, m, 0);
    }

    private static void dfs(int n, int m, int depth) {
        if(depth == m) {
            for(int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                arr[depth] = i + 1;
                dfs(n, m, depth + 1);
                visit[i] = false;
            }
        }
    }

}