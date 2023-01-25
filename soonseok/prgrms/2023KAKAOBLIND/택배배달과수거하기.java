import java.util.*;

/*
    한 번 출발 시 마다, 가장 먼 배달지부터 delivery와 pickup을 cap 만큼 빼면서,
    
    가장 먼 거리 * 2를 더해주면 된다
*/

class Solution {
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        // 1. 총 배달 및 수거 개수를 카운팅
        int totalD = 0;
        int totalP = 0;
        for(int i = 0; i < n; i++) {
            totalD += deliveries[i];
            totalP += pickups[i];
        }
        
        
        // 2. 배달 및 수거가 완료되면 종료
        long answer = 0;
        int d = n - 1;
        int p = n - 1;
        
        while(totalD != 0 || totalP != 0) {
            
            // 화물이 남아있으면서 가장 먼 거리를 구한다
            int cD = cap;
            int cP = cap;
            
            int farthest = 0;
            
            // 더 배달이 불가능하거나, 모든 배달이 끝난 경우 종료
            while(cD != 0) {
                
                if(d == -1) break; // 모든 배달이 끝난 경우 종료
                
                if(deliveries[d] == 0) { // 배달할게 없는 집이면 패스
                    d--;
                    continue;
                }
                
                if(deliveries[d] > 0) { // 배달할 수 있는 집인 경우
                    
                    farthest = Math.max(farthest, d); // 최장 거리 갱신
                    
                    if(deliveries[d] > cD) { // 배달이 한번으로 끝낼수 없는 경우
                        deliveries[d] -= cD; // 그 집에 배달 할 만큼 한다
                        totalD -= cD; // 총 배달 수 반영
                        cD = 0; // 더는 배달이 불가능하다
                        // 인덱스 유지
                    }
                    else { // 한번으로 배달이 끝나는 경우
                        cD -= deliveries[d];
                        totalD -= deliveries[d];
                        deliveries[d] = 0;
                        d--; // 인덱스 감소
                    }
                }
                
                
            } // while Delivery
            
            while(cP != 0) {
                
                if(p == -1) break;
                
                if(pickups[p] == 0) {
                    p--;
                    continue;
                }
                
                if(pickups[p] > 0) {
                    
                    farthest = Math.max(farthest, p);
                    
                    if(pickups[p] > cP) {
                        pickups[p] -= cP;
                        totalP -= cP;
                        cP = 0;
                    }
                    else {
                        cP -= pickups[p];
                        totalP -= pickups[p];
                        pickups[p] = 0;
                        p--;
                    }
                }
                
            } // while Pickup
            
            answer += (farthest + 1) * 2; // 왕복 거리를 더해줌

        }
        
        return answer;
        
    } // solution()
    
    
} // class Solution