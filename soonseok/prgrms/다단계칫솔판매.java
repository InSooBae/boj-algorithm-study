import java.util.*;

/*
    1 ≤ seller 길이 ≤ 100,000
    1 ≤ enroll 길이 ≤ 10,000
        -> 최악의 경우 depth 10000의 tree를 순회해야함, 즉 tree방식 x
        -> 하지만 칫솔 판매량이 한번에 100번, 100원이므로 10000원이 최대임
        -> 1 미만의 상납금은 제거하므로, 10%씩 쪼개면 최대 5번까지만 depth가 갈 수 있음
            -> 전파를 멈추면 됨
*/

class Solution {
    
    static int[] parents;
    static int[] answers;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int N = enroll.length; // 총 사람 수(root 제외)
        parents = new int[N + 1];
        answers = new int[N + 1];
        
        HashMap<String, Integer> map = new HashMap<>(); // 이름-idx 저장용 map
        map.put("center", 0); // 0번 idx는 민호
        
        for(int i = 0; i < N; i++) {
            parents[i + 1] = (i + 1); // 자기가 자신의 부모로 초기값 설정
            map.put(enroll[i], i + 1); // map에 이름-idx 저장
        }
        
        for(int i = 0; i < N; i++) { // 추천인 기록
            if("-".equals(referral[i])) {
                parents[i+1] = 0; // 추천인이 center(민호)
                continue;
            }
            
            parents[i+1] = map.get(referral[i]); // 추천인 idx로 부모 기록
        }
        
        
        for(int i = 0; i < seller.length; i++) {
            find(amount[i] * 100, map.get(seller[i]));
        }
        
        
        // 정답 배열에 복사
        int[] answer = new int[N];
        
        for(int i = 0; i < N; i++) {
            answer[i] = answers[i+1]; // 민호 제외하고 복사
        }
        
        return answer;
    } // solution()
    
    /**
        칫솔 총 판매액, 판매자 idx
    */
    public void find(int money, int idx) {
        
        // 기저조건
        // 상납할 사람이 없는 경우(root) & 상납금이 없는 경우
        if(parents[idx] == idx || money / 10 == 0) {
            answers[idx] += money; // 돈 모두 자신이 가지고 종료
            return;
        }
        
        int giveMoney = money / 10;
        int myMoney = money - giveMoney;
        
        // 내 금액 정산
        answers[idx] += myMoney;
        
        // 상납금도 판매액과 동일하게 재귀 계산
        find(giveMoney, parents[idx]);
        
    } // find()
    
}
