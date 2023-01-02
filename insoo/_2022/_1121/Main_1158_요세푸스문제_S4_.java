import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1158_요세푸스문제_S4_ {


    static class CircularList {
        private int[] data;
        private int[] pre;
        private int[] nxt;

        private int cursor = 1;

        private int size;

        public CircularList(int n) {
            this.data = new int[n + 1];
            this.pre = new int[n + 1];
            this.nxt = new int[n + 1];

            size = n;

            for (int i = 0; i <= n; i++) {
                data[i] = i;
                pre[i] = i - 1;
                nxt[i] = i + 1;
            }
            pre[1] = n;
            nxt[n] = 1;
        }

        public void next() {
            cursor = nxt[cursor];
        }

        public int removeCur() {
            int removedData = data[cursor];

            nxt[pre[cursor]] = nxt[cursor];
            pre[nxt[cursor]] = pre[cursor];
            next();
            size--;

            return removedData;
        }

        public int[] getData() {
            return data;
        }

        public int[] getPre() {
            return pre;
        }

        public int[] getNxt() {
            return nxt;
        }

        public int getCursor() {
            return cursor;
        }

        public int getSize() {
            return size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        CircularList circularList = new CircularList(n);

        List<Integer> result = new ArrayList<>();

        sb.append("<");

        int loopCnt = 0;
        while (circularList.getSize() != 0) {
            if (++loopCnt == k) {
                if (circularList.getSize() != 1) sb.append(circularList.removeCur()).append(", ");
                else sb.append(circularList.removeCur());
                loopCnt = 0;
            } else circularList.next();
        }

        sb.append(">");
        System.out.println(sb);
    }
}