package ru.pezhe.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.pezhe.base.Sprite;
import ru.pezhe.math.Rect;

public class Ship extends Sprite {

    private final float SHIP_BASE_SPEED = 0.01f;
    private final int LEFT_KEY = Input.Keys.A;
    private final int RIGHT_KEY = Input.Keys.D;

    private final Vector2 v;
    private Rect worldBounds;
    private int keyPressed;

    public Ship(TextureAtlas atlas, String regionName) {
        super(atlas.findRegion(regionName), 195, 287);
        v = new Vector2();
    }

    @Override
    public void update(float delta) {
        checkAndHandleBounds();
        pos.add(v);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        float height = 0.2f;
        setHeightProportion(height);
        pos.set(pos.x, this.worldBounds.getBottom() + halfHeight);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        defineDirection(touch);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        defineDirection(touch);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        stop();
        return false;
    }

    public void keyDown(int keycode) {
        keyPressed = keycode;
        switch(keycode) {
            case LEFT_KEY: {
                goLeft();
                break;
            }
            case RIGHT_KEY: {
                goRight();
                break;
            }
        }
    }

    public void keyUp(int keycode) {
        if (keycode == keyPressed) {
            stop();
            keyPressed = 0;
        }
    }

    private void defineDirection(Vector2 touch) {
        if (touch.x > 0) goRight();
        else if (touch.x < 0) goLeft();
        else stop();
    }

    private void goLeft() {
        v.x = -SHIP_BASE_SPEED;
    }

    private void goRight() {
        v.x = SHIP_BASE_SPEED;
    }

    private void stop() {
        v.setZero();
    }

    private void checkAndHandleBounds() {
        if (getLeft() - worldBounds.getLeft() < v.len() && v.x < 0) {
            pos.set(worldBounds.getLeft() + halfWidth, pos.y);
        }
        if (worldBounds.getRight() - getRight() < v.len() && v.x > 0) {
            pos.set(worldBounds.getRight() - halfWidth, pos.y);
        }
    }

}
