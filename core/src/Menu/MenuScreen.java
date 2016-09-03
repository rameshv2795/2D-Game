package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Vinod on 8/19/2016.
 */
public class MenuScreen {

    private Texture play;
    private float width, height;
    protected Texture background_texture;
    protected Sprite background_sprite;

    private Boolean new_game = false;
    private OrthographicCamera camera;

    Rectangle new_game_rectangle;

    /*Debug*/
    private Texture d_texture;
    private Sprite d_sprite;

    private int selection = 0;


    public MenuScreen(float w, float h) {



        play = new Texture("menu.png"); /* 1177 x 603 */
        width = w;
        height = h;

        camera = new OrthographicCamera();
        camera.setToOrtho(false,width,height);

        /*Debug*/
        d_texture = new Texture(Gdx.files.internal("debug_box.jpg"));
        d_sprite = new Sprite(d_texture,1000,600);
        d_sprite.setPosition(((width / 2) - (1177 / 2))+257, ((height / 2) - (603 / 2)) + 377);
        d_sprite.setPosition((800/2),400/2);

        background_texture = new Texture("utah.jpg");
        background_sprite = new Sprite(background_texture, 1500, 1000);


        new_game_rectangle = new Rectangle((width/2) - 350,(height/2) + 100,750,100);



    }

    public void render_menu(SpriteBatch batch) {

        background_sprite.draw(batch);
        batch.draw(play, (width / 2) - (1177 / 2), (height / 2) - (603 / 2));
        //d_sprite.draw(batch);

        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

           if(new_game_rectangle.contains(touch.x, touch.y)){


               selection = 1;

              for(int i = 0; i < 50; i++) {
                   System.out.println("BUTTON PRESSED");
                  // System.out.println(height);
               }




            }

        }


    }

    public int get_selection(){

        return selection;

    }

}