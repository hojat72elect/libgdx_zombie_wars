package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.physics.box2d.Body
import com.nopalsoft.zombiewars.Assets

class ZombieKid(body: Body) : Personajes(body) {
    init {
        DURATION_ATTACK = Assets.zombieKidAttack!!.animationDuration
        DURATION_DEAD = Assets.zombieKidDie!!.animationDuration + .2F
        VELOCIDAD_WALK = .3F
        DISTANCE_ATTACK = .35F
        DAMAGE = 1
        vidas = 5
        isFacingLeft = true
        tipo = TIPO_NO_RANGO
    }
}
