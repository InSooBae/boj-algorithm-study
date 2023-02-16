package _2023._0216;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Solution_N진수게임_L2 {
    public static void main(String[] args) {
        String solution = new Solution_N진수게임_L2().solution(2, 4, 2, 1);
        System.out.println(solution);
    }

    public String solution(int n, int t, int m, int p) {
        String answer = "";

        answer = makeN(n, t, m, p);

        return answer;
    }

    private String makeN(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();
        queue.add(0);
        int turn = t * m;
        int i = 1;
        while (i < turn) {
            while (num / n != 0) {
                int mod = num % n;

                stack.push(mod);
                num /= n;
            }
            stack.add(num);
            i++;
            num = i;

            while (!stack.isEmpty()) {queue.add(stack.pop());}

        }

        int order = 1;
        for (Integer integer : queue) {
            if (order++ == p) {
                if (integer >= 10) {
                    char c = (char) (integer - 10 + 'A');
                    sb.append(c);
                } else {
                    sb.append(integer);
                }
                if (sb.length() == t) break;
            }
            if (order > m) order = 1;
        }

        return sb.toString();
    }
}
