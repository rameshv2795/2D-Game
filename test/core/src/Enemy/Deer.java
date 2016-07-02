package Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Vinod on 6/28/2016.
 */
public class Deer {

    private float current_x,current_y, velocity_y;
    private Texture texture;
    private Sprite sprite;

    /*Animations*/
    private TextureAtlas walk_atlas,stand_atlas;
    private Animation walk_animation,stand_animation;
    //private float time_passed = 0;

    Rectangle bottom;


    public Deer(){ //constructor

        bottom = new Rectangle(1.0f,92.0f,90f,70f);

        velocity_y = 0;
        texture = new Texture(Gdx.files.internal("bear_edit.png"));
        sprite = new Sprite(texture,419,300);
        current_x = 92;
        setPosition(500,92); //need this to init position

        stand_atlas = new TextureAtlas(Gdx.files.internal("deer_stand.atlas")); /*reference atlas*/
        stand_animation = new Animation(1/10f,stand_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/
        walk_atlas = new TextureAtlas(Gdx.files.internal("bear_walk.atlas")); /*reference atlas*/
        walk_animation = new Animation(1/10f,walk_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/

    }

    public void setPosition(float x, float y){

        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x,y);
        current_x = x;
        current_y = y;

    }

    public Boolean hits(Rectangle r){

        if(bottom.overlaps(r)){
            return true;
        }
        return false;


    }

    public void moveRight(float delta){


        current_x = current_x + (100 * delta);
        bottom.x = current_x;
        setPosition(current_x, current_y);

    }

    public void moveLeft(float delta){

        current_x = current_x - (100 * delta);
        bottom.x = current_x;
        setPosition(current_x, current_y);

    }

    public void jump(){
        velocity_y = 30;
        bottom.y = current_y + 30;
        setPosition(current_x, current_y+30);
    }

    public void action(int type, float x, float y){

        if(type == 1){
            setPosition(current_x,y);
        }

    }

    public Texture get_texture(){

        return texture;

    }

    public Sprite get_sprite(){

        return sprite;

    }

    public float get_x_coord(){

        return current_x;
    }

    public void draw(SpriteBatch batch,float delta){

        batch.draw(stand_animation.getKeyFrame(delta,false),current_x,current_y);

    }

    public void draw_walk(SpriteBatch batch,float delta){

        batch.draw(walk_animation.getKeyFrame(delta,true),current_x,current_y);

    }



    public void update(float delta){

            /*Gravity*/

        if(current_y > 92) {

            velocity_y = velocity_y - (50 * delta); /*50 pixles per second*/
            bottom.y = bottom.y + velocity_y;
            setPosition(current_x, current_y + velocity_y);

        }

    }


}
