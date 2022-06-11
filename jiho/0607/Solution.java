package kakao_2021;

import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {
		String[][] temp = new String[5][5];

		temp[0][0] = "POOOP";
		temp[0][1] = "OXXOX";
		temp[0][2] = "OPXPX";
		temp[0][3] = "OOXOX";
		temp[0][4] = "POXXP";

		temp[1][0] = "POOPX";
		temp[1][1] = "OXPXP";
		temp[1][2] = "PXXXO";
		temp[1][3] = "OXXXO";
		temp[1][4] = "OOOPP";

		temp[2][0] = "PXOPX";
		temp[2][1] = "OXOXP";
		temp[2][2] = "OXPOX";
		temp[2][3] = "OXXOP";
		temp[2][4] = "PXPOX";

		temp[3][0] = "OOOXX";
		temp[3][1] = "XOOOX";
		temp[3][2] = "OOOXX";
		temp[3][3] = "OXOOX";
		temp[3][4] = "OOOOO";

		temp[4][0] = "PXPXP";
		temp[4][1] = "XPXPX";
		temp[4][2] = "PXPXP";
		temp[4][3] = "XPXPX";
		temp[4][4] = "PXPXP";

		System.out.println(Arrays.toString(solution(temp)));
	}

	public static int[] solution(String[][] places) {
		int[] answer = { 1, 1, 1, 1, 1 };
		ex: for (int i = 0; i < 5; i++) {
			char[][] map = new char[5][5];
			int cnt_P = 0;
			int[][] pos_P = new int[25][2];

			for (int j = 0; j < 5; j++) {
				for (int j2 = 0; j2 < 5; j2++) {
					if (places[i][j].charAt(j2) == 'P') {
						pos_P[cnt_P][0] = j;
						pos_P[cnt_P][1] = j2;
						cnt_P++;
					}
					map[j][j2] = places[i][j].charAt(j2);
				}
			}

			// map에 대기실 정보가 다 들어갔다
			if (cnt_P > 13) {
				answer[i] = 0;
				continue;
			}

			// 첫 인원부터 좌표 비교
			for (int k = 0; k < cnt_P - 1; k++) {
				int r = pos_P[k][0];
				int c = pos_P[k][1];

				for (int k2 = k + 1; k2 < cnt_P; k2++) {
					int r2 = pos_P[k2][0];
					int c2 = pos_P[k2][1];
					int manhatan = Math.abs(r - r2) + Math.abs(c - c2);

					if (manhatan > 2) {
						continue;
					} else if (manhatan == 2) {
						if (r == r2) {
							// 같은 행
							int mid = (c + c2) / 2;
							if (map[r][mid] != 'X') {
								answer[i] = 0;
								continue ex;
							}

						} else if (c == c2) {
							// 같은 열
							int mid = (r + r2) / 2;
							if (map[mid][c] != 'X') {
								answer[i] = 0;
								continue ex;
							}
						} else {
							// 대각방향
							int min_r = Math.min(r, r2);
							int min_c = Math.min(c, c2);

							if (map[min_r][min_c] == 'P') {
								// 좌상단 우하단 대각선
								if (map[min_r + 1][min_c] != 'X' || map[min_r][min_c + 1] != 'X') {
									answer[i] = 0;
									continue ex;
								}

							} else {
								// 좌하단 우상단 대각선
								if (map[min_r][min_c] != 'X' || map[min_r + 1][min_c + 1] != 'X') {
									answer[i] = 0;
									continue ex;
								}
							}

						}

					} else {
						answer[i] = 0;
						continue ex;
					}
				}
			}

		} // 대기실 별 for

		return answer;
	}
}

// 일단 맨하탄거리가 2임에도 되는 경우는 2가지 => 같은행OR열 이거나 대각방향 나눠서 고려한다
// P가 10을 초과하면 무조건 실패

//응시자 : P
//빈테이블 : O
//파티션 : X