package com.swroom.lifeline;

/**
 * 选项
 * Created by jingz on 2017/2/1.
 */
public abstract class Choice {

    /**
     * 接收玩家输入，并返回结果
     * @param id 选项id
     * @param input 选择结果
     * @return 返回结果
     */
    protected abstract boolean choose(int id, String input);

    /**
     * 根据选项返回对应的信息
     * @param id 选项id
     * @param input 选择结果
     * @return message
     */
    protected String getMessage(int id, String input) {
        System.out.println("通过id和input在数据库中查询对应的message并返回");
        return "You get a message.";
    }
}
