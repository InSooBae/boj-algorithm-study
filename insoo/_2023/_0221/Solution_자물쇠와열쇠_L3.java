package _2023._0221;

public class Solution_자물쇠와열쇠_L3 {
    public static void main(String[] args) {
        boolean solution = new Solution_자물쇠와열쇠_L3().solution(new int[][]{{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
        System.out.println(solution);
    }

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;

        boolean[][] keyMat = new boolean[key.length][key[0].length];
        int[][] lockMat = new int[lock.length + (key.length * 2) - 2][lock.length + (key.length * 2) - 2];
        int lockCnt = 0;
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                if (key[i][j] == 1) keyMat[i][j] = true;
            }
        }

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                if (lock[i][j] == 0) {
                    lockCnt++;
                    lockMat[i + key.length - 1][j + key.length - 1] = 1;
                } else lockMat[i + key.length - 1][j + key.length - 1] = 2;
            }
        }

//        for (int i = 0; i < lockMat.length; i++) {
//            for (int j = 0; j < lockMat.length; j++) {
//                System.out.print(lockMat[i][j] ? "* " : ". ");
//            }
//            System.out.println();
//        }

        for (int rotate = 0; rotate < 3; rotate++) {
            if (checkIsKeyLock(keyMat, lockMat, lockCnt)) {
                answer = true;
                break;
            } else {
                keyMat = rotateMat(keyMat);
                answer = false;
            }
        }

        if (!answer) answer = checkIsKeyLock(keyMat, lockMat, lockCnt);
        return answer;
    }

    private boolean[][] rotateMat(boolean[][] keyMat) {
        boolean[][] temp = new boolean[keyMat.length][keyMat.length];

        for (int x = keyMat.length - 1; x >= 0; x--) {
            for (int y = 0; y < keyMat.length; y++) {
                temp[y][x] = keyMat[keyMat.length - x - 1][y];
            }
        }


        return temp;
    }

    private boolean checkIsKeyLock(boolean[][] keyMat, int[][] lockMat, int lockCnt) {

//        for (int i = 0; i < keyMat.length; i++) {
//            for (int j = 0; j < keyMat.length; j++) {
//                System.out.print(keyMat[i][j] ? "* ":". ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("======================");

        for (int i = 0; i < lockMat.length - keyMat.length + 1; i++) {
            for (int j = 0; j < lockMat.length - keyMat.length + 1; j++) {

                int check = 0;
                boolean isFit = true;
                fit :for (int k = 0; k < keyMat.length; k++) {
                    for (int l = 0; l < keyMat.length; l++) {
                        if (lockMat[i + k][j + l] == 1 && keyMat[k][l]) check++;
                        else if (lockMat[i + k][j + l] == 2 && keyMat[k][l]) isFit = false;

                        if (!isFit) break fit;
                    }
                }
                if (check == lockCnt) return true;
            }
        }

        return false;
    }

}
