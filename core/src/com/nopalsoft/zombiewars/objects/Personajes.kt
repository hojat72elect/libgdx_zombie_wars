package com.nopalsoft.zombiewars.objects

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body

open class Personajes(var body: Body) {
    val DURATION_HURT = .3F
    @JvmField
    var state = 0
    @JvmField
    var tipo = 0
    @JvmField
    var DURATION_ATTACK = 0F
    @JvmField
    var DURATION_DEAD = 0F
    @JvmField
    var DAMAGE = 0
    @JvmField
    var DISTANCE_ATTACK = 0F
    @JvmField
    var position = Vector2(body.position.x, body.position.y)
    @JvmField
    var stateTime = 0F
    @JvmField
    var vidas = 0
    var attack = false // solo puede herir una vez cada vez que atacca

    /**
     * Los buenos caminan a la derecha
     */
    @JvmField
    var isFacingLeft = false
    @JvmField
    protected var TIME_TO_ATTACK_AGAIN = 0F
    @JvmField
    var VELOCIDAD_WALK = 0F

    init {
        state = STATE_NORMAL
        stateTime = 0f
    }

    fun update(delta: Float) {
        body.isAwake = true
        position.x = body.position.x
        position.y = body.position.y
        val velocity = body.linearVelocity

        if (state == STATE_DEAD) {
            stateTime += delta
            body.setLinearVelocity(0f, velocity.y)
            return
        } else if (state == STATE_HURT) {
            stateTime += delta
            if (stateTime >= DURATION_HURT) {
                state = STATE_NORMAL
                stateTime = 0f
            }
            body.setLinearVelocity(0f, velocity.y)
            return
        } else if (state == STATE_ATTACK) {
            stateTime += delta
            if (stateTime >= DURATION_ATTACK) {
                if ((stateTime - DURATION_ATTACK) >= TIME_TO_ATTACK_AGAIN) {
                    state = STATE_NORMAL
                    stateTime = 0f
                }
            }
            body.setLinearVelocity(0f, velocity.y)
            return
        }
        if (isFacingLeft) velocity.x = -VELOCIDAD_WALK
        else velocity.x = VELOCIDAD_WALK
        body.linearVelocity = velocity
        stateTime += delta
    }

    fun getHurt(damage: Int) {
        if (state != STATE_DEAD) {
            vidas -= damage
            if (vidas <= 0) {
                state = STATE_DEAD
                stateTime = 0f
            } else {
                if (state != STATE_HURT) {
                    state = STATE_HURT
                    stateTime = 0f
                }
            }
        }
    }

    fun die() {
        if (state != STATE_DEAD) {
            state = STATE_DEAD
            stateTime = 0f
        }
    }

    /**
     * Regresa si si ataco.
     */
    fun attack(): Boolean {
        if (state == STATE_NORMAL) {
            state = STATE_ATTACK
            attack = true
            stateTime = 0F
            return true
        }
        return false
    }

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_HURT = 1
        const val STATE_ATTACK = 2
        const val STATE_DEAD = 3
        const val TIPO_RANGO = 0
        const val TIPO_NO_RANGO = 1
    }
}
