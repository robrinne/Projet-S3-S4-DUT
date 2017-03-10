package com.mygdx.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Catan;
import com.mygdx.game.model.HUD;
import com.mygdx.game.model.State;

import java.util.ArrayList;

import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/**
 * Created by typhon0 on 01/03/17.
 */

public class GameScreen implements Screen {
    private Catan game;
    public Sprite sprite;
    public FloatArray vertices, v2, v3;
    public Vector2 center;
    public Texture texture, texture2, textureBrick;
    public TextureRegion texture3, texreg;
    public PolygonSprite polygonSprite, polygonSprite2;
    public ArrayList<PolygonRegion> listePolygonRegion;
    public Skin skin;
    public GDXDialogs dialogs;
    public Stage stage;


    private State state = State.RUN; // status du jeu


    public GameScreen(Catan g) {
        this.game = g;
        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));
        dialogs = GDXDialogsSystem.install();
        state = State.RUN;

        stage = new Stage(new ExtendViewport(1920, 1080));

        Gdx.input.setInputProcessor(stage); // Detection des inputs

        skin = new Skin(Gdx.files.internal("ui/glassy-ui.json"));

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

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {

        switch (state) {
            case RUN:
                Gdx.gl.glClearColor(0, 130, 175, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //System.out.println("+ "+plateau.getListeTuiles().size());

                drawBoard();

                stage.act(delta);

                stage.draw();


                break;
            case PAUSE:


                break;
            case RESUME:

                break;

            default:
                break;
        }


    }

    public void drawBoard() {

        game.batch.begin();
        // Affichage de la mer
        game.batch.draw(game.plateau.getTextureMer(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Affichage de chaque Tuile/Terrain
        for (int i = 0; i < game.plateau.getListeTuiles().size(); i++) {
            game.batch.draw(game.plateau.getListeTuiles().get(i).getTextureTuile(),
                    game.plateau.getListeTuiles().get(i).getCoinInferieurGaucheTuile().x,
                    game.plateau.getListeTuiles().get(i).getCoinInferieurGaucheTuile().y,
                    (float) Math.sqrt(3) * game.plateau.TAILLE_TUILE,
                    (float) (game.plateau.TAILLE_TUILE * 2));
        }

        // Affichage de chaque Jeton
        for (int i = 0; i < game.plateau.getListeTuiles().size(); i++) {
            game.batch.draw(game.plateau.getListeTuiles().get(i).getJeton().getTextureJeton(),
                    game.plateau.getListeTuiles().get(i).getCoinInferieurGaucheJeton().x,
                    game.plateau.getListeTuiles().get(i).getCoinInferieurGaucheJeton().y);
        }

        // Affichage de chaque Port
        for (int i = 0; i < game.plateau.getListePorts().size(); i++) {
            game.batch.draw(game.plateau.getListePorts().get(i).getTexturePort(),
                    game.plateau.getListePorts().get(i).getCoinInferieurGauchePort().x,
                    game.plateau.getListePorts().get(i).getCoinInferieurGauchePort().y,
                    64,
                    64);
        }

        game.batch.end();

        // Affichage du squelette en surcouche
        for (int i = 0; i < game.plateau.getListeTuiles().size(); i++) {
            game.sr.begin(ShapeRenderer.ShapeType.Line);
            game.sr.polygon(game.plateau.getListeTuiles().get(i).getVertices());
            game.sr.setColor(0, 0, 0, 1);
            Gdx.gl.glLineWidth(5);
            game.sr.end();


        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            this.state = State.PAUSE;
            quitGameConfirm();

        }

    }

    public void quitGameConfirm() {
        System.out.println("quit");
        GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        bDialog.setTitle("Quitter ?");
        bDialog.setMessage("Voulez-vous vraiment quitter ?");


        bDialog.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
                System.out.println(button);

                if (button == 1) { //Annuler

                } else if (button == 0) { //Quitter et sauvegarder
                    setState(State.RUN);
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new MainMenu(game));
                        }
                    });

                }
            }
        });
        bDialog.addButton(" Sauvegarder et quitter ");
        bDialog.addButton(" Annuler ");

        bDialog.build().show();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void setState(State t) {
        this.state = t;
    }
}
