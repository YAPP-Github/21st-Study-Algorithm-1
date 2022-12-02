import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  public static int N;
  public static char[] input;
  public static HashMap<String, Integer> map = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      input = st.nextToken().toCharArray();
      int left = 0, right = input.length - 1;
      if (checkPalindrome(left, right)) {
        bw.write(String.valueOf(0));
      } else if (checkSimilar(left, right)) {
        bw.write(String.valueOf(1));
      } else {
        bw.write(String.valueOf(2));
      }
      bw.newLine();
    }
    bw.flush();
    bw.close();
  }

  private static boolean checkPalindrome(int left, int right) {
    while (left <= right) {
      if (input[left] != input[right]) {
        return false;
      }
      left += 1;
      right -= 1;
    }
    return true;
  }

  private static boolean checkSimilar(int left, int right) {
    while (left <= right) {
      if (input[left] != input[right]) {
        boolean a = checkPalindrome(left + 1, right);
        boolean b = checkPalindrome(left, right - 1);
        if (a == false && b == false) {
          return false;
        } else {
          return true;
        }
      }
      left += 1;
      right -= 1;
    }
    return true;
  }
}