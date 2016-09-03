package Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

import Enemy.Deer;
import Surfaces.Bush;

/**
 * Created by Vinod on 8/4/2016.
 */
public class Level1 extends Level {

    Deer deer;

    public Level1(SpriteBatch b){

        batch = b;
        this.create_level_raw();
        this.create_level_unique();

    }


    @Override
    protected void create_level_unique(){
        System.out.println("IN UNIQUE!!!!!!!!!!!!!");

        deer_count = 1;
        make_deers(deers,1);

        /*Only for this level because initially wasn't using Lists*/
        deer = deers.get(0);

        bush = new Bush(200,80);
        bush.setPosition(160,80);

    }
    @Override
    public void render_level(SpriteBatch b){

        batch = b;
        background_sprite.draw(batch);

        bush.draw(batch); /*DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG*/
      //  System.out.println("IN RENDER!!!!!!!!!!!!!");

        time_passed = time_passed + Gdx.graphics.getDeltaTime();

			/*if (deer.get_x_coord() <= 300)
				deer.deer_set_direction(-1);
			else if (deer.get_x_coord() >= 500) {
				deer.deer_set_direction(1);
			}*/

        timer++;
        if(timer % 100 == 0) {

            if(deer.get_direction() == 1)
                deer.deer_set_direction(-1);
            else if ((deer.get_direction() == -1))
                deer.deer_set_direction(1);

        }

        if(deer.get_direction() == 1 && !in_bush && !deer.get_dead()){//
            game_over = true; //DEBUG
            game_over_texture = new Texture(Gdx.files.internal("GameOver.png"));
            game_over_sprite = new Sprite(game_over_texture,491,116);




        }

        if(game_over){
            game_over_sprite.setPosition(400,500);
            game_over_sprite.draw(batch);
            retry_sprite.setPosition(400,300);
            retry_sprite.draw(batch);
        }
        else if(level_passed){
            level_passed_sprite.setPosition(400,500);
            level_passed_sprite.draw(batch);
        }

			/*if (deer.get_x_coord() <= 300)
				deer.deer_set_direction(-1);
			else if (deer.get_x_coord() >= 500) {
				deer.deer_set_direction(1);
			}*/



      //  deer.draw(batch, time_passed, game_over); /*DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG*/
        draw_deer(deers,1);

        if(bear.bear_bush(bush.get_bush_box()) && Gdx.input.isKeyPressed(Input.Keys.R)) {

            in_bush = true;

        }

        else if(bear.bear_bush(bush.get_bush_box())){
            in_bush = false;
        }


        if (!is_moving && !death && !in_bush) {
            bear.draw(batch, time_passed);
        } else if (is_moving && !death && !in_bush) {
            bear.draw_walk(batch, time_passed);
        }



        if (death) {
            bear.draw_death(batch, time_passed);
        }

        make_floor(floor_array,9);




        //updates here
        bear.update(Gdx.graphics.getDeltaTime()); /*This is gravity*/

        //controls here

        Rectangle floor_temp = new Rectangle(0, 0, 800, 92);

		/* Polling */


        if (bear.hits(floor_temp)) {
            System.out.println("HIT");
            bear.action(1, bear.get_x_coord(), 92);

        }

        if (deer.hits_head(bear.get_hitbox())) {
            //	System.out.println("KILLED");

            if (!deer.get_dead())
                death = false;


        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && !in_bush) {
            //	System.out.println("RIGHT PUSHED!!!!!!!!!!!!!!!!!!");
            is_moving = true;
            bear.moveRight(Gdx.graphics.getDeltaTime());
            //	bear.setPosition(500,500);
            //is_moving = false;

        } else if (Gdx.input.isKeyPressed(Input.Keys.A) && !in_bush) {
            //	System.out.println("LEFT PUSHED!!!!!!!!!!!!!!!!!!");
            is_moving = true;
            bear.moveLeft(Gdx.graphics.getDeltaTime());
            //	bear.setPosition(500,500);
            //d	is_moving = false;

        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !in_bush) {
            System.out.println("JUMP!!!!!!!!!!!!!!!!!!");


            bear.jump();
            //	bear.setPosition(500,500);
            is_moving = false;

        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            System.out.println("ATTACK!!!!!!!!!!!!!!!!!!");

            is_moving = false;
            bear.is_attack();

            if (bear.kills_enemy(deer.get_back())) {
                System.out.println("HIT DEER!!!");

                if (!deer.get_gone()) {
                    deer.deer_dead();
                }
                //bear.action(1,bear.get_x_coord()d,92);

            }
            //	bear.setPosition(500,500);
            //d	is_moving = false;

        } else if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            System.out.println("EATING!!!!!!!!!!!!!!!!!!");

            is_moving = false;
            bear.is_eat();

            if ( bear.eats_enemy(deer.get_full()) && deer.get_dead()) {
                System.out.println("ATE DEER!!!");
                deer.deer_gone();
                level_passed = true;
                //bear.action(1,bear.get_x_coord(),92);

            }
            //	bear.setPosition(500,500);
            //d	is_moving = false;

        } else if (is_moving) {

            is_moving = false;

        }



    }




}
