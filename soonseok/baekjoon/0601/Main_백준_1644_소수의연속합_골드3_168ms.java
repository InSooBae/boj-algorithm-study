import java.io.*;
import java.util.*;

public class Main_1644 {

	static int N;

	static boolean[] primes;

	static List<Integer> list = new ArrayList<Integer>(); // 소수를 저장할 곳

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 이 자연수를 나타낼 수 있는 경우의 수를 구하라

		// 1. 에라토스테네스의 체로 연속한 소수 구하기(N까지 구하면 된다)
		primes = new boolean[N + 1];

		primes[0] = primes[1] = true; // 제외

		for (int i = 2; i * i <= N; i++) {
			// i 가 소수라면
			if (!primes[i]) {
				// i의 배수는 소수가 아니다
				for (int j = i * i; j <= N; j += i) {
					primes[j] = true;
				}
			}
		}

		for (int i = 2; i <= N; i++) {
			if (!primes[i]) list.add(i); // 소수를 리스트에 추가
		}

		// 2. 소수 리스트에서 투포인터를 이용하여 조합을 만들 수 있는지 체크
		int lo = 0;
		int hi = 0;
		int sum = 0;
		int cnt = 0;

		while (true) { // 우측 포인터가 끝을 넘어간 경우 종료

			if (sum >= N) { // 합이 더 큰 경우,
				sum -= list.get(lo++); // 왼쪽 포인터를 우측으로 옮기며 연속합 빼준다
			}
			else if (hi == list.size())
				break; // 더이상 값을 만들수가 없으면서 우측 끝에 도달한 경우
			// (값이 목표값보다 작은데, 왼쪽 포인터 옮기면 계속 작아지니, 그냥 종료해도 된다)

			else { // sum < N, 합이 더 작으니 우측 포인터 우측으로
				sum += list.get(hi++); // 포인터 범위가 넓어지니 연속합 더해준다
			}

			if (sum == N) cnt++; // 경우의 수가 맞을 경우 더해준다 (위에서 연산한 이후 체크)
		}

		System.out.print(cnt);

	} // main()
}
