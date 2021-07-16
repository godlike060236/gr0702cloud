package com.gr.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        String Str = "AAbbccccccaaaaaa";
        char[] StrArr = Str.toCharArray();// 把字符串转为字符数组toCharArray

        Map<Character, Integer> map = MapFunction(StrArr);
        char ch = FindMapMaxValue(map);
    }

    /**
     * MapFunction:实现将字符数组转存到Map中， 其中，Map中的key为出现的字符，value对应该字符出现的次数
     * @param StrArr  StrArr字符数组，输入前必须先将字符串转为字符数组
     * @return map 集合中，key为出现的字符（Character），value对应该字符出现的次数（Integer）
     */
    public static Map<Character, Integer> MapFunction(char[] StrArr) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        if (!(StrArr == null || StrArr.length == 0))// 先判断字符数组是否为空
            for (int i = 0; i < StrArr.length; i++)
                if (null != map.get(StrArr[i]))
                    // 若不为空，说明已经存在相同字符，则Value值在原来的基础上加1
                    map.put(StrArr[i], map.get(StrArr[i]) + 1);
                else
                    map.put(StrArr[i], 1);

        return map;
    }

    public static char FindMapMaxValue(Map<Character, Integer> map) {

        Iterator iter = map.entrySet().iterator();
        Map.Entry entry = (Map.Entry) iter.next();// 将第一个entry定义为最大次数的
        char maxKey = (char) entry.getKey();// 获得K
        int maxValue = (int) entry.getValue();// 获得V
        while (iter.hasNext()) {
            entry = (Map.Entry) iter.next();// 第二个entry
            char tempK = (char) entry.getKey();
            int tempV = (int) entry.getValue();
            if (maxValue < tempV) {
                maxKey = tempK;
                maxValue = tempV;
            }
        }

        System.out.println("出现次数最多的字符：" + maxKey + " 出现次数：" + maxValue);
        return maxKey;
    }
}
