package ru.pezhe.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.pezhe.base.Sprite;

public class Ship extends Sprite {

    private final float SHIP_VELOCITY_VALUE = 0.01f;
    private final float BASE_SHIP_SIZE = 0.2f;
    private final float textureFactor, shipSizeX, shipSizeY;
    private final Vector2 shipPosition, targetPosition, velocity;

    public Ship(Texture texture) {
        super(new TextureRegion(texture));
        textureFactor = texture.getHeight() / (float) texture.getWidth();
        shipSizeX = BASE_SHIP_SIZE;
        shipSizeY = BASE_SHIP_SIZE * textureFactor;
        shipPosition = new Vector2();
        targetPosition = new Vector2();
        velocity = new Vector2();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(regions[0],
                shipPosition.x - shipSizeX / 2,
                shipPosition.y - shipSizeY / 2,
                shipSizeX,
                shipSizeY);
        if (!velocity.isZero()) {
            if ((targetPosition.dst(shipPosition) < SHIP_VELOCITY_VALUE)) {
                shipPosition.set(targetPosition);
                velocity.setZero();
            } else {
                shipPosition.add(velocity);
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
        velocity.setZero().add(targetPosition).sub(shipPosition).nor().scl(SHIP_VELOCITY_VALUE);
    }

}
