package kakao_blind_2022;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution3 {

	public static void main(String[] args) {
//		int[] fees = { 180, 5000, 10, 600 };
//		String[] records = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
//				"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };

		int[] fees = { 120, 0, 60, 591 };
		String[] records = { "16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN" };

		int[] test = solution(fees, records);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}

	}

	public static int[] solution(int[] fees, String[] records) {
		StringTokenizer st = null;
		StringTokenizer st2 = null;

		Queue<info> queue = new LinkedList<info>();
		Queue<int[]> result = new LinkedList<int[]>(); // queue를 처음 돌고 나서 결과값 저장 큐

		// 중복합침 + 출차정보 없는 정보들 통합
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 2차원 배열의 첫 번째 원소를 기준으로 오름차순 정렬한다.
				return Integer.compare(o1[0], o2[0]);
			}
		});

		for (int i = 0; i < records.length; i++) {
			st = new StringTokenizer(records[i]);
			st2 = new StringTokenizer(st.nextToken(), ":");
			int hour = Integer.parseInt(st2.nextToken());
			int minute = Integer.parseInt(st2.nextToken());
			int number = Integer.parseInt(st.nextToken());
			String type = st.nextToken();

			if (type.equals("IN")) { // 진입
				queue.offer(new info(hour, minute, number));
			} else { // 출차
				// 큐를 다 뒤져서 해당 번호의 차를 찾는다
				while (true) {
					info temp = queue.poll();
					if (temp.num == number) { // 찾던 차다
						// 여기서는 분 만 계산
						int diff = 0;
						if (minute < temp.m) { // 들어온 분 이 더 크다면
							diff = (hour - temp.h - 1) * 60 + (minute - temp.m + 60);
						} else {
							diff = (hour - temp.h) * 60 + (minute - temp.m);
						}

						result.offer(new int[] { number, diff });
						break;

					} else { // 찾는 차가 아니다
						queue.offer(temp);
					}

				}
			}
		} // records 종료
		
		while (queue.size() != 0) {
			info temp = queue.poll();
			
			result.offer(new int[] { temp.num, (23 - temp.h) * 60 + 59 - temp.m });
			
		} // 나가는 시간이 없는 애들 넣어주자

		for (int i = 0, s = result.size(); i < s; i++) {
			if (result.size() == 0) {
				break;
			}
			int[] temp = result.poll();
			for (int j = 0, s2 = result.size(); j < s2; j++) {
				if (result.peek()[0] == temp[0]) {
					int[] temp2 = result.poll();
					temp[1] += temp2[1];
				} else {
					result.offer(result.poll());
				}
			} // 비교 종료
			pq.offer(temp);
		}


		int[] answer = new int[pq.size()];
		for (int i = 0; i < answer.length; i++) {
			int[] temp = pq.poll();

			// 기본시간 기본요금 추가시간 추가시간당추가요금
			if (temp[1] <= fees[0]) { // 기본요금
				answer[i] = fees[1];
			} else { // 추가요금
				if ((temp[1] - fees[0]) % fees[2] == 0) {

					answer[i] = fees[1] + ((temp[1] - fees[0]) / fees[2]) * fees[3];
				} else {
					answer[i] = fees[1] + ((temp[1] - fees[0]) / fees[2] + 1) * fees[3];

				}

			}

		}

		return answer;
	}

	public static class info {
		int h; // 출입 시
		int m; // 출입 분
		int num; // 차량번호

		public info(int hour, int minute, int number) {
			this.h = hour;
			this.m = minute;
			this.num = number;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return super.toString();
		}

	}
}

// 주차 요금을 나타내는 정수 배열 fees
// 자동차의 입/출차 내역을 나타내는 문자열 배열 records
// 차량 번호가 작은 자동차부터 청구할 주차 요금을 차례대로 정수 배열에 담아서 return

// info 객체를 생성해서 처리
// 큐에 in정보를 넣고 out정보가 올때마다 큐를 돌려서 해당 번호를 찾아라