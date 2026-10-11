package com.ftplink.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ServerEntity::class, SettingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FTPlinkDatabase : RoomDatabase() {
    abstract fun serverDao(): ServerDao
    abstract fun settingDao(): SettingDao
}
