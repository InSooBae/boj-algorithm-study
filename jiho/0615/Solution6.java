package kakao_blind_2022;

public class Solution6 {

	public static void main(String[] args) {
		int[][] board = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int[][] skill = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };

//		int[][] board = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
//		int[][] skill = { { 1, 1, 1, 2, 2, 4 }, { 1, 0, 0, 1, 1, 2 }, { 2, 2, 0, 2, 0, 100 } };
		System.out.println(solution2(board, skill));
	}

	public static int solution(int[][] board, int[][] skill) {

		for (int i = 0; i < skill.length; i++) {
			if (skill[i][0] == 1) { // 공격
				int r1 = skill[i][1];
				int c1 = skill[i][2];
				int r2 = skill[i][3];
				int c2 = skill[i][4];
				int degree = skill[i][5];

				for (int j = r1; j < r2 + 1; j++) {
					for (int j2 = c1; j2 < c2 + 1; j2++) {
						board[j][j2] -= degree;
					}
				}

			} else { // 회복
				int r1 = skill[i][1];
				int c1 = skill[i][2];
				int r2 = skill[i][3];
				int c2 = skill[i][4];
				int degree = skill[i][5];

				for (int j = r1; j < r2 + 1; j++) {
					for (int j2 = c1; j2 < c2 + 1; j2++) {
						board[j][j2] += degree;
					}
				}
			}
		}

		int answer = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] > 0) {
					answer++;
				}
			}
		}

		return answer;
	}

	public static int solution2(int[][] board, int[][] skill) {

		int[][] map = new int[board.length + 1][board[0].length + 1];

		for (int i = 0; i < skill.length; i++) {
			if (skill[i][0] == 1) { // 공격
				int r1 = skill[i][1];
				int c1 = skill[i][2];
				int r2 = skill[i][3];
				int c2 = skill[i][4];
				int degree = skill[i][5];

				map[r1][c1] -= degree;
				map[r2 + 1][c2 + 1] -= degree;
				map[r1][c2 + 1] += degree;
				map[r2 + 1][c1] += degree;

			} else { // 회복
				int r1 = skill[i][1];
				int c1 = skill[i][2];
				int r2 = skill[i][3];
				int c2 = skill[i][4];
				int degree = skill[i][5];

				map[r1][c1] += degree;
				map[r2 + 1][c2 + 1] += degree;
				map[r1][c2 + 1] -= degree;
				map[r2 + 1][c1] -= degree;

			}
		}

		for (int i = 0; i < map[0].length; i++) {
			for (int j = 1; j < map.length; j++) {
				map[j][i] += map[j - 1][i];
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 1; j < map[0].length; j++) {
				map[i][j] += map[i][j - 1];
			}
		}

		int answer = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] + map[i][j] > 0) {
					answer++;
				}
			}
		}

		return answer;
	}

}

// 건물의 내구도를 나타내는 2차원 정수 배열 board
// 적의 공격 혹은 아군의 회복 스킬을 나타내는 2차원 정수 배열 skill
// 적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수를 return

// skill의 각 행은 [type, r1, c1, r2, c2, degree]
// 1 = 공격 2 = 회복
// 내구도 < 1  일 경우 파괴로 간주

// skill의 길이만큼 반복문 돌리고
// board를 전체탐색해서 리턴