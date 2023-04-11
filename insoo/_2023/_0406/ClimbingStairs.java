package _2023._0406;

public class ClimbingStairs {
    public static void main(String[] args) {
        int i = new ClimbingStairs().climbStairs(10);
        System.out.println(i);
    }
    public int climbStairs(int n) {
        int[] arr = new int[n + 1];


        return dfs(n, arr);
    }

    public int dfs(int x,int[] arr) {
        if (x == 1 || x == 2) {
            return x;
        } else if (arr[x] != 0) return arr[x];

        return arr[x] = dfs(x - 1, arr) + dfs(x - 2, arr);
    }
}
