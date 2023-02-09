package _2023._0209;

public class Solution_점프와순간이동_L2 {
    public static void main(String[] args) {

    }

    public int solution(int n) {
        int ans = 0;


        while (n != 1) {
            if (n % 2 ==0) {
                n /= 2;
            } else {
                n -= 1;
                ans++;
            }
        }

        return ++ans;
    }
}
