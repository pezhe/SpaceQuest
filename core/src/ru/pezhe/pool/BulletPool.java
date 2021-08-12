package ru.pezhe.pool;

import ru.pezhe.base.SpritesPool;
import ru.pezhe.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }
}
