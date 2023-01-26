import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1300 {

    /*
        풀이 참고 : https://st-lab.tistory.com/281

        N ≤ 10^5 인 자연수
        따라서 NxN A행렬의 최대 크기는 10^10 (100억) -> 브루트 포스 불가

        B 행렬은 오름차순 정렬됨. 이는 B[11]=8 -> B행렬의 11번째 값은 8
        -> 8보다 작거나 같은 원소의 개수가 최소 11개

        B[K]=x 일 때, x보다 작거나 같은 원소의 개수가 최소 K개
        위 조건을 만족하는 x를 찾아서 이분탐색을 진행

        이 때, 행렬을 실제 만들지 않고 x보다 작거나 같은 원소의 개수를 어떻게 찾는가
        i=1, 1 2 3 4
        i=2, 2 4 6 8
        i=3, 3 6 9 12
        i=4, 4 8 12 16
        일종의 구구단이라는 것을 알 수 있다.
        이 때, x/i 값이 해당 row에서 x보다 작거나 같은 원소의 개수이다.

        1.
        우선 1 ≤ x ≤ N^2 인 상태에서, x는 항상 K보다 작거나 같다.
        -> 1 ≤ x ≤ K
        또 이분탐색 과정에서, 한 행에 대해 가질 수 있는 최대 원소는 N개라는 점을 주의하자

        2.
        또 lower-bound를 써야 한다. 중복 값이 존재하기 때문이다.
        찾고자 하는 값과 같거나 큰 수가 있는 첫 번째 인덱스를 찾아준다.

     */
    static int N, K;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 배열의 크기 1 indexing
        K = Integer.parseInt(br.readLine()); // B[K] 를 구해보자

        long left = 1;
        long right = K; // 범위는 1~K 다
        while(left < right) {
            
            long mid = (left + right) / 2;
            long cnt = 0; // 작거나 같은 원소의 개수

            for (int i = 1; i <= N; i++) { // 1부터 N단까지 구구단이 존재한다
                cnt += Math.min(mid/i, N); // x/i 값이 한 행(N) 을 초과하면 N으로 맞춘다
            }

            // cnt가 많은 경우, 임의의 x(mid)가 더 작아져야 한다는 뜻이다.
            if(K <= cnt) { right = mid; } // K개의 작거나 같은 원소를 가져야 하는데, 더 많다
            else { left = mid + 1; } // 현재 K보다 더 적은 원소를 가지고 있다
        }

        System.out.print(left); // left에는 K를 만족하는 수가 들어있다

    } // main()

}
