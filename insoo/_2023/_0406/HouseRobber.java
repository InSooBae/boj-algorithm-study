package _2023._0406;

import java.util.Arrays;

public class HouseRobber {

    public static void main(String[] args) {
        int rob = new HouseRobber().rob(new int[]{1, 7, 4, 3, 10});
        System.out.println(rob);
    }
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0],nums[1]);
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        memo[0] = nums[0];
        memo[1] = nums[1];


        return Math.max(dfs(nums.length - 1, nums, memo), dfs(nums.length - 2, nums, memo));
    }

    public int dfs(int n, int[] nums, int[] memo) {
        if (n < 0) return 0;
        if (memo[n] != -1) return memo[n];

        return memo[n] = Math.max(dfs(n-2,nums,memo) + nums[n], dfs(n-3,nums,memo) + nums[n]);
    }
}
