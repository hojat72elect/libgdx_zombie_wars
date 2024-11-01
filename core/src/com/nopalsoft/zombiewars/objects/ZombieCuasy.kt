package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.physics.box2d.Body
import com.nopalsoft.zombiewars.Assets

class ZombieCuasy(body: Body) : Personajes(body) {
    init {
        DURATION_ATTACK = Assets.zombieCuasyAttack.animationDuration
        DURATION_DEAD = Assets.zombieCuasyDie.animationDuration + .2F
        VELOCIDAD_WALK = .15F
        DISTANCE_ATTACK = .35F
        DAMAGE = 1
        vidas = 5
        isFacingLeft = true
        tipo = TIPO_NO_RANGO
    }
}
