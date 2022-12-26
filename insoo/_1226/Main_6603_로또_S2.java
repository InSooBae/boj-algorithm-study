package _1226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6603_로또_S2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] temp = new int[6];

        boolean isLoop = false;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            
            int[] s = new int[k];

            inputSet(st, k, s);

            combination(0, 0, s, temp, sb);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void combination(int pos, int start, int[] s, int[] temp, StringBuilder sb) {
        if (pos == 6) {
            for (int i : temp) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < s.length; i++) {
            temp[pos] = s[i];
            combination(pos + 1, i + 1, s, temp, sb);
        }
    }

    private static void inputSet(StringTokenizer st, int k, int[] s) {
        for (int i = 0; i < k; i++) {
            int number = Integer.parseInt(st.nextToken());
            s[i] = number;
        }
    }
}
