package com.wujinli.test;

import java.util.Collection;

/**
 * author: WuJinLi
 * time  : 17/5/22
 * desc  :
 */

public class ListUtils {
    public ListUtils() {
    }

    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.size() <= 0;
    }
}
