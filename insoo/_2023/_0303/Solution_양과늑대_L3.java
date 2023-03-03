package _2023._0303;

import java.util.*;

public class Solution_양과늑대_L3 {
    public static void main(String[] args) {
        int solution = new Solution_양과늑대_L3().solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
        System.out.println(solution);
    }

    private static class Node {
        Node left;
        Node right;
        int nodeNum;
        boolean isSheep;

        public Node(Node left, Node right, int nodeNum, boolean isSheep) {
            this.left = left;
            this.right = right;
            this.nodeNum = nodeNum;
            this.isSheep = isSheep;
        }

        public void addChild(Node node) {
            if (left == null) {
                left = node;
            } else if (right == null) {
                right = node;
            }
        }
    }

    private static class Pos {
        int vis;
        int sheepCnt;
        int wolfCnt;

        public Pos(int vis, int sheepCnt, int wolfCnt) {
            this.vis = vis;
            this.sheepCnt = sheepCnt;
            this.wolfCnt = wolfCnt;
        }
    }


    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        List<Node> nodes = new ArrayList<>();
        int sheepCnt = 0;
        for (int i = 0; i < info.length; i++) {
            nodes.add(new Node(null, null, i, info[i] == 0));
            if (info[i] == 0) sheepCnt++;
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            nodes.get(parent).addChild(nodes.get(child));
        }

        Node root = nodes.get(0);

        answer = bfs(root, nodes, sheepCnt);

        return answer;
    }

    private int bfs(Node root, List<Node> nodes, int sheepCnt) {
        Queue<Pos> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
//        Vis vis = new Vis(0);
        queue.add(new Pos(1, 1, 0));
        visited.add(1);
        int max = 0;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            if (max < cur.sheepCnt) max = cur.sheepCnt;
            for (int i = 0; i < 17; i++) {
                if ((cur.vis & 1 << i) != 0) {
                    Node curNode = nodes.get(i);

                    if (curNode.left != null) {
                        findWay(curNode.left, queue, visited, cur);
                    }
                    if (curNode.right != null) {
                        findWay(curNode.right, queue, visited, cur);
                    }
                }
            }

        }

        return max;
    }

    private static void findWay(Node curNode, Queue<Pos> queue, Set<Integer> visited, Pos vis) {
        Node node = curNode;
        int willVisit = vis.vis | 1 << node.nodeNum;
        if (!visited.contains(willVisit)) {
            if (node.isSheep) {
                queue.add(new Pos(willVisit, vis.sheepCnt + 1, vis.wolfCnt));
                visited.add(willVisit);
            } else if (vis.sheepCnt > vis.wolfCnt + 1) {
                queue.add(new Pos(willVisit, vis.sheepCnt, vis.wolfCnt + 1));
                visited.add(willVisit);
            }
        }
    }
}
