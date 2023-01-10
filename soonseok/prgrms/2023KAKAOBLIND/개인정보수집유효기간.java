import java.util.*;

class Solution {
   
    int todayNumber;
    
    int[] term = new int['Z' + 1]; // A~Z 안에 약관 범위 저장
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        List<Integer> res = new ArrayList<>();
        
        // 1. 오늘의 날짜, 약관 정보 가공
        init(today, terms);
        
        // 2. 각 개인정보별 검증
        for(int i = 0; i < privacies.length; i++) {
            String[] temp = privacies[i].split(" |\\."); // " " | "." 두개의 구분자
            
            int y = Integer.parseInt(temp[0]);
            int m = Integer.parseInt(temp[1]);
            int d = Integer.parseInt(temp[2]);
            char c = temp[3].charAt(0);
            
            int expiredNumber = (y * 28 * 12) + (m * 28) + d;
            expiredNumber += (term[c] * 28) - 1; // 만료일을 계산
            
            if(todayNumber > expiredNumber) res.add(i+1);
            
            
        }
        
        int size = res.size();
        int[] answer = new int[size];
        for(int i = 0; i < size; i++) {
            answer[i] = res.get(i);
        }
        return answer;
        
    } // solution()
    
    public void init(String today, String[] terms) {
        String[] t = today.split("\\."); // "." 은 정규식 예약어이다.
        int ty = Integer.parseInt(t[0]);
        int tm = Integer.parseInt(t[1]);
        int td = Integer.parseInt(t[2]);
        todayNumber = (ty * 28 * 12) + (tm * 28) + td; // 숫자로 변환
        
        for(int i = 0; i < terms.length; i++) {
            String[] s = terms[i].split(" ");
            char c = s[0].charAt(0);
            int period = Integer.parseInt(s[1]);
            
            term[c] = period;
        }
        
    } // init()
    
    
    
} // class Solution