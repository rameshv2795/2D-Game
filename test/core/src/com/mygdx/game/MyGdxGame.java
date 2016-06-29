package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Player.Bear;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private Bear bear;
	private Sprite sprite;
	private OrthographicCamera camera;
	
	@Override
	public void create () {

		bear = new Bear(); //create player controlled bear

		//bear.setPosition(200,100);

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,800,400);





	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		bear.draw(batch);
		batch.end();

		//updates here


		//controls here


		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			System.out.println("RIGHT PUSHED!!!!!!!!!!!!!!!!!!");
			bear.moveRight(Gdx.graphics.getDeltaTime());
		//	bear.setPosition(500,500);


		}
	}



	@Override
	public void dispose () {
		batch.dispose();
	}
}
