package _2023._0322;

public class Solution_MinimumScoreOfAPathBetweenTwoCities {

    public static void main(String[] args) {
        int i = new Solution_MinimumScoreOfAPathBetweenTwoCities().minScore(14, new int[][]{{12, 7, 2151}, {7, 2, 7116}, {11, 14, 8450}, {11, 2, 9954}, {1, 11, 3307}, {10, 7, 3561}, {10, 1, 4986}, {11, 7, 7674}, {14, 2, 1764}, {11, 12, 6608}, {14, 7, 1070}, {9, 8, 2287}, {14, 12, 6559}, {1, 2, 1450}, {2, 12, 9165}});
        System.out.println(i);
    }
    static int[] nodes;

    public int minScore(int n, int[][] roads) {
        makeSet(n);
        int min = Integer.MAX_VALUE;
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int val = road[2];
            union(a,b);
        }

        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int val = road[2];
            a = findSet(a);
            b = findSet(b);
            if (a == 1 || b == 1)
                if (min > val) min = val;
        }

        for (int node : nodes) {
            System.out.println(node);
        }

        return min;
    }

    private static void makeSet(int n) {
        nodes = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i]=i;
        }
    }

    private static int findSet(int x) {
        if (nodes[x] == x) return nodes[x];
        return nodes[x] = findSet(nodes[x]);
    }

    private static void union(int a, int b)  {
        a = findSet(a);
        b = findSet(b);

        if (a < b) {
            nodes[b] = a;
        } else if (a > b) {
            nodes[a] = b;
        }
    }
}
