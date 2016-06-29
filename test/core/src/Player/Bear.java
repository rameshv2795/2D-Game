package Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Vinod on 6/28/2016.
 */
public class Bear {

    private float current_x,current_y;
    private Texture texture;
    private Sprite sprite;
    Rectangle bottom;

    public Bear(){ //constructor

     //   bottom = new Rectangle(0.0f,0.0f,128.0f,128.0f);


        texture = new Texture(Gdx.files.internal("bear_edit.png"));
        sprite = new Sprite(texture,419,300);
        current_x = 1;
        current_y = 1;
        sprite.setPosition(1,1);


    }

    public void setPosition(float x, float y){

     //   bottom.x = x;
     //   bottom.y = y;
        sprite.setPosition(x,y);
        current_x = x;
        current_y = y;

    }

    public void moveRight(float delta){

        current_x = current_x + (1000 * delta);
        setPosition(current_x, current_y);

    }

    public void moveLeft(){

    }

    public Texture get_texture(){

        return texture;

    }

    public Sprite get_sprite(){

        return sprite;

    }

    public void draw(SpriteBatch batch){

        sprite.draw(batch);

    }


}
