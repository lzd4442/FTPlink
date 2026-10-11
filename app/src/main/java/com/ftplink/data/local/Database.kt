package com.ftplink.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "servers")
data class ServerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val type: String,
    val lastPath: String = "/",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "settings")
data class SettingEntity(
    @PrimaryKey
    val key: String,
    val value: String
)

@Dao
interface ServerDao {
    @Query("SELECT * FROM servers ORDER BY createdAt DESC")
    fun getAllServers(): Flow<List<ServerEntity>>

    @Query("SELECT * FROM servers WHERE id = :id")
    suspend fun getServerById(id: Long): ServerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServer(server: ServerEntity): Long

    @Update
    suspend fun updateServer(server: ServerEntity)

    @Delete
    suspend fun deleteServer(server: ServerEntity)

    @Query("DELETE FROM servers WHERE id = :id")
    suspend fun deleteServerById(id: Long)
}

@Dao
interface SettingDao {
    @Query("SELECT value FROM settings WHERE `key` = :key")
    suspend fun getValue(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setValue(setting: SettingEntity)

    @Query("DELETE FROM settings WHERE `key` = :key")
    suspend fun deleteValue(key: String)
}
