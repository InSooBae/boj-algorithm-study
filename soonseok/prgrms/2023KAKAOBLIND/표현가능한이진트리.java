import java.util.*;

/*
    10^15 까지의 십진수를 받아서
    이진수로 변환하고
    트리 요건에 맞는지 확인

*/

class Solution {

    // 가장 가까운 성립하는 포화 이진 트리 길이 (2^n - 1) 
    // 최대 50길이의 이진수까지 나올 수 있으므로, 63 길이의 트리가 최대 크기
    int[] treeSize = {1, 3, 7, 15, 31, 63};

    int N;
    String[] binaries;
    int[] answer;

    public int[] solution(long[] numbers) {

        init(numbers); // 이진수 문자열 생성

        for(int i = 0; i < N; i++) { // 각 이진수 문자열에 대해서 반복

            if(binaries[i].length() == 1) { // root만 있는 tree인 경우 처리
                int res = binaries[i].charAt(0) - '0';
                answer[i] = res; // root가 0인 경우 0, 1인 경우 1
            }

            if(treeCheck(binaries[i])) answer[i] = 1; // 트리 구성이 가능한 경우 1
        }

        return answer;

    } // solution()

    // 재귀 방식으로 이진수를 분할 검사
    public boolean treeCheck(String binary) {

        int size = binary.length(); // 현재 트리의 길이
        int rootIdx = size / 2; // root의 위치
        int root = binary.charAt(rootIdx) - '0'; // 현재 트리의 root 정보

        if(size == 1) return true; // leaf-node라면 항상 true

        // 만약 root가 0인데, 자식에 1이 있다면, 즉시 false
        if(root == 0 && binary.contains("1")) return false;

        // root 와 자식이 전부 0인 경우에는, 통째로 가상 노드이므로 true
        if(root == 0 && !binary.contains("1")) return true;

        String leftBinary = binary.substring(0, rootIdx); // 왼쪽 서브트리
        String rightBinary = binary.substring(rootIdx + 1, size); // 오른쪽 서브트리

        boolean left = treeCheck(leftBinary);
        boolean right = treeCheck(rightBinary);

        if(left && right) return true; // 서브트리들이 모두 조건을 만족하는 경우
        else return false;

    } // treeCheck()

    public void init(long[] numbers) {

        N = numbers.length;

        binaries = new String[N];
        answer = new int[N];

        for(int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();

            String str = Long.toBinaryString(numbers[i]); // 우선 이진수로 변환하고

            int size = str.length(); // 해당 이진수의 길이를 체크

            for(int j = 0; j < 6; j++) {
                if(size > treeSize[j]) continue; // 트리 길이보다 크면, 다음 사이즈 체크

                if(size == treeSize[j]) break; // 트리 길이와 일치하면 그냥 그대로 사용

                if(size < treeSize[j]) { // 트리 길이보다 작으면
                    int zeros = treeSize[j] - size; // 모자란만큼 앞에 0을 채워서 사용
                    for(int k = 0; k < zeros; k++) sb.append(0);
                    break;
                }
            }

            sb.append(str);

            binaries[i] = sb.toString();
        }

    } // init()

} // class Solution