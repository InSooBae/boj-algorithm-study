import java.io.*;
import java.util.*;

public class Main_2230 {

	static int N, M;

	static int[] arr;

	static int min = Integer.MAX_VALUE; // 최소 값을 저장

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 숫자 개수
		M = Integer.parseInt(st.nextToken()); // 차이가 M 이상

		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 1. 오름차순 정렬(투포인터로 우측진행할때, 차이를 크거나 작게 조정이 가능)
		Arrays.sort(arr);

		// 2. 투포인터 진행
		int e = 0;

		for (int s = 0; s < N; s++) { // 시작 위치를 변경

			while (e < N && arr[e] - arr[s] < M) { // 배열 끝이 아니고, 차이 조건이 만족되지 않은 경우
				e++; // 더 큰 차이로 만든다
			}

			if (e == N) break; // 배열 범위를 벗어나면 종료

			min = Math.min(min, arr[e] - arr[s]); // 차이 최소값을 갱신
		}

		System.out.print(min);

	} // main()
}
