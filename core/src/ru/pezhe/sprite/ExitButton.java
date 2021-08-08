package ru.pezhe.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.pezhe.base.BaseButton;
import ru.pezhe.math.Rect;

public class ExitButton extends BaseButton {

    private static final float PADDING = 0.03f;

    public ExitButton(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.18f);
        setBottom(worldBounds.getBottom() + PADDING);
        setRight(worldBounds.getRight() - PADDING);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
