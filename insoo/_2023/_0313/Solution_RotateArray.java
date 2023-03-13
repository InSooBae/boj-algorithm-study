package _2023._0313;

import java.util.Arrays;

public class Solution_RotateArray {
    public static void main(String[] args) {
        new Solution_RotateArray().rotate(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27}, 38);
    }

//    public void rotate(int[] nums, int k) {
//        k = (k % nums.length);
//        if (k == 0 || nums.length == 1) return;
//        int loopCnt = (nums.length / k);
//
//        int point = k;
//
//        for (int i = 0; i < loopCnt; i++) {
//            int j = 0;
//            int dest = 0;
//            for (j = nums.length - k; j < nums.length; j++) {
//                dest = (j+point) % nums.length;
//                swap(j,dest,nums);
//            }
////            if (i != 0) {
////                j--;
////                while (dest++ < j) {
////                    swap(j, dest, nums);
////                }
////            }
//            point += k;
//        }
//
//        System.out.println(Arrays.toString(nums));
//    }
//
//    private void swap(int src, int dest, int[] nums) {
//        int temp = nums[dest];
//        nums[dest] = nums[src];
//        nums[src] = temp;
//    }

    public void rotate(int[] nums, int k) {
        k = (k % nums.length);
        if (k == 0 || nums.length == 1) return;

        reverse(0,nums.length - 1,nums);
        reverse(0,k - 1, nums);
        reverse(k,nums.length - 1, nums);
        System.out.println(Arrays.toString(nums));
    }

    private void reverse(int src, int dest, int[] nums) {
        while (src < dest) {
            int temp = nums[dest];
            nums[dest] = nums[src];
            nums[src] = temp;
            src++;
            dest--;
        }

    }
}
