package com.azwin.productiveheroes.ViewModel.Fight

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.azwin.productiveheroes.Data.SettingsManager
import com.azwin.productiveheroes.Model.Fight.Statistic.MonsterData
import com.azwin.productiveheroes.Model.Fight.Statistic.PlayerData
import com.azwin.productiveheroes.Model.Fight.TimerData
import com.azwin.productiveheroes.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

open class TimerViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsManager = SettingsManager(application)

    //Monster Detail
    val allMonsters = listOf(
        MonsterData(
            id = 1,
            name = "Slime Ghost",
            maxHp = 900,
            expGain = 50,
            imageRes = R.drawable.slimeghost
        ),
        MonsterData(
            id = 2,
            name = "Baby Lizard",
            maxHp = 1200,
            expGain = 100,
            imageRes = R.drawable.babylizard
        ),
        MonsterData(
            id = 3,
            name = "Evil Tree",
            maxHp = 1500,
            expGain = 150,
            imageRes = R.drawable.eviltree
        ),
        MonsterData(
            id = 4,
            name = "The Unknown",
            maxHp = 1800,
            expGain = 200,
            imageRes = R.drawable.theunknown
        ),
    )

    private val _monster = mutableStateOf(allMonsters.first())
    open val monster: State<MonsterData> = _monster

    private val _monsterHp = mutableStateOf(_monster.value.maxHp)
    open val monsterHp: State<Int> = _monsterHp

    private val _player = MutableStateFlow(PlayerData())
    val player = _player.asStateFlow()

    private val _timer = mutableStateOf(TimerData())
    open val timer: State<TimerData> = _timer

    private var timerJob: Job? = null

    init {
        viewModelScope.launch {
            val (playerData, savedMonsterHp) = settingsManager.gameStatsFlow.first()
            _player.value = playerData
            // Make sure the loaded monster HP doesn't exceed the max HP of the default monster
            val defaultMonsterMaxHp = _monster.value.maxHp
            _monsterHp.value = if (savedMonsterHp > defaultMonsterMaxHp) defaultMonsterMaxHp else savedMonsterHp
        }
    }

    private fun saveGameStats() {
        viewModelScope.launch {
            settingsManager.saveGameStats(_player.value, _monsterHp.value)
        }
    }

    open fun selectMonster(selectedMonster: MonsterData) {
        timerJob?.cancel()
        _timer.value = _timer.value.copy(isTimerRunning = false, isRestRunning = false)
        _monster.value = selectedMonster
        _monsterHp.value = selectedMonster.maxHp
        saveGameStats()
    }

    open fun onAttackClicked() {
        if (player.value.stamina <= 0) return
        timerJob?.cancel()
        _timer.value = _timer.value.copy(isTimerRunning = true)
        startTimer()
    }

    open fun onPauseClicked() {
        timerJob?.cancel()
        _timer.value = _timer.value.copy(isTimerRunning = false)
    }

    open fun onResetClicked() {
        timerJob?.cancel()
        _timer.value = _timer.value.copy(isTimerRunning = false)
        _monsterHp.value = _monster.value.maxHp
        saveGameStats()
    }

    open fun onRestClicked() {
        if (_timer.value.isTimerRunning) return
        timerJob?.cancel()
        _timer.value = _timer.value.copy(isTimerRunning = false, isRestRunning = true)
        timerJob = viewModelScope.launch {
            while (_player.value.stamina < _player.value.MaxStamina) {
                delay(1.seconds)
                val newStamina = (_player.value.stamina + 6).coerceAtMost(_player.value.MaxStamina)
                _player.value = _player.value.copy(stamina = newStamina)
                saveGameStats()
            }
            _timer.value = _timer.value.copy(isRestRunning = false)
        }
    }

    open fun onRestCancelCLicked() {
        timerJob?.cancel()
        _timer.value = _timer.value.copy(isRestRunning = false)
    }

    private fun startTimer() {
        timerJob = viewModelScope.launch {
            while (_monsterHp.value > 0) {
                if (_player.value.stamina <= 0) {
                    onPauseClicked()
                    break
                }
                delay(1.seconds)
                _monsterHp.value -= _player.value.damage
                _player.value = _player.value.copy(stamina = _player.value.stamina - 1)
                saveGameStats()
            }

            if (_monsterHp.value <= 0) {
                addExp(monster.value.expGain)
                // Reset monster HP for the next fight
                _monsterHp.value = _monster.value.maxHp 
                saveGameStats()
            }

            _timer.value = _timer.value.copy(isTimerRunning = false)
        }
    }

    open fun addExp(exp: Int) {
        val newExp = _player.value.exp + exp
        if (newExp >= _player.value.maxExp) {
            val remainingExp = newExp - _player.value.maxExp
            _player.value = _player.value.copy(level = _player.value.level + 1, exp = remainingExp)
        } else {
            _player.value = _player.value.copy(exp = newExp)
        }
        saveGameStats()
    }
}