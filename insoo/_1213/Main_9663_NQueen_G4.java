package _1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_NQueen_G4 {
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] isUsedCol = new boolean[N];
        boolean[] isUsedLDiagonal = new boolean[2 * N - 1];
        boolean[] isUsedRDiagonal = new boolean[2 * N - 1];

        nQueen(0, N, isUsedCol, isUsedLDiagonal, isUsedRDiagonal);
        System.out.println(cnt);
    } // end of main


    static void nQueen(int rowNum, int N, boolean[] isUsedCol, boolean[] isUsedLDiagonal, boolean[] isUsedRDiagonal) {

        if (rowNum == N) {
            cnt++;
            return;
        }


        for (int i = 0; i < N; i++) {
            if (isUsedCol[i] || isUsedLDiagonal[rowNum + i] || isUsedRDiagonal[N - 1 + i - rowNum]) continue;
            isUsedCol[i] = true;
            isUsedLDiagonal[rowNum + i] = true;
            isUsedRDiagonal[N - 1 + i - rowNum] = true;
            nQueen(rowNum + 1, N, isUsedCol, isUsedLDiagonal, isUsedRDiagonal);
            isUsedCol[i] = false;
            isUsedLDiagonal[rowNum + i] = false;
            isUsedRDiagonal[N - 1 + i - rowNum] = false;
        }
    }

}
