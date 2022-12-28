
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
83
84
85
86
87
88
89
90
91
92
93
94
95
96
97
98
99
100
101
102
103
104
105
106
107
108
109
110
111
112
113
114
115
116
import java.util.*;

/*
    오늘의 주차 현황 : HashMap< 차량번호(int), 주차정보(Parking) >  -> 오늘 입/출차 기록이 있는 차를 모두 저장

    최종적으로 차량번호 오름차순으로 정리해야 함
*/

class Solution {
    public static int[] solution(int[] fees, String[] records) {
        // 1. 차량번호 오름차순이므로, SortedMap->TreeMap을 이용하여 key를 정렬하며 정보 처리
        SortedMap<Integer, Parking> map = new TreeMap<>();

        // 2. 가장 먼저 records 배열을 이용하여, 각 차량의 하루간 총 주차 시간 체크
        for(int i = 0; i < records.length; i++) {

            String[] record = records[i].split(" ");

            int carNum = Integer.parseInt(record[1]); // 차량 번호
            int hour = Integer.parseInt(record[0].substring(0,2)); // 시각
            int min = Integer.parseInt(record[0].substring(3,5));

            // 입차인 경우
            if("IN".equals(record[2])) {
                // 처음 입차한 경우
                if(!map.containsKey(carNum)) {
                    map.put(carNum, new Parking(hour, min));
                }
                // 이미 입차했다가 출차한 적이 있는 경우
                else {
                    map.get(carNum).in(hour, min);
                }
            }

            // 출차인 경우
            else {
                map.get(carNum).out(hour, min);
            }
        } // for

        // 3. 모든 차량의 주차 시간을 계산, 이제 시간을 근거로 금액을 계산하여 결과 반환
        List<Integer> ans = new ArrayList<>();

        for(Map.Entry<Integer, Parking> entry : map.entrySet()) {
            // 아직도 출차가 안된 경우, 주차 시간을 재 계산
            if(entry.getValue().isParked) {
                entry.getValue().out(23, 59);
            }

            System.out.println(entry.getKey() + "->" + entry.getValue().totalParkedTime);

            int fee = fees[1]; // 차량별 기본 요금 설정

            // 기본 시간을 초과한 경우 요금 재 계산
            if(entry.getValue().totalParkedTime > fees[0]) {
                int tempTime = entry.getValue().totalParkedTime - fees[0];

                // 단위 시간마다 단위 요금을 청구하므로, 나누기 계산
                // 단위 시간으로 나누어 떨어지지 않으면 올림
                fee += (int)Math.ceil((float)tempTime / fees[2]) * fees[3]; 

            }

            ans.add(fee); // 계산 완료된 주차 금액을 저장

        } // for


        // 4. 정답 만들어 반환

        int[] answer = new int[ans.size()];

        for(int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;

    } // solution()

    /**
        isParked : 현재 주차장 내부에 주차됐는지
        startHour : 주차 시작 시간
        startMin : 시작 분
        totalParkedTime : 주차 누적 시간

        in : 2회차 입차 시 변수 변경
        out : 출차 시 누적 시간 계산 메소드
    */
    public static class Parking {
        boolean isParked = false;
        int startHour;
        int startMin;
        int totalParkedTime = 0;

        public Parking(int sH, int sM) {
            this.isParked = true; // 주차 시작
            this.startHour = sH;
            this.startMin = sM;
        }

        public void in(int sH, int sM) { // 2회차 주차부터 사용
            this.isParked = true; // 주차 시작
            this.startHour = sH;
            this.startMin = sM;
        }

        public void out(int eH, int eM) {
            this.isParked = false; // 주차 종료
            this.totalParkedTime += (eH - this.startHour) * 60 + (eM - this.startMin); // 총 주차 시간 계산
        }

    } // Parking

}
