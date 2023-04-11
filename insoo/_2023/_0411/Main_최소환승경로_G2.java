package _2023._0411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_최소환승경로_G2 {

    private static class Triple implements Comparable<Triple> {
        int line;
        int station;
        int dis;

        public Triple(int line, int station, int dis) {
            this.line = line;
            this.station = station;
            this.dis = dis;
        }

        @Override
        public int compareTo(Triple o) {
            return this.dis - o.dis;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] stationLine = new ArrayList[N + 1];
        ArrayList<Integer>[] lineStation = new ArrayList[L];
        int[] lineVisited = new int[L];
        int[] stationVisited = new int[N + 1];
        Arrays.fill(lineVisited, Integer.MAX_VALUE - 1);
        Arrays.fill(stationVisited, Integer.MAX_VALUE - 1);
        for (int i = 0; i <= N; i++) {
            stationLine[i] = new ArrayList<>();
        }

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            lineStation[i] = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int n = Integer.parseInt(st.nextToken());
                if (n == -1) break;
                stationLine[n].add(i);
                lineStation[i].add(n);
            }
        }

        st = new StringTokenizer(br.readLine());
        int srcStation = Integer.parseInt(st.nextToken());
        int destStation = Integer.parseInt(st.nextToken());

        if (srcStation == destStation) System.out.println(0);
        else System.out.println(bfs(stationLine, lineStation, lineVisited,stationVisited, srcStation, destStation));
    }

    private static int bfs(ArrayList<Integer>[] stationLine, ArrayList<Integer>[] lineStation, int[] lineVisited,int[] stationVisited, int srcStation, int destStation) {
        // 라인 담는 큐
        PriorityQueue<Triple> queue = new PriorityQueue<>();
        boolean isArrived = false;
        int answer = -1;

        for (int line :stationLine[srcStation]) {
            queue.add(new Triple(line, srcStation, 0));
            lineVisited[line] = 0;
        }
        stationVisited[srcStation] = 0;

        while (!queue.isEmpty()) {
            Triple cur = queue.poll();

            for (int nxtStation : lineStation[cur.line]) {
                if (stationVisited[nxtStation] > cur.dis) {
                    if (nxtStation == destStation) {
                        isArrived = true;
                        answer = cur.dis;
                        break;
                    }
                    stationVisited[nxtStation] = cur.dis;
                    queue.add(new Triple(cur.line, cur.station, cur.dis));

                    for (int nxtLine : stationLine[nxtStation]) {

                        if (lineVisited[nxtLine] > cur.dis + 1) {
                            lineVisited[nxtLine] = cur.dis + 1;
                            queue.add(new Triple(nxtLine, nxtStation, lineVisited[nxtLine]));
                        }
                    }
                }
                if (isArrived) break;
            }
        }

        return answer;
    }
}
