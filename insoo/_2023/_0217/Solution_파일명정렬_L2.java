package _2023._0217;

import java.util.ArrayList;
import java.util.List;

public class Solution_파일명정렬_L2 {
    public static void main(String[] args) {
        String[] solution = new Solution_파일명정렬_L2().solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});

    }

    static private class FileName implements Comparable<FileName> {

        String head;

        int num;

        String tail;

        int idx;

        public FileName(String head, int num, String tail, int idx) {
            this.head = head;
            this.num = num;
            this.tail = tail;
            this.idx = idx;
        }

        @Override
        public int compareTo(FileName o) {
            int i = this.head.compareTo(o.head);
            return i == 0 ? (this.num == o.num ? this.idx - o.idx : this.num - o.num) : i;
        }

        @Override
        public String toString() {
            return "FileName{" +
                    "head='" + head + '\'' +
                    ", num=" + num +
                    ", tail='" + tail + '\'' +
                    ", idx=" + idx +
                    '}';
        }
    }

    public String[] solution(String[] files) {
        String[] answer = {};
        StringBuilder sb = new StringBuilder();
        List<FileName> fileNames = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            String head = "";
            int num = 0;
            String tail = "";

            int j = 0;
            for (; j < file.length(); j++) {
                char c = file.charAt(j);
                if ('0'<= c && c <= '9') break;
                sb.append(c);
            }
            head = sb.toString();
            sb.setLength(0);

            for (; j < file.length(); j++) {
                char c = file.charAt(j);
                if (!('0'<= c && c <= '9')) break;
                sb.append(c);
            }

            num = Integer.parseInt(sb.toString());
            sb.setLength(0);

            for (; j < file.length(); j++) {
                sb.append(file.charAt(j));
            }

            tail = sb.toString();
            sb.setLength(0);

            fileNames.add(new FileName(head.toLowerCase(), num, tail, i));
        }

        fileNames.sort(FileName::compareTo);
        answer = new String[files.length];
        int idx = 0;
        for (FileName fileName : fileNames) {
            answer[idx++] = files[fileName.idx];
        }

        return answer;
    }
}
