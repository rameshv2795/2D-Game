package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.Frame;
import java.awt.Point;


import Menu.MenuScreen;
import Enemy.Deer;
import Levels.Level;
import Levels.Level1;
import Levels.Level2;
import Player.Bear;
import Surfaces.Bush;
import Surfaces.Floor;

public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Level current_level;
	private int which_level = 0;
	private MenuScreen m;
	private float screen_width, screen_height;

	
	@Override
	public void create () {

		screen_width = Gdx.graphics.getWidth();
		screen_height = Gdx.graphics.getHeight();



		batch = new SpriteBatch();

		if(which_level == 0){
			m = new MenuScreen(screen_width,screen_height);

		}

		else if(which_level == 1) {

			current_level = new Level1(batch);


		}

		else if(which_level == 2) {

			current_level = new Level2(batch);


		}






	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.enableBlending();
		batch.begin();


		if (which_level == 0) {

			m = new MenuScreen(screen_width,screen_height);
			m.render_menu(batch);

			if(m.get_selection() == 1){
				which_level = 1;
				current_level = new Level1(batch);

			}



		}

		else{

			current_level.render_level(batch);

		}



		batch.end();

		if (which_level != 0) {


			if (current_level.get_level_failed() && Gdx.input.isKeyPressed(Input.Keys.R)) { /*reset level*/

				if (which_level == 1) {
					System.out.println("WHICH_LEVEL: " + which_level);
					current_level = new Level1(batch);

				}

				else if (which_level == 2) {
					System.out.println("SHOULD BE HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					current_level = new Level2(batch);

				}

			}

			if (current_level.get_level_passed() && Gdx.input.isKeyPressed(Input.Keys.R)) { /*Go to next level*/

				if (which_level == 1) {
					System.out.println("GO TO NEXT LEVEL!!!!!!");
					which_level = 2;
					current_level = new Level2(batch);

				}

			}

		}
	}


	@Override
	public void dispose () {

		batch.dispose();
	}
}
