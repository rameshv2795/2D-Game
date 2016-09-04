package Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vinod on 6/28/2016.
 */
public class Moose {

    private float current_x,current_y, velocity_y;
    private Texture carcass_texture;
    private Sprite carcass_sprite;
    private Boolean dead = false, eaten = false;

    /*Animations*/
    private TextureAtlas walk_atlas,stand_atlas, deer_death_atlas,deer_walk_atlas,deer_walk_flip_atlas, stand_flip_atlas;
    private Animation walk_animation,stand_animation, deer_death_animation, deer_walk_animation, deer_walk_flip_animation,stand_flip_animation;
    //private float time_passed = 0;

    private Timer time;

    Rectangle bottom;
    Rectangle head,back,full;

    private int direction = -1;






    public Moose(){ //constructor

        bottom = new Rectangle(1.0f,92.0f,90f,70f);
        head = new Rectangle(520.0f,92.0f,0f,20f);
        back = new Rectangle(520.0f,92.0f,50f,20f);
        full = new Rectangle(550.0f,92.0f,40f,20f);



        velocity_y = 0;
        carcass_texture = new Texture(Gdx.files.internal("carcass.png"));
        carcass_sprite = new Sprite(carcass_texture,200,40);
        current_x = 92;
        setPosition(500,92); //need this to init position

        stand_atlas = new TextureAtlas(Gdx.files.internal("moose_stand.atlas")); /*reference atlas*/
        stand_animation = new Animation(1/10f,stand_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/
        walk_atlas = new TextureAtlas(Gdx.files.internal("bear_walk.atlas")); /*reference atlas*/
        walk_animation = new Animation(1/10f,walk_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/
        deer_death_atlas = new TextureAtlas(Gdx.files.internal("deer_death.atlas")); /*reference atlas*/
        deer_death_animation = new Animation(1/10f,deer_death_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/
        deer_walk_atlas = new TextureAtlas(Gdx.files.internal("deer_walk.atlas")); /*reference atlas*/
        deer_walk_animation = new Animation(1/10f,deer_walk_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/
        deer_walk_flip_atlas = new TextureAtlas(Gdx.files.internal("deer_walk_flip.atlas")); /*reference atlas*/
        deer_walk_flip_animation = new Animation(1/10f,deer_walk_flip_atlas.getRegions()); /*30 images per second, */
        stand_flip_atlas = new TextureAtlas(Gdx.files.internal("deer_stand_flip.atlas")); /*reference atlas*/
        stand_flip_animation = new Animation(1/10f,stand_flip_atlas.getRegions()); /*30 images per second, walk_atlas.getRegions() is images*/

    }

    public void setPosition(float x, float y){

        bottom.x = x;
        bottom.y = y;
        carcass_sprite.setPosition(x,y);
        current_x = x;
        current_y = y;
        head.x = x + 20;
        head.y = y;
        back.y = y;
        back.x = x + 20;
        full.y = y;
        full.x = x + 30;

        if(direction == -1){

            head.x = head.x + 80;
            back.x = back.x - 80;

        }

    }

    public Boolean hits(Rectangle r){

        if(bottom.overlaps(r)){
            return true;
        }
        return false;


    }

    public Boolean hits_back(Rectangle r){

        if(back.overlaps(r)){

            return true;
        }
        return false;


    }

    public Boolean hits_head(Rectangle r){

        if(head.overlaps(r)){
            return true;
        }
        return false;


    }

    public void moveRight(float delta){

        direction = -1;
        current_x = current_x + (100 * delta);
        bottom.x = current_x;
        setPosition(current_x, current_y);

    }

    public void moveLeft(float delta){

        direction = 1;
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

    public void deer_dead(){

        dead = true;

    }

    public void deer_set_direction(int d){

        direction = d;

    }

    public void deer_gone(){
        dead = false;
        eaten = true;

    }

    public Texture get_texture(){

        return carcass_texture;

    }

    public Sprite get_sprite(){

        return carcass_sprite;

    }

    public float get_x_coord(){

        return current_x;
    }

    public Boolean get_gone(){

        return eaten;
    }



    public void draw(SpriteBatch batch,float delta,Boolean see_player){


        if(see_player && !dead && !eaten) {
            int i = 0;




            batch.draw(deer_walk_flip_animation.getKeyFrame(delta, true), current_x, current_y);

            current_x = (current_x + (7));
            bottom.x = current_x;
            setPosition(current_x, current_y);


        }

        else if(!dead && !eaten) {

            if(direction == 1) {
                batch.draw(stand_animation.getKeyFrame(delta, true), current_x, current_y);
                // direction = -1;
//            frame = walk_animation.getKeyFrame(delta,true);
               /* current_x = (current_x - (2));
*/              setPosition(current_x, current_y);
            }

            else if(direction == -1){

                batch.draw(stand_flip_animation.getKeyFrame(delta, true), current_x, current_y);
                // direction = -1;
//            frame = walk_animation.getKeyFrame(delta,true);
                /*current_x = (current_x + (2));
                bottom.x = current_x;
                setPosition(current_x, current_y);*/
                setPosition(current_x, current_y);

            }

        }
        else if(dead)
            batch.draw(deer_death_animation.getKeyFrame(delta,false),current_x,current_y);
        else if(eaten)
            carcass_sprite.draw(batch);

    }

    public void draw_walk(SpriteBatch batch,float delta){

        batch.draw(walk_animation.getKeyFrame(delta,true),current_x,current_y);

    }

    public void draw_attack(SpriteBatch batch,float delta){

        batch.draw(walk_animation.getKeyFrame(delta,true),current_x,current_y);

    }

    public Rectangle get_back(){

        return back;
    }

    public Rectangle get_head(){

        return head;
    }

    public Rectangle get_full(){

        return full;
    }

    public float[] get_location(){ /*Return current location in array*/

        float loc [];

        loc = new float[2];

        loc[0] = current_x;
        loc[1] = current_y;

        return loc;

    }

    public int get_direction(){

        return direction;
    }

    public Boolean get_dead(){

        if(dead || eaten)
            return true;

        return false;
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
