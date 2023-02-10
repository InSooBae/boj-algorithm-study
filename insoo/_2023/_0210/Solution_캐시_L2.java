package _2023._0210;

import java.util.*;

public class Solution_캐시_L2 {
    public static void main(String[] args) {

    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> cache = new ArrayList<>();
        int curSize = 0;

        for (String city : cities) {
            city = city.toLowerCase();
            if (curSize > 0) {
                boolean isCached = false;
                for (int i = 0; i < curSize; i++) {
                    if (city.equals(cache.get(i))) {
                        cache.remove(i);
                        cache.add(city);
                        isCached = true;
                        break;
                    }
                }

                if (isCached) {
                    answer += 1;
                } else {
                    if (curSize < cacheSize) {
                        curSize++;
                        cache.add(city);
                    } else {
                        cache.remove(0);
                        cache.add(city);
                    }
                    answer += 5;
                }
            } else if (cacheSize != 0) {
                answer += 5;
                curSize++;
                cache.add(city);
            } else {
                answer += 5;
            }
        }

        return answer;
    }
}
