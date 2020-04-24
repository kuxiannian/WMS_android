
package com.lxkj.wms.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static boolean isEmpty(final List<? extends Object> list) {
        return null == list || list.size() == 0;
    }

    public static int listSize(final List<? extends Object> list) {
        return (null == list) ? 0 : list.size();
    }

    public static <E> E getIndex(final List<E> list, final int i) {
        if (null == list || i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    public static <E> int indexOf(final List<E> list, final E o) {
        if (!isEmpty(list) && null != o) {
            for (int i = 0; i < list.size(); i++) {
                if (o.equals(list.get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static <E> List<E> cutIfLong(final List<E> list, final int max) {
        if (null == list || max <= 0 || max >= list.size()) {
            return list;
        }
        return list.subList(0, max);
    }

    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {

        List<List<T>> listArray = new ArrayList<List<T>>();

        List<T> subList = null;

        for (int i = 0; i < list.size(); i++) {

            if (i % pageSize == 0) {//每次到达页大小的边界就重新申请一个subList

                subList = new ArrayList<T>();

                listArray.add(subList);

            }

            subList.add(list.get(i));

        }

        return listArray;

    }

}
