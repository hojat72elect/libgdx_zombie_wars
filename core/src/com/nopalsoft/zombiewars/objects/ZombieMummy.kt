package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.physics.box2d.Body
import com.nopalsoft.zombiewars.Assets

class ZombieMummy(body: Body) : Personajes(body) {
    init {
        DURATION_ATTACK = Assets.zombieMummyAttack!!.animationDuration
        DURATION_DEAD = Assets.zombieMummyDie!!.animationDuration + .2f
        VELOCIDAD_WALK = .5f
        DISTANCE_ATTACK = .35f
        DAMAGE = 1
        vidas = 3
        isFacingLeft = true
        tipo = TIPO_NO_RANGO
    }
}
