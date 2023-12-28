package com.example.appbasic_sns_teamproj

import android.app.Activity

enum class Direction {
    STAY, LEFT, UP, RIGHT, DOWN
}

fun Activity.setSlide(enterDirection: Direction, exitDirection: Direction) {
    var enter = 0
    var exit = 0
    enter = when (enterDirection) {
        Direction.STAY -> R.anim.stay
        Direction.LEFT -> R.anim.from_left_enter
        Direction.UP -> R.anim.from_up_enter
        Direction.RIGHT -> R.anim.from_right_enter
        Direction.DOWN -> R.anim.from_down_enter
    }
    exit = when (exitDirection) {
        Direction.STAY -> R.anim.stay
        Direction.LEFT -> R.anim.to_left_exit
        Direction.UP -> R.anim.to_up_exit
        Direction.RIGHT -> R.anim.to_right_exit
        Direction.DOWN -> R.anim.to_down_exit
    }
    overridePendingTransition(enter, exit)
}