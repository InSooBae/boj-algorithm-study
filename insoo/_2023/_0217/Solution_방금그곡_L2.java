package _2023._0217;

import java.util.*;

public class Solution_방금그곡_L2 {
    public static void main(String[] args) {
        new Solution_방금그곡_L2().solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"});
    }

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int time = 0;

        List<Character> ms = new ArrayList<>();
        Stack<Character> temp = new Stack<>();
        subtitutionSharp(m, ms, temp);

        for (Character character : ms) {
            sb.append(character);
        }

        m = sb.toString();
        sb.setLength(0);

//        System.out.println(ms);
        for (String musicinfo : musicinfos) {
            st = new StringTokenizer(musicinfo, ",");
            List<Character> musicNoSharp = new ArrayList<>();
            Stack<Character> t = new Stack<>();

            String startTime = st.nextToken();
            String endTime = st.nextToken();
            String title = st.nextToken();
            String music = st.nextToken();

            subtitutionSharp(music, musicNoSharp, t);
//            System.out.println("startTime = " + startTime);
//            System.out.println("endTime = " + endTime);
//            System.out.println("title = " + title);
//            System.out.println("music = " + music);
            String[] startTimes = startTime.split(":");
            String[] endTimes = endTime.split(":");


            int startH = Integer.parseInt(startTimes[0]);
            int endH = Integer.parseInt(endTimes[0]);
            int startM = Integer.parseInt(startTimes[1]);
            int endM = Integer.parseInt(endTimes[1]);

//            System.out.println("startH = " + startH);
//            System.out.println("endH = " + endH);
//            System.out.println("startM = " + startM);
//            System.out.println("endM = " + endM);

            if (endM < startM) {
                endH--;
                endM += 60;
            }

            int playTimeForMin = ((endH - startH) * 60) + endM - startM;

//            char[] playMusic = new char[playTimeForMin];
            int idx = 0;
            for (int i = 0; i < playTimeForMin; i++) {
                sb.append(musicNoSharp.get(idx++));
                if (idx == musicNoSharp.size()) idx = 0;
            }

//            boolean isContain = false;
            if (sb.toString().contains(m)) {
                if (time < playTimeForMin) {
                    time = playTimeForMin;
                    answer = title;
                }
            }

            sb.setLength(0);
//            for (int i = 0; i < playTimeForMin; i++) {
//                if ()
//            }

//            System.out.println(Arrays.toString(playMusic));
        }

        return answer;
    }

    private static void subtitutionSharp(String m, List<Character> ms, Stack<Character> temp) {
        boolean isSharp = false;
        for (int i = m.length() - 1; i >= 0; i--) {
            if (m.charAt(i) == '#') {
                isSharp = true;
                continue;
            }

            if (isSharp) {
                temp.push((char) (m.charAt(i) + 12));
                isSharp = false;
                continue;
            }

            temp.push(m.charAt(i));
        }

        while (!temp.isEmpty()) {
            ms.add(temp.pop());
        }
    }
}
