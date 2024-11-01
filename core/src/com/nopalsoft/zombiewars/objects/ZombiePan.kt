package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.physics.box2d.Body
import com.nopalsoft.zombiewars.Assets

class ZombiePan(body: Body) : Personajes(body) {
    init {
        DURATION_ATTACK = Assets.zombiePanAttack!!.animationDuration
        DURATION_DEAD = Assets.zombiePanDie!!.animationDuration + .2F
        VELOCIDAD_WALK = .75F
        DISTANCE_ATTACK = .35F
        DAMAGE = 1
        vidas = 3
        isFacingLeft = true
        tipo = TIPO_NO_RANGO
    }
}
