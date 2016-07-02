package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import Enemy.Deer;
import Player.Bear;
import Surfaces.Floor;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Bear bear;
	private Deer deer;
	private Floor floor;
	private Floor[] floor_array;
	private int floor_size;
	private Sprite sprite;
	private OrthographicCamera camera;
	private float time_passed = 0;
	private Boolean is_stand = true,is_moving = false;
	private Texture background_texture;
	private Sprite background_sprite;
	
	@Override
	public void create () {

		floor_size = 9;
		bear = new Bear(); //create player controlled bear
		deer = new Deer();

		floor = new Floor();
		floor_array = new Floor[floor_size];

		background_texture = new Texture("rockymountains.jpg");
		background_sprite =new Sprite(background_texture);
		//background_sprite.setSize(1f,1f * background_sprite.getHeight() / background_sprite.getWidth() );


		//bear.setPosition(200,100);

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,400);





	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.enableBlending();
		batch.begin();

		background_sprite.draw(batch);

		time_passed = time_passed + Gdx.graphics.getDeltaTime();
		deer.draw(batch,time_passed);
		if(!is_moving){
			bear.draw(batch,time_passed);
		}
		else if(is_moving){
			bear.draw_walk(batch,time_passed);
		}

		make_floor(floor_array,floor_size);



		batch.end();

		//updates here
		bear.update(Gdx.graphics.getDeltaTime()); /*This is gravity*/

		//controls here

		Rectangle floor_temp = new Rectangle(0,0,800,92);

		/* Polling */


		if(bear.hits(floor_temp)){
			System.out.println("HIT");
			bear.action(1,bear.get_x_coord(),92);

		}

		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			System.out.println("RIGHT PUSHED!!!!!!!!!!!!!!!!!!");
			is_moving = true;
			bear.moveRight(Gdx.graphics.getDeltaTime());
		//	bear.setPosition(500,500);
			//is_moving = false;

		}

		else if(Gdx.input.isKeyPressed(Input.Keys.A)){
			System.out.println("LEFT PUSHED!!!!!!!!!!!!!!!!!!");
			is_moving = true;
			bear.moveLeft(Gdx.graphics.getDeltaTime());
			//	bear.setPosition(500,500);
		//d	is_moving = false;

		}

		else if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			System.out.println("JUMP!!!!!!!!!!!!!!!!!!");
			bear.jump();
			//	bear.setPosition(500,500);
			is_moving = false;

		}

		else if(is_moving){

			is_moving = false;

		}


	}

	private void make_floor(Floor[] f, int size){

		int x_prev = -270;

		for(int i = 0; i < size; i++){

			f[i] = new Floor();
			f[i].setPosition(x_prev + 270, 0);
			x_prev = x_prev + 270;
			f[i].draw(batch);
		}





	}

	@Override
	public void dispose () {

		batch.dispose();
	}
}
