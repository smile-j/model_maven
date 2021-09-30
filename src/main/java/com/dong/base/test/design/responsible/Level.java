package com.dong.base.test.design.responsible;

/**
 * Created by Administrator on 2018/2/23.
 */
public class Level {

    private int level=0;
    public Level(int level){
        this.level = level;
    }

    public boolean above(Level level){
        if (this.level>=level.level){
            return true;
        }
        return false;
    }

}
