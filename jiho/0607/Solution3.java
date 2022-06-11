package kakao_2021;

import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution3 {

	public static void main(String[] args) {
		String[] temp = { "D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z" };
		String[] temp2 = { "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		

		System.out.println(solution(8, 2, temp));
		System.out.println(solution(8, 2, temp2));
		System.out.println(solution2(8, 2, temp));
		System.out.println(solution2(8, 2, temp2));
	}

	// n : 처음 표의 행 개수
	// k : 처음 선택된 행 위치
	// cmd : 수행 명령어 배열
	public static String solution(int n, int k, String[] cmd) {
		String answer = "";
		StringBuilder sb = new StringBuilder();
		int cursor = k;
		// 행 정보를 boolean 배열 통해서 사용
		boolean[] hang = new boolean[n];
		Arrays.fill(hang, true);

		// 삭제 스택 : 삭제 당시 인덱스
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < cmd.length; i++) {
			StringTokenizer st = new StringTokenizer(cmd[i]);
			switch (st.nextToken()) {
			case "U":
				int upper = Integer.parseInt(st.nextToken());
				for (int j = cursor - 1; j > 0; j--) {
					if (hang[j]) { // 행이 있다
						upper--;
					}
					if (upper == 0) {
						cursor = j;
						break;
					}
				}

				break;
			case "D":
				int downer = Integer.parseInt(st.nextToken());
				for (int j = cursor + 1; j < hang.length; j++) {
					if (hang[j]) {
						downer--;
					}
					if (downer == 0) {
						cursor = j;
						break;
					}
				}

				break;
			case "C":
				hang[cursor] = false;
				stack.push(cursor);
				boolean flag = false;
				for (int j = cursor + 1; j < hang.length; j++) {
					if (hang[j]) {
						cursor = j;
						flag = true;
						break;
					}
				}

				if (!flag) {
					cursor--;
				}

				break;
			case "Z":
				int recover = stack.pop();
				hang[recover] = true;
				break;

			}

		} // 명령어 끝

		for (int i = 0; i < hang.length; i++) {
			if (hang[i]) {
				sb.append("O");
			} else {
				sb.append("X");
			}
		}

		answer = sb.toString();
		return answer;
	}
	
	public static String solution2(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack<>();
        int size = n;
        for(String query : cmd){
            char key = query.charAt(0);
            switch(key){
                case 'U':
                    k-= Integer.valueOf(query.substring(2));
                    break;
                case 'D':
                    k+= Integer.valueOf(query.substring(2));
                    break;
                case 'C':
                    stack.push(k);
                    size--;
                    if(k==size) k--;
                    break;
                case 'Z':
                    int val = stack.pop();
                    if(val<=k) k++;
                    size++;
                    break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append('O');
        }
        while(!stack.isEmpty()) {
            sb.insert(stack.pop().intValue(), 'X');
        }
        return sb.toString();
    }
}

// U위 D아래
// C : 현재 행 삭제 후 바로 아래 행 선택 (마지막 행 삭제했을 경우 바로 윗 행 선택)
// Z : 최근 삭제된 행 복 / 현재 선택된 행 안바뀜
// 처음표랑 비교해서 삭제된 행은 X, 그대로인 행은 O로 표시해서 문자열로 리턴 

// C - Z 는 한 쌍
// Z로 복구해도 커서 위치는 그대로 => 커서 이동은 계속 해줘야한다
