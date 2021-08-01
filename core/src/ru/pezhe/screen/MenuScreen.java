package ru.pezhe.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.pezhe.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private final float SHIP_VELOCITY_VALUE = 3.0f;

    private Texture background;
    private Texture ship;
    private Vector2 shipPosition;
    private Vector2 targetPosition;
    private Vector2 velocity;

    @Override
    public void show() {
        super.show();
        background = new Texture("space.jpg");
        ship = new Texture("ship.png");
        shipPosition = new Vector2(Gdx.graphics.getWidth()/2.0f - 44, Gdx.graphics.getHeight()/2.0f - 23);
        targetPosition = new Vector2();
        velocity = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(ship, shipPosition.x, shipPosition.y, 88, 46);
        batch.end();
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
    public void dispose() {
        super.dispose();
        background.dispose();
        ship.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        setNewCourse(screenX, screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        setNewCourse(screenX, screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }

    private void setNewCourse(int x, int y) {
        targetPosition.set(x - 44, Gdx.graphics.getHeight() - y - 23);
        velocity.setZero().add(targetPosition).sub(shipPosition).nor().scl(SHIP_VELOCITY_VALUE);
    }
}
