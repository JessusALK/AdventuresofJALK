package com.jalk.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Shape;
import com.jalk.game.entity.Player;
import com.jalk.game.graphics.Assets;
import com.jalk.game.graphics.Loader;

import static com.jalk.game.graphics.Loader.drawSprite;
import static com.jalk.game.graphics.Loader.loadAnimations;
import static com.jalk.game.graphics.Loader.setAnimation;

public class Main extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Texture playerWalkD;
	private Texture playerWalkU;
	private Texture playerWalkR;
	private Texture playerWalkL;
	private Animation walkD;
	private Animation walkU;
	private Animation walkR;
	private Animation walkL;
	private Rectangle block;
	private OrthographicCamera camera;
	private TextureRegion[] animationFrames;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private TiledMapTileLayer layer;
	private MapProperties mapProperties;
	private Player player;

	FPSLogger fps;


	private int startAnim = 0;


	float elapsedTime;


	
	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("blocks.png");
		tiledMap = new TmxMapLoader().load("Map.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		mapProperties = tiledMap.getProperties();
		int mapWidth = mapProperties.get("width", Integer.class);

		layer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
		fps = new FPSLogger();







		playerWalkD = new Texture("PWDown.png");
		playerWalkU = new Texture("PWUp.png");
		playerWalkR = new Texture("PWRight.png");
		playerWalkL = new Texture("PWLeft.png");



		walkD = loadAnimations(playerWalkD, animationFrames, walkD, 32, 32, 4, 1);
		walkU = loadAnimations(playerWalkU, animationFrames, walkU, 32, 32, 4, 1);
		walkR = loadAnimations(playerWalkR, animationFrames, walkR, 32, 32, 4, 1);
		walkL = loadAnimations(playerWalkL, animationFrames, walkL, 32, 32, 4, 1);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();



		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);



		block = new Rectangle();
		player = new Player(800 / 2 - 64 / 2, 40, 32, 32);



		/*player.x = ;
		player.y = 40;
		player.height = 32;
		player.width = 32;
		*/







		block.x = 800 / 2 - 64 / 2;
		block.y = 20;
		block.height = 32;
		block.width = 32;

	}

	@Override
	public void render () {
		fps.log();
		elapsedTime += Gdx.graphics.getDeltaTime();


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX(), player.getY(), 0);
		float lerp = .9f;
		//camera.position.set(player.x * lerp	, player.y * lerp , 0);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();



		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if(startAnim == 0){
			setAnimation(walkD);
			startAnim++;

		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			setAnimation(walkL);
			player.setX(-100* Gdx.graphics.getDeltaTime()) ;

		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			setAnimation(walkR);
			player.setX(100* Gdx.graphics.getDeltaTime()) ;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			setAnimation(walkU);
			player.setY(100* Gdx.graphics.getDeltaTime()) ;


		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			setAnimation(walkD);
			player.setY(-100* Gdx.graphics.getDeltaTime()) ;

		}



		drawSprite(batch, elapsedTime, player.getBounds());

		batch.end();









	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerWalkD.dispose();
		playerWalkU.dispose();
		playerWalkR.dispose();
		playerWalkL.dispose();

		img.dispose();
	}
}
