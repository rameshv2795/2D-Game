package Surfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Vinod on 6/28/2016.
 */
public class Floor{


    private float current_x,current_y;
    private Texture texture;
    private Sprite sprite;


    public Floor(){

        texture = new Texture(Gdx.files.internal("floor.jpg"));
        sprite = new Sprite(texture,270,100);

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

}
