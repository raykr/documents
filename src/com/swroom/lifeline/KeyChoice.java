package com.swroom.lifeline;

/**
 * 关键选择
 * Created by jingz on 2017/2/1.
 */
public class KeyChoice extends Choice {

    /**
     *
     * @param id 选项id
     * @param input 选择结果
     * @return
     */
    @Override
    public boolean choose(int id, String input) {
        System.out.println("根据id和a与数据库中记录对比，获取该关键选择正确与否");

        // 模拟选择Ａ是正确项
        if ("A".equals(input)) {
            return true;
        }
        return false;
    }

}
