import java.util.*;

/*
    2 ≤ 등대 개수 ≤ 100,000

    뱃길은 항상 n-1 개

        -> 등대를 껐다 키는 경우의 수 2^1e5 이므로, 완탐으로는 불가능
        -> 각 등대 번호마다 자기가 연결된 애들을 기록하는 것도 비효율

    1) 각 등대마다 연결된 등대의 개수를 기록하는 int 배열
    2) 각 등대를 켤지 끌지 결정하는 boolean 배열
    3) 간선이 1개인 등대의 반대편에 연결된 등대를 키면서, 각 등대의 간선을 제거해나가는 방법

*/

class Solution {
    public int solution(int n, int[][] lighthouse) {

        if(n == 2) return 1; // 등대가 둘 뿐인 경우 처리

        int[] edges = new int[n + 1]; // 1 idx
        boolean[] lights = new boolean[n + 1]; 

        Deque<int[]> deq = new ArrayDeque<>(); 

        for(int i = 0; i < lighthouse.length; i++) { // 각 등대의 간선 개수를 기록
            int idx1 = lighthouse[i][0];
            int idx2 = lighthouse[i][1];

            edges[idx1]++;
            edges[idx2]++;

            deq.add(lighthouse[i]); // 불이 밝혀지지 않은 뱃길을 기록
        }

        while(!deq.isEmpty()) {
            int idx1 = deq.peek()[0];
            int idx2 = deq.peek()[1];

            if(lights[idx1] || lights[idx2]) { // 뱃길 밝혀졌으면 다음으로

                edges[idx1] --;
                edges[idx2] --;

                deq.poll();
                continue;
            }

            if(edges[idx1] == 1) { // 끄트머리 등대인 경우
                lights[idx2] = true; // 더 안쪽 등대를 켜주고
                edges[idx1] = 0; // 그 등대는 제거
                edges[idx2] --; // 안쪽 등대는 간선이 하나 줄어든다

                deq.poll();
                continue;
            }

            if(edges[idx2] == 1) {
                lights[idx1] = true; 
                edges[idx2] = 0;
                edges[idx1] --;

                deq.poll();
                continue;
            }

            deq.add(deq.poll()); // 순번을 뒤로 보낸다.

        }

        // System.out.println(Arrays.toString(edges));
        // System.out.println(Arrays.toString(lights));


        // 켠 등대 개수 
        int answer = 0;
        for(int i = 0; i < lights.length; i++) {
            if(lights[i]) answer++;
        }

        return answer;
    }
}