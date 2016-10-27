package com.jalk.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import static com.jalk.game.graphics.Loader.setAnimation;

/**
 * Created by Client on 10/25/2016.
 */
public class Player extends GameObject {
    private String state = "";



    public Player(float x, float y, int w, int h) {
        super(x, y, w, h);
    }
    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            state = "left";
            setVelX(-100* Gdx.graphics.getDeltaTime()) ;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            state = "right";
            setVelX(100* Gdx.graphics.getDeltaTime()) ;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            state = "up";
           setVelY(100* Gdx.graphics.getDeltaTime()) ;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            state = "down";
            setVelY(-100* Gdx.graphics.getDeltaTime()) ;
        }
    }




    public String getCharacterState(){
        return state;
    }


}
