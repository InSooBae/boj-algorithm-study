import java.io.*;
import java.util.*;

public class Main_1806 {

	static int N, S;

	static int[] arr;

	static int min = Integer.MAX_VALUE; // 최소 길이를 저장할 곳

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 숫자 개수
		S = Integer.parseInt(st.nextToken()); // 합이 S 이상

		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int e = 0; // 끝 위치
		int sum = arr[0]; // 현재의 부분합

		for (int s = 0; s < N; s++) { // 시작 위치를 뒤로 옮긴다
			while (e < N && sum < S) { // 배열 끝이 아니고, 부분합 조건을 만족하지 못했다면 반복
				e++; // 다음 수도 부분합에 추가해준다
				if (e != N) sum += arr[e]; // 배열을 초과하지 않았다면 부분합 연산
			}

			if (e == N) break; // 배열 범위를 벗어나면 종료

			min = Math.min(min, e - s + 1); // 부분합 배열의 길이 최소값을 갱신
			sum -= arr[s]; // 반복문이 진행되며, 시작 위치가 다음으로 움직이면서 맨 앞의 배열값이 빠진다
		}

		if (min == Integer.MAX_VALUE) min = 0; // 불가능한 경우 0으로 갱신

		System.out.print(min);

	} // main()
}
