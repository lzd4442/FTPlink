package com.ftplink.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/ftplink/data/local/FTPlinkDatabase;", "Landroidx/room/RoomDatabase;", "()V", "serverDao", "Lcom/ftplink/data/local/ServerDao;", "settingDao", "Lcom/ftplink/data/local/SettingDao;", "app_debug"})
@androidx.room.Database(entities = {com.ftplink.data.local.ServerEntity.class, com.ftplink.data.local.SettingEntity.class}, version = 1, exportSchema = false)
public abstract class FTPlinkDatabase extends androidx.room.RoomDatabase {
    
    public FTPlinkDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ftplink.data.local.ServerDao serverDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ftplink.data.local.SettingDao settingDao();
}