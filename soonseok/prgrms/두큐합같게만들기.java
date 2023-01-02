
/*
https://school.programmers.co.kr/learn/courses/30/lessons/118667
*/

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        int answer = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long totalSum = 0;
        long queue1_sum = 0;
        long queue2_sum = 0;

        // 1. 모든 수의 총 합 구하면서 큐 만들기
        for(int i = 0; i < queue1.length; i++) {

            totalSum += queue1[i] + queue2[i];

            q1.add(queue1[i]);
            queue1_sum += queue1[i];

            q2.add(queue2[i]);
            queue2_sum += queue2[i];
        }

        long target = totalSum / 2;

        if(target*2 != totalSum) return -1; // 수가 나누어지지 않으면 불가능

        int[] check = new int[queue1.length * 2]; // 체크용 배열
        for(int i = 0; i < queue1.length; i++) { // 어떤 수가 target 이상이면 불가능
            if(queue1[i] > target) return -1;
            if(queue2[i] > target) return -1;

            check[i] = queue1[i];
            check[i + queue1.length] = queue2[i];
        }

        // 10 -> 3 3 3 1 같은 경우에도 불가능


        // 2. 큐의 누적합을 계산
        while(queue1_sum != queue2_sum) {
            if(answer > queue1.length * 3) return -1; // 배열 2배 이상으로 작업했다면 불가능

            if(queue1_sum < target) {
                // queue1의 누적합이 목표치보다 작은 경우
                queue2_sum -= q2.peek();
                queue1_sum += q2.peek();
                q1.add(q2.poll());
                answer++;
            }

            else if(queue1_sum > target) {
                // queue1의 누적합이 목표치보다 큰 경우
                queue2_sum += q1.peek();
                queue1_sum -= q1.peek();
                q2.add(q1.poll());   
                answer++;
            }
        } // while


        return answer;
    }
}
