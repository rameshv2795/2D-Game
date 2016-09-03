package Surfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

/**
 * Created by Vinod on 6/28/2016.
 */
public class Bush{


    private float current_x,current_y;
    private Texture texture;
    private Sprite sprite;

    private Rectangle bush_box;




    public Bush(float x, float y){

        texture = new Texture(Gdx.files.internal("bush.png"));
        sprite = new Sprite(texture,150,130);
        bush_box = new Rectangle(x,y,85,130);




    }

    public void setPosition(float x, float y){

        //   bottom.x = x;
        //   bottom.y = y;
        sprite.setPosition(x,y);
        current_x = x;
        current_y = y;

    }


    public void draw(SpriteBatch batch){

        sprite.draw(batch);

    }

    public Rectangle get_bush_box(){

        return bush_box;

    }



}
