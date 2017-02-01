package com.swroom.lifeline;

/**
 * 非关键选择
 * Created by jingz on 2017/2/1.
 */
public class NormalChoice extends Choice {

    /**
     * 非关键选择在流程上都是true
     * @param id 选项id
     * @param a 选择结果
     * @return
     */
    @Override
    public boolean choose(int id, String a) {
        System.out.println("非关键选择，总是返回true");
        return true;
    }

}
