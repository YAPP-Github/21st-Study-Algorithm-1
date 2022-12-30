import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String compressed = br.readLine();

        // 열리는 괄호가 나오기 전의 숫자를 제외하고 계산된 길이
        Stack<Integer> lengthStack = new Stack<>();

        // 열리는 괄호 직전에 나온 숫자를 저장 (k)
        Stack<Integer> multiStack = new Stack<>();

        int count = 0;

        for (int i = 0; i < compressed.length(); i++) {
            char targetChar = compressed.charAt(i);

            if (targetChar == '(') {
                int multiNumber = compressed.charAt(i - 1) - '0';
                count -= 1;
                lengthStack.add(count);
                multiStack.add(multiNumber);
                count = 0;
            } else if (targetChar == ')') {
                int val = multiStack.peek();
                multiStack.pop();
                val *= count;

                int plus = lengthStack.peek();
                lengthStack.pop();
                count = plus + val;
            } else count++;
        }

        System.out.print(count);
    }
}

