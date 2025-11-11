package com.azwin.productiveheroes.Model.Fight.Statistic

data class PlayerData(
    val stamina: Int = 1800,
    val MaxStamina: Int = 1800,
    val damage: Int = 1,
    val level: Int = 1,
    val exp: Int = 0,
) {
    val maxExp: Int
        get() = 100 * level
}
