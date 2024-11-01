package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.physics.box2d.Body
import com.nopalsoft.zombiewars.Assets

class ZombieFrank(body: Body) : Personajes(body) {
    init {
        DURATION_ATTACK = Assets.zombieFrankAttack!!.animationDuration
        DURATION_DEAD = Assets.zombieFrankDie!!.animationDuration + .2F
        VELOCIDAD_WALK = 1F
        DISTANCE_ATTACK = .35F
        DAMAGE = 5
        vidas = 10
        isFacingLeft = true
        tipo = TIPO_NO_RANGO
    }
}
