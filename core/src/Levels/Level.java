package Levels;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;


import java.util.ArrayList;
import java.util.List;

import Enemy.Deer;
import Player.Bear;
import Surfaces.Bush;
import Surfaces.Floor;

/**
 * Created by Vinod on 8/4/2016.
 */
public abstract class Level  {

    protected SpriteBatch batch;
    protected Bear bear;
    protected List<Deer> deers;
    protected Floor floor;
    protected Floor[] floor_array;
    protected Bush bush;
    protected int floor_size;
    protected Sprite sprite;
    protected OrthographicCamera camera;
    protected float time_passed = 0;
    protected Boolean is_stand = true,is_moving = false,death = false, attack = false, in_bush = false;
    protected Texture background_texture;
    protected Sprite background_sprite;
    protected Texture bush_texture;
    protected Sprite bush_sprite;
    protected Texture game_over_texture, level_passed_texture, retry_texture;
    protected Sprite game_over_sprite,level_passed_sprite, retry_sprite;

    protected Boolean level_passed = false;
    protected Boolean game_over = false;

    protected int deer_count = 0;

    protected int playtime = 0;


    /*For debug*/
    Boolean level1 = true;


    protected int timer = 0;

    protected Dialog you_win;




    protected void make_floor(Floor[] f, int size){ /*Same for all levels*/

        int x_prev = -270;

        for(int i = 0; i < size; i++){

            f[i] = new Floor();
            f[i].setPosition(x_prev + 270, 0);
            x_prev = x_prev + 270;
            f[i].draw(batch);
        }


    }

    protected void make_deers(List<Deer> d, int size){ /*Same for all levels*/

        int x_prev = -270;

        for(int i = 0; i < size; i++){

            d.add(new Deer());

        }


    }

    protected void create_level_raw(){

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        bear = new Bear();
        deers = new ArrayList<Deer>();

        floor = new Floor();
        floor_array = new Floor[9];

        background_texture = new Texture("utah.jpg");
        background_sprite =new Sprite(background_texture,1500,1000);

        game_over_texture = new Texture(Gdx.files.internal("GameOver.png"));
        game_over_sprite = new Sprite(game_over_texture,491,116);

        level_passed_texture = new Texture(Gdx.files.internal("LevelPassed.png"));
        level_passed_sprite = new Sprite(level_passed_texture,557,115);

        retry_texture = new Texture(Gdx.files.internal("retry.png"));
        retry_sprite = new Sprite(retry_texture,622,115);

    }

    protected void draw_deer(List<Deer> d, int count){

        for(int i = 0; i < count; i++) {

            d.get(i).draw(batch, time_passed, game_over);
        }
    }

    public Boolean get_level_passed(){
        return level_passed;
    }

    public Boolean get_level_failed(){
        return game_over;
    }

    protected abstract void create_level_unique();
    public abstract void render_level(SpriteBatch b);

    public OrthographicCamera get_camera(){

        return camera;
    }







}
