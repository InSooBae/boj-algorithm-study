import java.util.Arrays;
import java.util.Scanner;

public class Main_BOJ_수고르기_G5_박지호_924ms {
	private static int N;
	private static long M, result;
	private static long[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextLong();
		result = Long.MAX_VALUE;
		arr = new long[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextLong();
		}

		Arrays.sort(arr);
		doit(0, 0);

		System.out.println(result);
	}

	public static void doit(int s, int e) {
		if (e >= N) {
			return;
		}
		long diff = arr[e] - arr[s];

		if (diff == M) {
			result = M;
			return;
		} else if (diff > M) {
			result = Math.min(result, diff);
			doit(s + 1, e);
		} else {
			doit(s, e + 1);
		}

	}
}
