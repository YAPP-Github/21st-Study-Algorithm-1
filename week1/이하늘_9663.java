import java.io.*;

public class Main {
    public static void main(String[ ] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n  = Integer.parseInt(br.readLine());
        int[] queen = new int[n];
        System.out.println(search(queen, 0));
    }

    public static int search(int[] queen, int row) {
        int n = queen.length;
        int count = 0;
        if(n == row) return 1;

        for(int col = 0; col < n; col++) {
            queen[row] = col;
            if(check(queen, row)) {
                 count += search(queen, row + 1);
            }
        }
        return count;
    }

    public static boolean check(int[] queen, int row) {
        for(int i = 0; i < row; i++) {
            if(
                queen[i] == queen[row] ||
                (Math.abs(queen[i] - queen[row]) == row - i) 
            ) return false;
        }
        return true;
    }
}