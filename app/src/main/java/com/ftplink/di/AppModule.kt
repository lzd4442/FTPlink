package com.ftplink.di

import android.content.Context
import androidx.room.Room
import com.ftplink.data.local.FTPlinkDatabase
import com.ftplink.data.local.ServerDao
import com.ftplink.data.local.SettingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FTPlinkDatabase {
        return Room.databaseBuilder(
            context,
            FTPlinkDatabase::class.java,
            "ftplink.db"
        ).build()
    }

    @Provides
    fun provideServerDao(database: FTPlinkDatabase): ServerDao = database.serverDao()

    @Provides
    fun provideSettingDao(database: FTPlinkDatabase): SettingDao = database.settingDao()
}
