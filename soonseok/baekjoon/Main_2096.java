import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2096 {

    static int N;
    static int[][] tower;

    static int[][] minDP;
    static int[][] maxDP;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tower = new int[N][3];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            tower[i][0] = line.charAt(0) - '0';
            tower[i][1] = line.charAt(2) - '0';
            tower[i][2] = line.charAt(4) - '0';
        }

        maxDP = new int[N][3];
        minDP = new int[N][3];

        maxDP[0][0] = minDP[0][0] = tower[0][0];
        maxDP[0][1] = minDP[0][1] = tower[0][1];
        maxDP[0][2] = minDP[0][2] = tower[0][2];

        for (int i = 1; i < N; i++) {
            // max
            maxDP[i][0] = Math.max(maxDP[i-1][0], maxDP[i-1][1]) + tower[i][0]; // 좌측

            maxDP[i][2] = Math.max(maxDP[i-1][1], maxDP[i-1][2]) + tower[i][2]; // 우측

            int maxMid = Math.max(maxDP[i-1][0], maxDP[i-1][1]);
            maxMid = Math.max(maxMid, maxDP[i-1][2]);
            maxDP[i][1] = maxMid + tower[i][1]; // 중앙

            // min
            minDP[i][0] = Math.min(minDP[i-1][0], minDP[i-1][1]) + tower[i][0]; // 좌측

            minDP[i][2] = Math.min(minDP[i-1][1], minDP[i-1][2]) + tower[i][2]; // 우측

            int minMid = Math.min(minDP[i-1][0], minDP[i-1][1]);
            minMid = Math.min(minMid, minDP[i-1][2]);
            minDP[i][1] = minMid + tower[i][1]; // 중앙
        }

        int max = Math.max(maxDP[N-1][0], maxDP[N-1][1]);
        max = Math.max(max, maxDP[N-1][2]);

        int min = Math.min(minDP[N-1][0], minDP[N-1][1]);
        min = Math.min(min, minDP[N-1][2]);

        System.out.print(max + " " + min);

    } // main()

}
