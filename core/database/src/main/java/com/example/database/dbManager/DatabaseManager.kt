package com.example.database.dbManager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.entity.HitCachedEntity


@Database(entities = [HitCachedEntity::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun cachedHitDao(): HitDao

}
