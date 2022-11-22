package _1122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_6198_옥상정원꾸미기_G5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        long seekRoofTopCnt = 0;

        int[] arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int curPos = 1; curPos <= N; curPos++) {
            int curBuildingHeight = arr[curPos];

            while (stack.size() != 0 && stack.peek() <= curBuildingHeight) {
                stack.pop();
            }

            stack.push(curBuildingHeight);
            seekRoofTopCnt += stack.size() - 1;
        }

        System.out.println(seekRoofTopCnt);
    }
}
