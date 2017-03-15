package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by typhon0 on 15/03/17.
 */


public class HUD {
    public Stage stage;
    private Skin skin;

    public HUD(SpriteBatch sb)  {

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));


        stage = new Stage(new ExtendViewport(1920, 1080));
        //Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setSize(1920, 1080);

        //Text Button
        final Button piocher = new Button(skin, "round");
        table.add(piocher).size(170, 170).padRight(1500);

        final Button stat = new Button(skin, "round");
        table.add(stat).size(170, 170);
        table.row();

        //Text Button
        final Button echange = new Button(skin, "round");
        table.add(echange).size(170, 170).padRight(1500);

        final Button regle = new Button(skin, "help");
        table.add(regle).size(170, 170);
        table.row();

        final Button lancedes = new Button(skin, "round");
        table.add(lancedes).size(170, 170).padRight(1500);

        final Button settings = new Button(skin, "settings");
        table.add(settings).size(170, 170);

        table.row();


        final Button pions = new Button(skin, "round");
        table.add(pions).size(170, 170).padRight(1500);
        final Button quit = new Button(skin, "exit");
        table.add(quit).size(170, 170);
        table.row();

        final Button passtour = new Button(skin, "round");
        table.add(passtour).size(170, 170).padRight(1500);
        table.row();


        //  table.pack();


        stage.addActor(table);

    }

}
