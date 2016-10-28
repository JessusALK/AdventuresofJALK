package com.jalk.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.jalk.game.entity.Block;
import com.jalk.game.entity.Player;


import static com.jalk.game.graphics.Loader.*;
import static com.jalk.game.world.MapSpecifications.getObjectX;
import static com.jalk.game.world.MapSpecifications.getObjectY;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Texture playerWalkD;
	private Texture playerWalkU;
	private Texture playerWalkR;
	private Texture playerWalkL;
	private Texture bushTex;
	private Animation walkD;
	private Animation walkU;
	private Animation walkR;
	private Animation walkL;
	private Rectangle block;
	private OrthographicCamera camera;
	private TextureRegion[] animationFrames;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private TiledMapTileLayer backLayer;
	private MapLayer objectLayer;
	private MapProperties mapProperties;
	private Player player;
	private Block Bush;
	private MapObjects  objects;
	private MapObject playerObj;
	private MapObject rockObj;



	FPSLogger fps;


	private int startAnim = 0;


	float elapsedTime;


	
	@Override
	public void create () {

		batch = new SpriteBatch();
		objects = new MapObjects();


		img = new Texture("blocks.png");
		tiledMap = new TmxMapLoader().load("Map.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		mapProperties = tiledMap.getProperties();
		int mapWidth = mapProperties.get("width", Integer.class);




		backLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		objectLayer = tiledMap.getLayers().get("GameObject");

		objects = objectLayer.getObjects();


		playerObj = objectLayer.getObjects().get("Player");
		int s = objectLayer.getObjects().getCount();
		System.out.println(s);



		

		System.out.println(getObjectX(playerObj, "Player") + ", " + getObjectY(playerObj, "Player"));
		fps = new FPSLogger();







		playerWalkD = new Texture("PWDown.png");
		playerWalkU = new Texture("PWUp.png");
		playerWalkR = new Texture("PWRight.png");
		playerWalkL = new Texture("PWLeft.png");
		bushTex = new Texture("bush.png");



		walkD = loadAnimations(playerWalkD, animationFrames, walkD, 32, 32, 4, 1);
		walkU = loadAnimations(playerWalkU, animationFrames, walkU, 32, 32, 4, 1);
		walkR = loadAnimations(playerWalkR, animationFrames, walkR, 32, 32, 4, 1);
		walkL = loadAnimations(playerWalkL, animationFrames, walkL, 32, 32, 4, 1);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();



		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);



		block = new Rectangle();
		player = new Player(getObjectX(playerObj, "Player"), getObjectY(playerObj, "Player"), 32, 32);



		/*player.x = ;
		player.y = 40;
		player.height = 32;
		player.width = 32;
		*/
		Bush = new Block(800/2 - 64 / 2, 40, 32, 32, "Bush");










		block.x = 800 / 2 - 64 / 2;
		block.y = 20;
		block.height = 32;
		block.width = 32;

	}

	@Override
	public void render () {
		fps.log();
		elapsedTime += Gdx.graphics.getDeltaTime();
		player.handleInput();
        player.collisionDetection(Bush.getBounds());

		for (TiledMapTileMapObject rectangleObject : objectLayer.getObjects().getByType(TiledMapTileMapObject.class)) {
			Rectangle rectangle = new Rectangle((rectangleObject.getX()), rectangleObject.getY(), rectangleObject.getProperties().get("width", Float.class),
					rectangleObject.getProperties().get("height", Float.class));
			if(Intersector.overlaps(rectangle, player.getBounds())){
				player.collisionDetection(rectangle);
			}
		}



		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX(), player.getY(), 0);
		float lerp = .9f;
		//camera.position.set(player.x * lerp	, player.y * lerp , 0);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();








		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		renderMapObjects(objectLayer, batch, "Rock");


		if(startAnim == 0){
			setAnimation(walkD);
			startAnim++;

		}
		if(player.getCharacterState().equals("left")){
			setAnimation(walkL);
		}
		if(player.getCharacterState().equals("right")){
			setAnimation(walkR);
		}
		if(player.getCharacterState().equals("up")){
			setAnimation(walkU);
		}
		if(player.getCharacterState().equals("down")){
			setAnimation(walkD);
		}

		if(player.getX() <= 0 || player.getY() <= 0){
			player.setVelX(0);
			player.setVelY(0);
		}
		System.out.println("Player x : " + player.getX() + " Player y : " + player.getY());



		drawAnimation(batch, elapsedTime, player.getBounds());
		drawSprite(batch, bushTex, Bush.getBounds());

		batch.end();









	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerWalkD.dispose();
		playerWalkU.dispose();
		playerWalkR.dispose();
		playerWalkL.dispose();
		bushTex.dispose();
		tiledMap.dispose();

		img.dispose();
	}
}
