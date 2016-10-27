package com.jalk.game.entity;

/**
 * Created by Client on 10/26/2016.
 */
public class Block extends GameObject {
    private String id;

    public Block(float x, float y, float w, float h, String id) {
        super(x, y, w, h);
        this.id = id;
    }
    public String getID(){
        return id;
    }

}
