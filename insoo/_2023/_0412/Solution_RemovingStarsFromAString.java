package _2023._0412;

import java.util.LinkedList;
import java.util.ListIterator;

class Solution_RemovingStarsFromAString {

    public static void main(String[] args) {
        String s = new Solution_RemovingStarsFromAString().removeStars("leet**cod*e");
        System.out.println(s);
    }
    public String removeStars(String s) {
        LinkedList<Character> list = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (char c :s.toCharArray()) {
            list.add(c);
        }
        ListIterator<Character> iterator = list.listIterator();
        while (iterator.hasNext()) {
            char next = iterator.next();
            if (next == '*') {
                iterator.remove();
                iterator.previous();
                iterator.remove();
            }
        }
        
        for (char c : list) {
            sb.append(c);
        }

        return sb.toString();
    }
}