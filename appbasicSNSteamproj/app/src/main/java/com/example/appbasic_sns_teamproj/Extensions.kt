package com.example.appbasic_sns_teamproj

import android.app.Activity

enum class Direction {
    STAY, LEFT, UP, RIGHT, DOWN
}

fun Activity.setSlide(direction: Direction) {
    when (direction) {
        Direction.STAY -> overridePendingTransition(R.anim.stay, R.anim.stay)
        Direction.LEFT -> overridePendingTransition(R.anim.from_left_enter, R.anim.to_left_exit)
        Direction.UP -> overridePendingTransition(R.anim.from_up_enter, R.anim.to_up_exit)
        Direction.RIGHT -> overridePendingTransition(R.anim.from_right_enter, R.anim.to_right_exit)
        Direction.DOWN -> overridePendingTransition(R.anim.from_down_enter, R.anim.to_down_exit)
    }
}