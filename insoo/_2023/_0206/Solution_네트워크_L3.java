package _2023._0206;

import java.util.HashSet;

public class Solution_네트워크_L3 {


    public static void main(String[] args) {
        int solution = new Solution_네트워크_L3().solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {
                0, 0, 1}});

        System.out.println(solution);
    }

    static int[] parent;
    /*
           1 1 0
           1 1 0
           0 0 1
     */
    public int solution(int n, int[][] computers) {
        int answer = 0;

        HashSet<Integer> hashSet = new HashSet<>();
        parent = makeSet(n);

        for (int i = 0; i < n; i++) {
            dfs(computers, i);
        }

        for (int i = 0; i < parent.length; i++) {
            hashSet.add(parent[i]);
        }


        return answer = hashSet.size();
    }

    private void dfs(int[][] computers, int start) {
        for (int i = 0; i < computers[start].length; i++) {
            // 자기 자신 가르키면 안됨
            if (i != start && computers[start][i] != 0) {
                // 아직 합쳐진다면 다음 것도 확인해보러 감
                if (union(start, i)) dfs(computers, i);
            }
        }
    }

    private int[] makeSet(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[a] = b;
            return true;
        }

        return false;
    }
}
