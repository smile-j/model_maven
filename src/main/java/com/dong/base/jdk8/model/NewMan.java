package com.dong.base.jdk8.model;

import java.util.Optional;

/**
 * Created by Administrator on 2017/11/17.
 */
public class NewMan {

    private Optional<Godness> godness = Optional.empty();

    public Optional<Godness> getGodness() {
        return godness;
    }


    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    public NewMan(Optional<Godness> godness) {

        this.godness = godness;
    }

    public NewMan(){

    }
}
