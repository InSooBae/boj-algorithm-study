package _2023._0207;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution_뉴스클러스터링_L2 {
    public static void main(String[] args) {
        int solution = new Solution_뉴스클러스터링_L2().solution("FRANCE", "french");
        System.out.println(solution);
    }

    public int solution(String str1, String str2) {
        int answer = 0;

        int intersectionCnt = 0;
        int unionCnt = 0;

        ArrayList<String> str1Set = new ArrayList<>();
        ArrayList<String> str2Set = new ArrayList<>();

        makeSet(str1, str1Set);
        makeSet(str2, str2Set);

        // for intersection
        boolean[] visited = new boolean[str2Set.size()];
        // for union
        HashMap<String, Integer> str1Cnt = new HashMap<>();
        HashMap<String, Integer> str2Cnt = new HashMap<>();

        // calc intersection cnt
        for (int i = 0; i < str1Set.size(); i++) {
            String str = str1Set.get(i);
            for (int j = 0; j < str2Set.size(); j++) {
                if (!visited[j] && str.equals(str2Set.get(j))) {
                    visited[j] = true;
                    intersectionCnt++;
                    break;
                }
            }
        }

        calcStrCnt(str1Set, str1Cnt);
        calcStrCnt(str2Set, str2Cnt);

        for (String s : str1Cnt.keySet()) {
            Integer str1Num = str1Cnt.get(s);
            if (str2Cnt.containsKey(s)) {
                Integer str2Num = str2Cnt.get(s);
                unionCnt += Math.max(str1Num, str2Num);
            } else {
                unionCnt += str1Num;
            }
        }

        for (String s : str2Cnt.keySet()) {
            if (!str1Cnt.containsKey(s)) {
                unionCnt += str2Cnt.get(s);
            }
        }

//        System.out.println(intersectionCnt);
//        System.out.println(unionCnt);

        if (unionCnt == 0 && intersectionCnt == 0) return 65536;
        double v = Double.valueOf(intersectionCnt) / unionCnt;
        answer = (int) (65536 * v);

//        for (String s : str1Set) {
//            System.out.print(s + " ");
//        }
//
//        System.out.println();
//        for (String s : str2Set) {
//            System.out.print(s + " ");
//        }
//        System.out.println();


        return answer;
    }

    private static void calcStrCnt(ArrayList<String> strSet, HashMap<String, Integer> strCnt) {
        for (String s : strSet) {
            if (strCnt.containsKey(s)) {
                strCnt.put(s, strCnt.get(s) + 1);
            } else {
                strCnt.put(s, 1);
            }
        }
    }

    private static void makeSet(String str, ArrayList<String> strSet) {
        for (int i = 0; i < str.length() - 1; i++) {
            char front = str.charAt(i);
            char back = str.charAt(i + 1);

            //('A' <= front && front <= 'Z' || 'a' <= front && front <= 'z')
            if (Character.isAlphabetic(front) && Character.isAlphabetic(back)) {
                front = Character.toLowerCase(front);
                back = Character.toLowerCase(back);

                String set = String.valueOf(front) + String.valueOf(back);

                strSet.add(set);
            }
        }
    }
}
