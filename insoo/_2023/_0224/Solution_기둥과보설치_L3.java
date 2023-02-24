package _2023._0224;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_기둥과보설치_L3 {
    public static void main(String[] args) {
        int[][] solution = new Solution_기둥과보설치_L3().solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}});
        for (int[] ints : solution) {
            System.out.print(Arrays.toString(ints) + ",");
        }
    }

    static class Pos implements Comparable<Pos> {
        int x;
        int y;
        int material;

        public Pos(int x, int y, int material) {
            this.x = x;
            this.y = y;
            this.material = material;
        }

        @Override
        public int compareTo(Pos o) {
            return this.x != o.x ? this.x - o.x : this.y - o.y;
        }
    }

    private static int[] dx = {
            1, 1, 0, -1, -1, -1, 0, 1
    };
    private static int[] dy = {
            0, 1, 1, 1, 0, -1, -1, -1
    };


    private static final int PILLAR = 1;
    private static final int BEAM = 2;

    private static final int DELETE = 0;
    private static final int INSTALL = 1;

    /*
        TODO:1.기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
        TODO:2.보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
     */
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        List<Pos> res = new ArrayList<>();

        int[][][] mat = new int[n + 1][n + 1][3];

        for (int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int material = build[2] == 0 ? PILLAR : BEAM;
            int oper = build[3];

            if (oper == INSTALL) {

                if (material == PILLAR) {
                    if (canInstallPillar(x, y, mat)) {
                        mat[y][x][material] = material;
                    }
                } else { // 보 설치
                    if (canInstallBeam(x, y, mat)) {
                        mat[y][x][material] = material;
                    }
                }

            } else {
                mat[y][x][material] = 0;
                boolean canDelete = true;

                br :for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat.length; j++) {
                        if (mat[i][j][PILLAR] == PILLAR) {
                            if (!canInstallPillar(j, i, mat)) canDelete = false;
                        }
                        if (mat[i][j][BEAM] == BEAM) {
                            if (!canInstallBeam(j, i, mat)) canDelete = false;
                        }
                        if (!canDelete) break br;
                    }
                }
//                for (int i = 0; i < dx.length; i++) {
//                    int nx = dx[i] + x;
//                    int ny = dy[i] + y;
//
//                    if (nx < 0 || nx > n || ny < 0 || ny > n) continue;
//                    if (mat[ny][nx][PILLAR] == PILLAR) {
//                        if (!canInstallPillar(nx, ny, mat)) canDelete = false;
//                    }
//                    if (mat[ny][nx][BEAM] == BEAM) {
//                        if (!canInstallBeam(nx, ny, mat)) canDelete = false;
//                    }
//
//                    if (!canDelete) break;
//                }

                if (!canDelete) mat[y][x][material] = material;

            }
        }


        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (mat[i][j][PILLAR] == PILLAR) res.add(new Pos(j, i, 0));
                if (mat[i][j][BEAM] == BEAM) res.add(new Pos(j, i, 1));
//                System.out.print(mat[i][j] + " ");
            }
//            System.out.println();
        }

        Collections.sort(res);

        answer = new int[res.size()][3];

        for (int i = 0; i < answer.length; i++) {
            Pos pos = res.get(i);
            answer[i][0] = pos.x;
            answer[i][1] = pos.y;
            answer[i][2] = pos.material;
        }

        return answer;
    }

    private boolean canInstallBeam(int x, int y, int[][][] mat) {
        if (y == 0) return false;

        if (mat[y - 1][x][PILLAR] == PILLAR || mat[y - 1][x + 1][PILLAR] == PILLAR) return true;

        if (x > 0 && mat[y][x - 1][BEAM] == BEAM && mat[y][x + 1][BEAM] == BEAM) return true;

        return false;
    }

    private boolean canInstallPillar(int x, int y, int[][][] mat) {
        // 바닥
        if (y == 0) return true;

        if (x > 0 && mat[y][x - 1][BEAM] == BEAM) return true;

        if (mat[y][x][BEAM] == BEAM) return true;

        if (mat[y - 1][x][PILLAR] == PILLAR) return true;

        return false;
    }

}
