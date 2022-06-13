package kakao_blind_2022;

import java.util.StringTokenizer;

public class Solution1 {

	public static void main(String[] args) {
//		String[] test_id_list = { "muzi", "frodo", "apeach", "neo" };
//		String[] test_report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
//		int test_k = 2;
		String[] test_id_list = { "con", "ryan" };
		String[] test_report = { "ryan con", "ryan con", "ryan con", "ryan con" };
		int test_k = 3;

//		int[] result = solution2(test_id_list, test_report, test_k);
		int[] result2 = solution2(test_id_list, test_report, test_k);
		for (int i = 0; i < result2.length; i++) {
			System.out.println(result2[i]);
		}
	}

	public static int[] solution(String[] id_list, String[] report, int k) {

		StringTokenizer st = null;

		boolean[][] check = new boolean[id_list.length][id_list.length]; // 신고체크배열
		int[] reportCnt = new int[id_list.length]; // 신고당한 횟수
		int[] answer = new int[id_list.length]; // 결과 메일 담을 배열
		String[] repList = new String[id_list.length]; // 해당 인덱스의 회원이 누구를 신고했는가 저장하는 배열

		for (int i = 0; i < repList.length; i++) {
			repList[i] = "";
		}

		for (int i = 0; i < report.length; i++) {
			st = new StringTokenizer(report[i]);
			String att = st.nextToken(); // 신고한 사람
			String def = st.nextToken(); // 신고당한사람

			for (int j = 0; j < id_list.length; j++) {
				if (att.equals(id_list[j])) {
					repList[j] += " " + def;

				}

				if (def.equals(id_list[j])) {
					reportCnt[j]++;
				}
			}

		}

		for (int i = 0; i < reportCnt.length; i++) {
			if (reportCnt[i] >= k) {
				for (int j = 0; j < repList.length; j++) {
					if (repList[j].contains(id_list[i])) {
						answer[j]++;
					}
				}
			}
		}

		return answer;

	}

	public static int[] solution2(String[] id_list, String[] report, int k) {

		StringTokenizer st = null;

		boolean[][] check = new boolean[id_list.length][id_list.length]; // 신고체크배열
		int[] answer = new int[id_list.length]; // 결과 메일 담을 배열
		int[] reportCnt = new int[id_list.length]; // 신고당한 횟수
		for (int i = 0; i < report.length; i++) {
			st = new StringTokenizer(report[i]);
			String att = st.nextToken(); // 신고한 사람
			String def = st.nextToken(); // 신고당한사람
			int attNum = -1;
			int defNum = -1;

			for (int j = 0; j < id_list.length; j++) {
				if (att.equals(id_list[j])) { // 신고한 사람 행 찾음
					attNum = j;
				}
				if (def.equals(id_list[j])) { // 신고당한 사람 행 찾음
					defNum = j;
				}
				if (attNum != -1 && defNum != -1) { // 둘다 찾았으면
					break;
				}
			}

			check[attNum][defNum] = true;
		}

		for (int i = 0; i < check.length; i++) {
			for (int j = 0; j < check[i].length; j++) {
				if (check[j][i]) {
					reportCnt[i]++;
				}
			}
		}

		for (int t = 0; t < reportCnt.length; t++) {
			if (reportCnt[t] >= k) {
				for (int i = 0; i < check.length; i++) {
					if (check[i][t]) {
						answer[i]++;
					}
				}

			}
		}

		return answer;

	}
}

// 이용자의 ID가 담긴 문자열 배열 id_list
// 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report
// 정지 기준이 되는 신고 횟수 k

// 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return

// 신고자가 누굴 신고했는지 기록해놔야한다

// 1. 몇번 신고당했는지 카운트도 올리고
// 2. 해당 신고자가 누굴 신고했는지 String으로 +도 해줘야함
// 단 중복신고X
