package ru.pezhe.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pezhe.base.Sprite;
import ru.pezhe.math.Rect;

public class Ship extends Sprite {

    private final float SHIP_VELOCITY_VALUE = 0.01f;
    private final Vector2 targetPosition, velocity;

    public Ship(Texture texture) {
        super(new TextureRegion(texture));
        scale = 0.1f;
        targetPosition = new Vector2();
        velocity = new Vector2();
    }

    public void calculatePosition () {
        if (!velocity.isZero()) {
            if ((targetPosition.dst(pos) < SHIP_VELOCITY_VALUE)) {
                pos.set(targetPosition);
                velocity.setZero();
            } else {
                pos.add(velocity);
            }
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        setNewCourse(touch);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        setNewCourse(touch);
        return super.touchDragged(touch, pointer);
    }

    private void setNewCourse(Vector2 touch) {
        targetPosition.set(touch.x, touch.y);
        velocity.setZero().add(targetPosition).sub(pos).nor().scl(SHIP_VELOCITY_VALUE);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }

}
