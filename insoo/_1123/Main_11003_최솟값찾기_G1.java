package _1123;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_11003_최솟값찾기_G1 {

    static class Pair {
        private int pos;
        private int num;

        public Pair(int pos, int num) {
            this.pos = pos;
            this.num = num;
        }

        public int getPos() {
            return pos;
        }

        public int getNum() {
            return num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        Deque<Pair> deque = new LinkedList<>();



        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (!deque.isEmpty() && deque.peekLast().num >= num) {
                deque.pollLast();
            }
            deque.addLast(new Pair(i, num));
            if (deque.peekFirst().pos < i - L + 1) {
                deque.pollFirst();
            }
            sb.append(deque.peekFirst().num).append(" ");
        }

        System.out.println(sb);
    }
}
