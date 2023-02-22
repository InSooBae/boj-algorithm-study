package _2023._0222;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution_길찾기게임_L3 {
    public static void main(String[] args) {
        int[][] solution = new Solution_길찾기게임_L3().solution(new int[][]{{7, 4, 6, 9, 1, 8, 5, 2, 3}, {9, 6, 5, 8, 1, 4, 3, 2, 7}});
        System.out.println(Arrays.toString(solution));
    }


    static class Node implements Comparable<Node> {
        Node left;
        Node right;

        int num;
        int x;
        int y;

        public Node(Node left, Node right, int num, int x, int y) {
            this.left = left;
            this.right = right;
            this.num = num;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return o.y != this.y ? o.y - this.y : this.x - o.x;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};

        List<Node> temps = new ArrayList<>();

        int num = 1;
        for (int[] ints : nodeinfo) {
            temps.add(new Node(null, null, num++, ints[0], ints[1]));
        }

        Collections.sort(temps);

        Node root = temps.get(0);
        for (int i = 1; i < temps.size(); i++) {
            insertNode(root, temps.get(i));
        }
        List<Integer> pre = new ArrayList<>();
        preTraverse(root, pre);
        List<Integer> post = new ArrayList<>();
        postTraverse(root, post);

        answer = new int[2][pre.size()];

        for (int i = 0; i < answer[0].length; i++) {
            answer[0][i] = pre.get(i);
        }

        for (int i = 0; i < answer[0].length; i++) {
            answer[1][i] = post.get(i);
        }
        return answer;
    }

    private void postTraverse(Node node, List<Integer> post) {
        if (node.left != null) postTraverse(node.left, post);
        if (node.right!= null) postTraverse(node.right, post);
        post.add(node.num);
    }

    private void preTraverse(Node node, List<Integer> pre) {
        pre.add(node.num);
        if (node.left != null) preTraverse(node.left, pre);
        if (node.right!= null) preTraverse(node.right, pre);
    }

    private void insertNode(Node parent, Node child) {
        if (parent.x < child.x) {
            if (parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        } else {
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        }
    }


}
