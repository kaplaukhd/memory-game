package com.template

data class StageSettings(
    val level: Int,
    val difficulty: Int,
    var isCompleted: Boolean = false,
)

class GetStageLevel() {
    fun getLevel(): ArrayList<StageSettings> {
        val levels = arrayListOf<StageSettings>()
        levels.add(StageSettings(1, 1))
        levels.add(StageSettings(2, 1))
        levels.add(StageSettings(3, 1))
        levels.add(StageSettings(4, 1))
        levels.add(StageSettings(5, 1))
        levels.add(StageSettings(6, 1))
        levels.add(StageSettings(7, 1))
        levels.add(StageSettings(8, 1))
        levels.add(StageSettings(9, 1))
        levels.add(StageSettings(10, 2))
        levels.add(StageSettings(11, 2))
        levels.add(StageSettings(12, 2))
        levels.add(StageSettings(13, 2))
        levels.add(StageSettings(14, 2))
        levels.add(StageSettings(15, 2))
        levels.add(StageSettings(16, 2))
        levels.add(StageSettings(17, 2))
        levels.add(StageSettings(18, 2))
        levels.add(StageSettings(19, 3))
        levels.add(StageSettings(20, 3))
        levels.add(StageSettings(21, 3))
        levels.add(StageSettings(22, 3))
        levels.add(StageSettings(23, 3))
        levels.add(StageSettings(24, 3))
        levels.add(StageSettings(25, 3))
        levels.add(StageSettings(26, 3))
        levels.add(StageSettings(27, 3))
        levels.add(StageSettings(28, 4))
        levels.add(StageSettings(29, 4))
        levels.add(StageSettings(30, 4))
        levels.add(StageSettings(31, 4))
        levels.add(StageSettings(32, 4))
        levels.add(StageSettings(33, 4))
        levels.add(StageSettings(34, 4))
        levels.add(StageSettings(35, 4))
        levels.add(StageSettings(36, 4))
        return levels
    }
}