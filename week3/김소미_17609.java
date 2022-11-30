import java.io.*;
import java.util.*;

public class Main {
    public static int t;
    public static String s;
    public static char[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++) {
            s = br.readLine();
            arr = s.toCharArray();
            int left = 0;
            int right = s.length() - 1;
            if(isPalindrome(left, right)) {
                System.out.println(0);
                continue;
            }
            if(similarPalindrome(left, right)) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }

    private static boolean isPalindrome(int left, int right) {
        while(left <= right) {
            if(arr[left] != arr[right])
                return false;
            left += 1;
            right -= 1;
        }

        return true;
    }

    private static boolean similarPalindrome(int left, int right) {
        while(left <= right) {
            if(arr[left] != arr[right]) {
                if(!isPalindrome(left+1, right) && !isPalindrome(left, right-1)) {
                    return false;
                } else{
                    return true;
                }
            }
            left += 1;
            right -= 1;
        }
        return true;
    }
    
}
