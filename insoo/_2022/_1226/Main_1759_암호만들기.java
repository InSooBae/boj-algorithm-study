package _2022._1226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1759_암호만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        char[] arr = new char[C];
        char[] temp = new char[L];

        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        combination(0, 0, arr, temp, L, C, 0, 0, sb);

        System.out.println(sb);
    }

    private static void combination(int pos, int start, char[] arr, char[] temp, int L, int C, int vowelCnt, int consonantCnt, StringBuilder sb) {
        if (pos == L) {
            if (vowelCnt >= 1 && consonantCnt >= 2) {
                for (int i = 0; i < L; i++) {
                    sb.append(temp[i]);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            if (isVowel(arr[i])) {
                vowelCnt += 1;
            } else {
                consonantCnt += 1;
            }
            temp[pos] = arr[i];
            combination(pos + 1, i + 1, arr, temp, L, C, vowelCnt, consonantCnt, sb);

            if (isVowel(arr[i])) {
                vowelCnt -= 1;
            } else {
                consonantCnt -= 1;
            }
        }
    }

    private static boolean isVowel(char arr) {
        return arr == 'a' || arr == 'e' || arr == 'i' || arr == 'o' || arr == 'u';
    }
}
