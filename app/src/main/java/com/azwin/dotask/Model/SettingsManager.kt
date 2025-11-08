package com.azwin.dotask.Data // Sesuaikan package-mu

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.azwin.dotask.Model.Fight.Statistic.PlayerData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "game_progress_final")

class SettingsManager(private val context: Context) {

    companion object {
        val PLAYER_LEVEL = intPreferencesKey("player_level")
        val PLAYER_EXP = intPreferencesKey("player_exp")
        val PLAYER_STAMINA = intPreferencesKey("player_stamina")
        val MONSTER_HP = intPreferencesKey("monster_hp")
    }

    suspend fun saveGameStats(player: PlayerData, monsterHp: Int) {
        context.dataStore.edit { settings ->
            settings[PLAYER_LEVEL] = player.level
            settings[PLAYER_EXP] = player.exp
            settings[PLAYER_STAMINA] = player.stamina
            settings[MONSTER_HP] = monsterHp
        }
    }

    val gameStatsFlow: Flow<Pair<PlayerData, Int>> = context.dataStore.data.map { preferences ->
        val playerLevel = preferences[PLAYER_LEVEL] ?: 1
        val playerExp = preferences[PLAYER_EXP] ?: 0
        val playerStamina = preferences[PLAYER_STAMINA] ?: 1800
        // Default HP for the first monster (Slime Ghost) is 900
        val monsterHp = preferences[MONSTER_HP] ?: 900

        val player = PlayerData(
            level = playerLevel,
            exp = playerExp,
            stamina = playerStamina
        )

        Pair(player, monsterHp)
    }
}