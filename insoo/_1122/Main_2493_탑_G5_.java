import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑_G5_ {

    static class Pair {
        private int pos;
        private int height;

        public Pair(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }

        public int getPos() {
            return pos;
        }

        public int getHeight() {
            return height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        Stack<Pair> s = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        ArrayList<Integer> results = new ArrayList<>();

        s.push(new Pair(0, Integer.MAX_VALUE));

        for (int curPos = 1; curPos <= N; curPos++) {
            int curBuildingHeight = Integer.parseInt(st.nextToken());
            while (s.peek().height < curBuildingHeight) {
                s.pop();
            }
            results.add(s.peek().pos);
            s.push(new Pair(curPos, curBuildingHeight));
        }

        for (Integer result : results) {
            sb.append(result).append(" ");
        }

        System.out.println(sb);
    }
}
