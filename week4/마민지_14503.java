import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  public static int N, M, x, y, d, cnt = 0;
  public static int[][] table;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    table = new int[N][M];

    st = new StringTokenizer(br.readLine());
    x = Integer.parseInt(st.nextToken());
    y = Integer.parseInt(st.nextToken());
    d = Integer.parseInt(st.nextToken());

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        table[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    countCleanLoop();
    bw.write(String.valueOf(cnt));
    bw.flush();
    bw.close();
  }

  private static void countCleanLoop() {
    int tmpX = x, tmpY = y, tmpD = d;
    while (true) {
      if (table[x][y] == 0) {
        table[x][y] = 2;
        cnt++;
      }
      for (int i = 0; i < 4; i++) {
        tmpD -= 1;
        if (tmpD == -1) {
          tmpD = 3;
        }
        Map<String, Number> coordinates = getNewCoordinate(tmpD);
        tmpX = (int) coordinates.get("tmpX");
        tmpY = (int) coordinates.get("tmpY");
        if (tmpX >= 0 && tmpX < N && tmpY >= 0 && tmpY < M && table[tmpX][tmpY] == 0) {
          x = tmpX;
          y = tmpY;
          d = tmpD;
          break;
        } else { // 지금 보는 방향이 불가능하다면
          int backDirection = d;
          if (d > 1) {
            backDirection = d - 2;
          } else {
            backDirection = d + 2;
          }
          coordinates = getNewCoordinate(backDirection);
          tmpX = (int) coordinates.get("tmpX");
          tmpY = (int) coordinates.get("tmpY");
          if (i == 3) { // 모든 방향을 둘러봤다면
            if (table[tmpX][tmpY] != 1) { // 후퇴할 수 있다면
              x = tmpX;
              y = tmpY;
              break;
            } else { // 후퇴할 칸이 벽이라면 종료
              return;
            }
          } else { // 확인할 방향이 남아있다면
            continue;
          }
        }
      }
    }
  }

  private static Map<String, Number> getNewCoordinate(int direct) {
    Map<String, Number> coordinates = new HashMap<>();
    if (direct == -1) {
      direct = 3;
    }
    if (direct == 3) {
      coordinates.put("tmpX", x);
      coordinates.put("tmpY", y - 1);
    } else if (direct == 2) {
      coordinates.put("tmpX", x + 1);
      coordinates.put("tmpY", y);
    } else if (direct == 1) {
      coordinates.put("tmpX", x);
      coordinates.put("tmpY", y + 1);
    } else if (direct == 0) {
      coordinates.put("tmpX", x - 1);
      coordinates.put("tmpY", y);
    } else {
      coordinates.put("tmpX", x);
      coordinates.put("tmpY", y);
    }
    return coordinates;
  }
}
