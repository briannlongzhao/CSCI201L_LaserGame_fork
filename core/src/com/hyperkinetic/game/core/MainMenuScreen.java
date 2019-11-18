package com.hyperkinetic.game.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

//import javax.xml.soap.Text;

public class MainMenuScreen  extends InputAdapter implements Screen {
    LaserGame game;
    private Stage stage;
    private SpriteBatch batch;
    private Label outputLabel;
    private Texture backgroundPic;
    private Texture titlePic;
    private int width;
    private int height;

    public MainMenuScreen (final LaserGame game) {
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
        // constructor
        this.game = game;

        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();

        backgroundPic = new Texture(Gdx.files.internal("tempBackground.png"));
        titlePic = new Texture(Gdx.files.internal("LaserGameTitle.png"));

        Skin neon = new Skin(Gdx.files.internal("skin/neon-ui.json"));

        Button login = new TextButton("LOG IN", neon);
        login.setSize(200,100);
        login.setPosition(width/2 - 100, height/2);
        login.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LaserGameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(login);


        Button guest = new TextButton("GUEST", neon);
        guest.setSize(200,100);
        guest.setPosition(width/2 - 100, height/2 + 100);
        guest.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LaserGameScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(guest);


        Button quit = new TextButton("QUIT", neon);
        quit.setSize(200,100);
        quit.setPosition(width/2 - 100, height/2 - 200);
        quit.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(quit);


        Button settings = new TextButton("SETTINGS", neon);
        settings.setSize(200,100);
        settings.setPosition(width/2 - 100, height/2 - 100);
        settings.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new SettingsScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(settings);


        // test propose
        outputLabel = new Label("Press a Button",neon);
        outputLabel.setSize(100,100);
        outputLabel.setPosition(0,500);
        outputLabel.setAlignment(Align.center);
        // test propose

        stage.addActor(outputLabel);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255,255,255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(backgroundPic, 0, 0, width, height);
        stage.getBatch().draw(titlePic, width / 2 - 958/ 2 , height - 4 * 86);
        stage.getBatch().end();

        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}