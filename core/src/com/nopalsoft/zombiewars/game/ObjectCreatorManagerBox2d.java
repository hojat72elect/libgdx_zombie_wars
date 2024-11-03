package com.nopalsoft.zombiewars.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nopalsoft.zombiewars.objects.Bullet;
import com.nopalsoft.zombiewars.objects.HeroFarmer;
import com.nopalsoft.zombiewars.objects.HeroForce;
import com.nopalsoft.zombiewars.objects.HeroLumber;
import com.nopalsoft.zombiewars.objects.Personajes;
import com.nopalsoft.zombiewars.objects.ZombieCuasy;
import com.nopalsoft.zombiewars.objects.ZombieFrank;
import com.nopalsoft.zombiewars.objects.ZombieKid;
import com.nopalsoft.zombiewars.objects.ZombieMummy;
import com.nopalsoft.zombiewars.objects.ZombiePan;

public class ObjectCreatorManagerBox2d {

    WorldGame oWorld;
    World oWorldBox;

    public ObjectCreatorManagerBox2d(WorldGame oWorld) {
        this.oWorld = oWorld;
        oWorldBox = oWorld.oWorldBox;
    }

    public void createZombieKid() {
        createZombieMalo(ZombieKid.class);
    }

    public void createZombieCuasy() {
        createZombieMalo(ZombieCuasy.class);
    }

    public void createZombieMummy() {
        createZombieMalo(ZombieMummy.class);
    }

    public void createZombiePan() {
        createZombieMalo(ZombiePan.class);
    }

    public void createZombieFrank() {
        createZombieMalo(ZombieFrank.class);
    }

    public void createHeroForce() {
        createHero(HeroForce.class);
    }

    public void createHeroFarmer() {
        createHero(HeroFarmer.class);
    }

    public void createHeroLumber() {
        createHero(HeroLumber.class);
    }

    private void createZombieMalo(Class<?> zombieType) {
        Personajes obj = null;

        BodyDef bd = new BodyDef();
        bd.position.set(16, 1.6f);
        bd.type = BodyType.DynamicBody;

        Body oBody = oWorldBox.createBody(bd);

        if (zombieType == ZombieKid.class) {
            obj = new ZombieKid(oBody);
        } else if (zombieType == ZombieCuasy.class) {
            obj = new ZombieCuasy(oBody);
        } else if (zombieType == ZombieMummy.class) {
            obj = new ZombieMummy(oBody);
        } else if (zombieType == ZombiePan.class) {
            obj = new ZombiePan(oBody);
        } else if (zombieType == ZombieFrank.class) {
            obj = new ZombieFrank(oBody);
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.17f, .32f);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 8;
        fixture.friction = 0;
        fixture.filter.groupIndex = -1;
        oBody.createFixture(fixture);

        oBody.setFixedRotation(true);
        oBody.setUserData(obj);
        oWorld.arrFacingLeft.add(obj);

        shape.dispose();

    }

    private void createHero(Class<?> heroType) {
        Personajes obj = null;

        BodyDef bd = new BodyDef();
        bd.position.set(1, 1.6f);
        bd.type = BodyType.DynamicBody;

        Body oBody = oWorldBox.createBody(bd);

        if (heroType == HeroForce.class) {
            obj = new HeroForce(oBody);
        } else if (heroType == HeroFarmer.class) {
            obj = new HeroFarmer(oBody);
        } else if (heroType == HeroLumber.class) {
            obj = new HeroLumber(oBody);
        }

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.17f, .32f);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 8;
        fixture.friction = 0;
        fixture.filter.groupIndex = -1;
        oBody.createFixture(fixture);

        oBody.setFixedRotation(true);
        oBody.setUserData(obj);
        oWorld.arrFacingRight.add(obj);

        shape.dispose();

    }

    public void createBullet(Personajes oPerWhoFired) {
        Bullet obj;
        BodyDef bd = new BodyDef();

        if (oPerWhoFired.tipo == Personajes.TIPO_RANGO) {
            if (oPerWhoFired.isFacingLeft) {
                bd.position.set(oPerWhoFired.position.x - .42f, oPerWhoFired.position.y - .14f);
            } else {
                bd.position.set(oPerWhoFired.position.x + .42f, oPerWhoFired.position.y - .14f);
            }
        } else
            bd.position.set(oPerWhoFired.position.x, oPerWhoFired.position.y);

        bd.type = BodyType.DynamicBody;
        Body oBody = oWorldBox.createBody(bd);

        obj = new Bullet(oBody, oPerWhoFired);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.1f, .1f);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 1;
        fixture.isSensor = true;
        oBody.createFixture(fixture);

        oBody.setFixedRotation(true);
        oBody.setUserData(obj);
        oBody.setBullet(true);
        oBody.setGravityScale(0);
        oWorld.arrBullets.add(obj);

    }

}
