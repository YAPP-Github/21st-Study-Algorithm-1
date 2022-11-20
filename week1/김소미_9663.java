import java.io.*;

public class Main {
    public static int[] arr;
    public static int N;
    public static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        dfs(0);
        System.out.println(count);
    }    

    public static void dfs(int depth) {
        // 모든 원소를 다 채운 경우
        if (depth == N) {
            count++;
            return;
        }

        for (int i=0; i<N; i++) {
            arr[depth] = i;
            if(checkPossibility(depth)) {
                dfs(depth + 1);
            }
        }
    }

    public static boolean checkPossibility(int col) {
        for(int i=0; i < col; i++) {
            if (arr[col] == arr[i]) {
                return false;
            }

            // 대각선에 있는 경우
            else if (Math.abs(col-i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }

        return true;
    }
}
