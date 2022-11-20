import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int M;
    public static int[] rtnArr;
    public static boolean[] checkArr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rtnArr = new int[M];
        checkArr = new boolean[N + 1];

        recursive(0);
        System.out.println(sb);
    }

    public static void recursive(int depth) {
        if (depth == M) {
            for (int num :
                    rtnArr) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (checkArr[i] == false) {
                rtnArr[depth] = i;
                checkArr[i] = true;
                recursive(depth + 1);
                checkArr[i] = false;
            }
        }
    }
}
