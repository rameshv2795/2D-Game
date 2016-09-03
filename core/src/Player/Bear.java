package Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Frame;

/**
 * Created by Vinod on 6/28/2016.
 */
public class Bear {

    private float current_x,current_y, velocity_y;
    private Texture texture;
    private Sprite sprite;
    private int direction = 0;
    private Boolean attack = false, eat = false;

    /*Animations*/
    private TextureAtlas walk_atlas,stand_atlas,death_atlas,walk_flip_atlas,stand_flip_atlas, attack_atlas, attack_atlas_flip, eat_atlas, eat_atlas_flip;
    private Animation walk_animation,stand_animation,death_animation, walk_flip_animation, stand_flip_animation, attack_animation, attack_animation_flip, eat_animation, eat_animation_flip;
    //private float time_passed = 0;

    /*Hit Boxes*/
    Rectangle bottom; /*For ground*/
    Rectangle attack_box,head, eat_box; /*If this hits, enemy dies*/





    private TextureRegion frame;

    public Bear(){ //constructor




        bottom = new Rectangle(1.0f,92.0f,90f,70f);
        head = new Rectangle(1.0f,92.0f,90f,70f);
        attack_box = new Rectangle(11.0f,92.0f,10f,70f);





        eat_box = new Rectangle(11.0f,92.0f,90f,70f);

        velocity_y = 0;
        texture = new Texture(Gdx.files.internal("bear_edit.png"));
        sprite = new Sprite(texture,419,300);
        current_x = 92;
        setPosition(1,92); //need this to init position

        /*Initialize Animations*/
        stand_atlas = new TextureAtlas(Gdx.files.internal("bear_stand.atlas")); /*reference atlas*/
        stand_animation = new Animation(1/10f,stand_atlas.getRegions()); /*10 images per second, walk_atlas.getRegions() is images*/
        stand_flip_atlas = new TextureAtlas(Gdx.files.internal("stand_flip.atlas")); /*reference atlas*/
        stand_flip_animation = new Animation(1/10f,stand_flip_atlas.getRegions()); /*10 images per second, walk_atlas.getRegions() is images*/
        walk_atlas = new TextureAtlas(Gdx.files.internal("bear_walk.atlas")); /*reference atlas*/
        walk_animation = new Animation(1/10f,walk_atlas.getRegions()); /*10 images per second, walk_atlas.getRegions() is images*/
        walk_flip_atlas = new TextureAtlas(Gdx.files.internal("bear_walk_flip.atlas")); /*reference atlas*/
        walk_flip_animation = new Animation(1/10f,walk_flip_atlas.getRegions()); /*10 images per second, walk_atlas.getRegions() is images*/
        death_atlas = new TextureAtlas(Gdx.files.internal("bear_death.atlas")); /*reference atlas*/
        death_animation = new Animation(2f,death_atlas.getRegions()); /*10 images per second, walk_atlas.getRegions() is images*/
        attack_atlas = new TextureAtlas(Gdx.files.internal("bear_attack.atlas")); /*reference atlas*/
        attack_animation = new Animation(1/10f,attack_atlas.getRegions());
        attack_atlas_flip = new TextureAtlas(Gdx.files.internal("bear_attack_flip.atlas")); /*reference atlas*/
        attack_animation_flip = new Animation(1/10f,attack_atlas_flip.getRegions());
        eat_atlas = new TextureAtlas(Gdx.files.internal("eat.atlas")); /*reference atlas*/
        eat_animation = new Animation(1/7f,eat_atlas.getRegions());
        eat_atlas_flip = new TextureAtlas(Gdx.files.internal("eat_flip.atlas")); /*reference atlas*/
        eat_animation_flip = new Animation(1/7f,eat_atlas_flip.getRegions());



    }

    public void setPosition(float x, float y){

        current_x = x;
        current_y = y;
        attack_box.x = x + 10;
        attack_box.y = y;
        eat_box.x = x + 10;
        eat_box.y = y;

        if(direction == -1){

            attack_box.x = attack_box.x - 80;
            eat_box.x = eat_box.x - 80;

        }

        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x,y);


    }

    public Boolean hits(Rectangle r){

        if(bottom.overlaps(r)){
            return true;
        }
        return false;


    }

    public Boolean hits_enemy (Rectangle r){

        if(bottom.overlaps(r)){
            return true;
        }
        return false;


    }

    public Boolean bear_bush(Rectangle r){

        if(bottom.overlaps(r)){
            return true;
        }
        return false;

    }

    public Boolean kills_enemy (Rectangle r){

        if(attack_box.overlaps(r)){
            return true;
        }
        return false;


    }

    public Boolean eats_enemy (Rectangle r){

        if(eat_box.overlaps(r)){
            return true;
        }
        return false;


    }

    public void moveRight(float delta){

        direction = 1;
        current_x = current_x + (100 * delta);
        bottom.x = current_x;
        setPosition(current_x, current_y);

    }

    public void moveLeft(float delta){

        direction = -1;
        frame = walk_animation.getKeyFrame(delta,true);
        current_x = (current_x - (100 * delta));
        bottom.x = current_x;
        setPosition(current_x, current_y);

    }

    public void jump(){
        velocity_y = 10;
        bottom.y = current_y + 10;
        setPosition(current_x, current_y+10);
    }

    public void is_attack(){
        attack = true;

    }

    public void is_eat(){

        eat = true;

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

    public float get_y_coord(){

        return current_y;
    }


    public void draw(SpriteBatch batch,float delta){

        if(!attack && !eat) {
            if (direction == 1 || direction == 0)
                batch.draw(stand_animation.getKeyFrame(delta, false), current_x, current_y); /*false: so doesnt repeat anim*/
            else if (direction == -1)
                batch.draw(stand_flip_animation.getKeyFrame(delta, false), current_x, current_y);

        }

        else if(attack){

            if (direction == 1 || direction == 0) {
                batch.draw(attack_animation.getKeyFrame(delta, true), current_x, current_y);
                attack = false;

            }

            else if (direction == -1){
                batch.draw(attack_animation_flip.getKeyFrame(delta, true), current_x, current_y);
                attack = false;

            }
        }

        else if(eat){

            if (direction == 1 || direction == 0) {
                batch.draw(eat_animation.getKeyFrame(delta, true), current_x, current_y);
                eat = false;

            }

            else if (direction == -1){
                batch.draw(eat_animation_flip.getKeyFrame(delta, true), current_x, current_y);
                eat = false;

            }
        }



    }

    public void draw_walk(SpriteBatch batch,float delta){

        if(direction == 1)
            batch.draw(walk_animation.getKeyFrame(delta,true),current_x,current_y);
        else if(direction == -1)
            batch.draw(walk_flip_animation.getKeyFrame(delta,true),current_x,current_y);
    }

    public void draw_death(SpriteBatch batch,float delta){

        death_animation.setPlayMode(Animation.PlayMode.REVERSED);
        batch.draw(death_animation.getKeyFrame(delta,false),current_x,current_y);

    }

    public void draw_attack(SpriteBatch batch,float delta){

        batch.draw(attack_animation.getKeyFrame(delta,true),current_x,current_y);

    }



    public void update(float delta){

            /*Gravity*/

        if(current_y > 92) {

            velocity_y = velocity_y - (50 * delta); /*50 pixles per second*/
            bottom.y = bottom.y + velocity_y;
            setPosition(current_x, current_y + velocity_y);

        }

    }


    public Rectangle get_hitbox(){


        return bottom;
    }
    public Rectangle get_attack_hitbox(){

        return attack_box;
    }


}

