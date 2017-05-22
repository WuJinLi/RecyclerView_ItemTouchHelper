package com.wujinli.test;


/**
 * author: WuJinLi
 * time  : 17/5/21
 * desc  : 定义接口（实现移动，删除，移动后数据刷新的方法）
 */

public interface ItemTouchHelperAdapter {
    /**
     * 定义Item移动的抽象方法
     */
    boolean onItemMove(int fromPosition, int toPosition);


    /**
     * 定义Item删除的抽象方法
     */
    void onItemDismiss(int position);

}
