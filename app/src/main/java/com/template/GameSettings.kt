package com.template

data class GameSettings(
    val cardCount: Int
){
    companion object{
        val DEFAULT = GameSettings(4)
    }
}