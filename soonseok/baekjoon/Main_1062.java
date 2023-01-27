import java.io.*;
import java.util.*;

public class Main_1062 {

    static int N, K;
    static boolean[] learn = new boolean[26]; // a == 97
    static String[] words;

    static int maxWord = 0; // 단어 개수

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 단어 개수
        K = Integer.parseInt(st.nextToken()); // 배우는 알파벳 수

        words = new String[N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine(); // 단어 앞 뒤에서 anta, tica를 제외한다
            words[i] = line.substring(4, line.length() - 4);
        }

        // 반드시 a n t i c 다섯 알파벳은 읽어야 함

        if(K < 5) {
            System.out.print(0); // 하나도 읽을 수 없는 경우
            return;
        }

        if(K == 26) {
            System.out.print(N); // 모든 알파벳을 배운 경우
            return;
        }

        learn['a' - 'a'] = true;
        learn['n' - 'a'] = true;
        learn['t' - 'a'] = true;
        learn['i' - 'a'] = true;
        learn['c' - 'a'] = true;

        K -= 5; // 배울 수 있는 기회 5개 소모

        dfs(0,0);
        System.out.print(maxWord);

    } // main()

    // 알파벳을 고르는것은 순서와 상관이 없는 조합
    public static void dfs(int start, int count) {
        // 기저조건 : K개의 알파벳을 모두 고른 경우
        if(count == K) {
            
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                boolean flag = true; // 읽을 수 있다고 가정

                for (int j = 0; j < words[i].length(); j++) { // i번째 단어를 알파벳 단위로
                    if( !learn[ words[i].charAt(j) - 'a' ] ) { // 못 배운 알파벳인 경우
                        flag = false;
                        break;
                    }
                }

                if(flag) cnt++;
                
            }

            maxWord = Math.max(maxWord, cnt);

            return;
        }

        for (int i = start; i < 26; i++) {
            if(learn[i]) continue; // 이미 배운 알파벳은 넘어간다

            learn[i] = true; // 이번 알파벳을 배운다

            dfs(i, count + 1);

            learn[i] = false; // 이번 알파벳을 배우지 않는다
        }


    } // dfs()

}
