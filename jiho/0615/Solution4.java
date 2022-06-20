package kakao_blind_2022;

import java.util.Arrays;

public class Solution4 {

	static int max;
	static int[] lion, target, peachs, result; // 라이언이 쏜 화살

	public static void main(String[] args) {
		int n = 5;
		int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }; // 10점2번 9점1번 8점1번 ,,,,

//		int n = 9;
//		int[] info = { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 }; // 10점2번 9점1번 8점1번 ,,,,

//		int n = 10;
//		int[] info = { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 }; // 10점2번 9점1번 8점1번 ,,,,
		
//		int n = 1;
//		int[] info = { 1,0,0,0,0,0,0,0,0,0,0 }; // 10점2번 9점1번 8점1번 ,,,,

//		int n = 3;
//		int[] info = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 }; // 10점2번 9점1번 8점1번 ,,,,
		System.out.println(Arrays.toString(solution(n, info)));

	}

	public static int[] solution(int n, int[] info) {

		max = Integer.MIN_VALUE;
		result = new int[11];
		result[0] = -1;
		lion = new int[n];
		target = new int[11];
		peachs = info;

		for (int i = 0; i < 11; i++) {
			target[i] = 10 - i;
		}

		combi(0, 0, n);

		int[] answer = result;

		if (result[0] == -1) {
			return new int[] { -1 };
		} else {
			return answer;
		}

	}

	public static void combi(int cnt, int start, int n) {
		if (cnt == n) {
//			System.out.println(Arrays.toString(lion));
			calc();
			return;
		}

		for (int i = start; i < 11; i++) {
			lion[cnt] = target[i];
			combi(cnt + 1, i, n);
		}
	}

	public static void calc() {
		int[] lion_cnt = new int[11];
		int peach_total = 0;
		int lion_total = 0;

		for (int i = 0; i < lion.length; i++) {
			lion_cnt[10 - lion[i]]++;
		}

		for (int i = 0; i < lion_cnt.length; i++) {
			if (lion_cnt[i] == 0 && peachs[i] == 0) {
				continue;
			}

			if (lion_cnt[i] > peachs[i]) {
				lion_total += 10 - i;
			} else {
				peach_total += 10 - i;
			}
		}

//		System.out.println(Arrays.toString(lion_cnt));

//		if (lion_cnt[0] >= 3) {
//			System.out.println("lion : " + Arrays.toString(lion_cnt));
//			System.out.println("peach : " + Arrays.toString(peachs));
//
//		}

		int diff = lion_total - peach_total;

		if (diff > 0) {
			if (result[0] == -1) { // 처음 어피치보다 높은 점수다
				result = lion_cnt;
				max = diff;
//				System.out.println("FIRST : " + Arrays.toString(result));
			} else { // 전에도 이겼던 전적이 있다
				if (diff > max) {
					result = lion_cnt;
					max = diff;
				} else if (diff == max) {
					for (int i = 10; i > 0; i--) {
						if (lion_cnt[i] == result[i]) {
//						System.out.println("CONTINUE");
							continue;
						}

						if (lion_cnt[i] > result[i]) {
							result = lion_cnt;
//							System.out.println("CHANGED : " + Arrays.toString(result));
							break;
						} else {
//						System.out.println("BREAKED" + Arrays.toString(lion_cnt));
							break;
						}

					} // end of for
				}

			}
		}

	}

}

// 중복조합

// 화살의 개수를 담은 자연수 n 
// 어피치가 맞힌 과녁 점수의 개수를 10점부터 0점까지 순서대로 담은 정수 배열 info
// 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return