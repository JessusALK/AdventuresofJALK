package com.jalk.game.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Client on 10/20/2016.
 */
    public class Loader {
    private static Animation anim;

        public static Animation loadAnimations(Texture tex, TextureRegion[] frames, Animation anim, int width, int height, int col, int row){
            TextureRegion[][] tempFrames = TextureRegion.split(tex , width, height);
            frames = new TextureRegion[row * col];

            int index = 0;
            for(int i = 0; i < col; i++){
                for(int j = 0; j < row; j++){
                    frames[index++] = tempFrames[j][i];
                }
            }
            anim = new Animation(1f/4f, frames);

            return anim;
        }
    public static void setAnimation(Animation animation){
        anim = animation;
    }
    public static void drawSprite(SpriteBatch batch,Float elapsedTime, Rectangle rectangle){
        batch.draw(anim.getKeyFrame(elapsedTime/2f, true), rectangle.x, rectangle.y);
    }


}
