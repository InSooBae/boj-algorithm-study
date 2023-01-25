import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1339 {

    static int N;

    static int[] letter = new int[26]; // A==0 ~ Z==25

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            int size = line.length();

            for (int j = 0; j < size; j++) {
                int idx = line.charAt((size-1) - j) - 'A';
                letter[idx] += (int) Math.pow(10.0, (double)j);
            }

        } // input for

        Arrays.sort(letter);

        int answer = 0;
        int cnt = 9;
        for (int i = 25; i >= 0; i--) {
            if(letter[i] == 0 || cnt == 0) break;

            answer += letter[i] * (cnt--);
        }

        System.out.print(answer);

    } // main()

}
