package _2023._0411;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_터렛_S3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            double calc = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
            double r = r1 + r2;
//            System.out.println("calc = " + calc);
//            System.out.println("r = " + r);
            if (calc == 0 && r1 == r2) {
                sb.append(-1).append("\n");
                continue;
            }
            if (calc > r) {
                sb.append(0).append("\n");
            } else if (calc == r) {
                sb.append(1).append("\n");
            } else {
                if (calc + Math.min(r1,r2) == Math.max(r1, r2)) {
                    sb.append(1).append("\n");
                } else if (calc + Math.min(r1,r2) < Math.max(r1, r2)) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(2).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
