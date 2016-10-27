package com.jalk.game.entity;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Client on 10/26/2016.
 */
public abstract class GameObject {

    float xPos;
    float yPos;
    private float width;
    private float height;
    private Rectangle rectangle;


    public GameObject(float x, float y, float w, float h){
        xPos = x;
        yPos = y;
        width = w;
        height = h;

    }


    public void setX(float x){
        xPos += x;
    }

    public void setY(float y){
        yPos += y;
    }

    public float getX(){
        return xPos;
    }

    public float getY(){
        return yPos;
    }
    public float getHeight(){
        return height;
    }
    public float getWidth(){
        return width;
    }

    public Rectangle getBounds(){
        return new Rectangle(xPos, yPos, width, height);

    }

}
