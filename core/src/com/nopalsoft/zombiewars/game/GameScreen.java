package com.nopalsoft.zombiewars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.math.MathUtils;
import com.nopalsoft.zombiewars.Assets;
import com.nopalsoft.zombiewars.MainZombieWars;
import com.nopalsoft.zombiewars.screens.Screens;
import com.nopalsoft.zombiewars.Settings;

public class GameScreen extends Screens {
    static final int STATE_RUNNING = 0;
    public WorldGame oWorld;
    int state;
    WorldGameRenderer2 renderer;

    float accelCamX;

    public GameScreen(MainZombieWars game) {
        super(game);

        oWorld = new WorldGame(0);
        renderer = new WorldGameRenderer2(batcher, oWorld);

    }

    @Override
    public void update(float delta) {

        if (state == STATE_RUNNING) {
            updateRunning(delta);
        }

    }

    private void updateRunning(float delta) {

        if (Gdx.input.isKeyPressed(Keys.A))
            accelCamX = -5;
        else if (Gdx.input.isKeyPressed(Keys.D))
            accelCamX = 5;
        else if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer))
            accelCamX = Gdx.input.getAccelerometerY() * 1.5f;
        else
            accelCamX = 0;

        if (Gdx.input.isKeyPressed(Keys.Z)) {

            Settings.zoom += .025F;
            if (Settings.zoom > 2.105f)
                Settings.zoom = 2.105f;
        } else if (Gdx.input.isKeyPressed(Keys.X)) {
            Settings.zoom -= .025F;
            if (Settings.zoom < 1)
                Settings.zoom = 1;
        }

        oWorld.update(delta, accelCamX);

    }

    @Override
    public void draw(float delta) {
        renderer.render();
        oCam.update();
        batcher.setProjectionMatrix(oCam.combined);

        batcher.begin();
        Assets.fontGrande.draw(batcher, "FPS: " + Gdx.graphics.getFramesPerSecond() + "\nNew hero: E\nZoom: X,Z", 10, 400);
        batcher.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.ESCAPE)
            Gdx.app.exit();
        else if (keycode == Keys.C)
            oWorld.atackaLL();
        else if (keycode == Keys.V)
            oWorld.dieALl();
        else if (keycode == Keys.E)
            if (MathUtils.randomBoolean()) {
                oWorld.objectCreatorManager.creatHeroLumber();
            } else if (MathUtils.randomBoolean()) {
                oWorld.objectCreatorManager.creatHeroFarmer();
            } else {
                oWorld.objectCreatorManager.creatHeroForce();
            }
        return super.keyDown(keycode);
    }

}
