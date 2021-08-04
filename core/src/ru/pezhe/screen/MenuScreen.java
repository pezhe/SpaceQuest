package ru.pezhe.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import ru.pezhe.base.BaseScreen;
import ru.pezhe.math.Rect;
import ru.pezhe.sprite.Background;
import ru.pezhe.sprite.Ship;

public class MenuScreen extends BaseScreen {

    private Texture bg, shipTexture;
    private Background background;
    private Ship ship;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/space.jpg");
        shipTexture = new Texture("textures/ship.png");
        background = new Background(bg);
        ship = new Ship(shipTexture);
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        ship.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        shipTexture.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        ship.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchDragged(Vector2 touch, int pointer) {
        ship.touchDragged(touch, pointer);
        return super.touchDragged(touch, pointer);
    }

}
