package com.gr.config;

import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ProductConfig {

    public List<String> getHotWords(String[] StrArr) {
        Map<String, Integer> map = MapFunction(StrArr);
        List<String> hotWords = new ArrayList<>();
        while (!map.isEmpty()){
            String hotWord = FindMapMaxValue(map);
            map.remove(hotWord);
            hotWords.add(hotWord);
        }
        return hotWords;
    }

    public Map<String, Integer> MapFunction(String[] StrArr) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (!(StrArr == null || StrArr.length == 0))// 先判断字符数组是否为空
            for (String c : StrArr)
                if (null != map.get(c))
                    // 若不为空，说明已经存在相同字符，则Value值在原来的基础上加1
                    map.put(c, map.get(c) + 1);
                else
                    map.put(c, 1);
        return map;
    }

    public String FindMapMaxValue(Map<String, Integer> map) {

        Iterator iter = map.entrySet().iterator();
        Map.Entry entry = (Map.Entry) iter.next();// 将第一个entry定义为最大次数的
        String maxKey = (String) entry.getKey();// 获得K
        int maxValue = (int) entry.getValue();// 获得V
        while (iter.hasNext()) {
            entry = (Map.Entry) iter.next();// 第二个entry
            String tempK = (String) entry.getKey();
            int tempV = (int) entry.getValue();
            if (maxValue < tempV) {
                maxKey = tempK;
                maxValue = tempV;
            }
        }
        return maxKey;
    }
}
