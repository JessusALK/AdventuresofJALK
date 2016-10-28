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


    public void setVelX(float x){
        xPos += x;
    }

    public void setVelY(float y){
        yPos += y;
    }
    public void setX(float x){
        xPos = x;
    }
    public void setY(float y){
        yPos = y;
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
    public void collisionDetection(Rectangle rect){
        collideBottom(rect);
        collideRight(rect);
        collideTop(rect);
        collideLeft(rect);
    }

    private void collideLeft(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle left = new Rectangle(rect.x, rect.y + (15 / 2), 5, rect.getHeight() - 15);
        if (left.overlaps(tileRect)) {
            setX(tileRect.getX() + getWidth());
            setVelX(0);

        }
    }

    private void collideRight(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle right = new Rectangle(rect.x + rect.width - 5, rect.y + (15 / 2), 5, rect.getHeight() - 15);
        if (right.overlaps(tileRect)) {
            setX(tileRect.getX() - getWidth());

            setVelX(0);


        }
    }

    private void collideBottom(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle bottom = new Rectangle(rect.x + (15 / 2), rect.y + rect.getHeight() - 5, rect.width - 15, 5);
        if (bottom.overlaps(tileRect)) {
            setY(tileRect.getY() - getHeight());
            setVelX(0);

        }
    }

    private void collideTop(Rectangle tileRect) {
        Rectangle rect = getBounds();
        Rectangle top = new Rectangle(rect.x + (15 / 2), rect.y, rect.width - 15, 5);
        if (top.overlaps(tileRect)) {
            setY(tileRect.getY() + getHeight());
            setVelY(0);
        }
    }

}
