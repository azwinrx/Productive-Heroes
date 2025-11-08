package com.azwin.dotask.Model

import com.azwin.dotask.Model.Fight.Statistic.PlayerData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object PlayerRepository {
    private val _playerState = MutableStateFlow(PlayerData())
    val playerState = _playerState.asStateFlow()

    fun addExp(expToAdd: Int) {
        val newExp = _playerState.value.exp + expToAdd
        _playerState.value = _playerState.value.copy(exp = newExp)
        checkLevelUp()
    }

    fun staminaIncrease(number:Int) {
        val currentStamina = _playerState.value.stamina
        val maxStamina = _playerState.value.MaxStamina
        val newStamina = (currentStamina + number).coerceAtMost(maxStamina)
        _playerState.value = _playerState.value.copy(stamina = newStamina)
    }

    fun staminaDecrease(number:Int){
        val currentStamina = _playerState.value.stamina
        // Make sure stamina isnt < 0
        val newStamina = (currentStamina - number).coerceAtLeast(0)
        _playerState.value = _playerState.value.copy(stamina = newStamina)
    }

    private fun checkLevelUp() {
        if (_playerState.value.exp >= _playerState.value.maxExp) {
            levelUpPlayer()
        }
    }

    private fun levelUpPlayer() {
        val currentPlayer = _playerState.value
        val remainingExp = currentPlayer.exp - currentPlayer.maxExp
        val newLevel = currentPlayer.level + 1

        val newStats = PlayerData(level = newLevel, exp = remainingExp)

        _playerState.value = newStats.copy(stamina = newStats.MaxStamina)
    }
}
