package com.jalk.game.world;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Created by Client on 10/27/2016.
 */
public class MapSpecifications {



    public static float getObjectX(MapObject obj, String name){
        float x = obj.getProperties().get("x", Float.class);
        return x;

    }
    public static float getObjectY(MapObject obj, String name){
        float y = obj.getProperties().get("y", Float.class);
        return y;
    }


}
