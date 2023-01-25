import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1700 {

    static int N, K;
    static int[] use;
    static boolean[] plug;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 멀티탭 구멍
        K = Integer.parseInt(st.nextToken()); // 전기 용품 총 사용 횟수

        use = new int[K];
        plug = new boolean[K + 1]; // K 이하의 수는 전자기기를 나타냄

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            use[i] = Integer.parseInt(st.nextToken());


        int available = N;
        int cnt = 0;

        for (int i = 0; i < K; i++) {
            int cur = use[i];

            if(plug[cur]) continue; // 플러그 꽂혀있으면 그냥 지나간다
            
            // !! 플러그가 꽂혀있지 않은 경우 !!
            
            if(available > 0) { // 빈 콘센트가 있는 경우, 꽂고 지나간다
                plug[cur] = true;
                available--;
                continue;
            }
            
            // 여기부터 자리가 없으니, 기존 콘센트를 꽂고 뽑아야 한다
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = i; j < K; j++) {
                // 이미 꽂혀있는데, 나중에 쓰는 애들만 체크
                if(plug[ use[j] ] && !list.contains( use[j] ) )
                    list.add(use[j]);
            }

            if(list.size() == N) { // 나중에 쓰는 애들로 꽉 찬 경우
                int idx = list.get(list.size() - 1); // 제일 나중에 쓰는 애 제거
                plug[idx] = false;
            }
            else { // 나중에 안 써서 뽑아도 되는 애 중 하나를 제거
                for (int j = 0; j < K+1; j++) {
                    if (plug[j] && !list.contains(j)) {
                        plug[j] = false;
                        break;
                    }
                }
            }


            cnt++; // 한 놈을 뽑았다
            plug[cur] = true; // 새 놈을 꼽았다

        } // for

        System.out.print(cnt);

    } // main()

}
