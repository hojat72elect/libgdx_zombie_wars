package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.physics.box2d.Body
import com.nopalsoft.zombiewars.Assets

class HeroForce(body: Body) : Personajes(body) {
    init {
        DURATION_ATTACK = Assets.heroForceShoot.animationDuration
        DURATION_DEAD = Assets.heroForceDie.animationDuration + .2F
        VELOCIDAD_WALK = 1F
        DAMAGE = 1
        DISTANCE_ATTACK = 2F
        TIME_TO_ATTACK_AGAIN = 2F
        vidas = 5
        isFacingLeft = false
        tipo = TIPO_RANGO
    }
}
