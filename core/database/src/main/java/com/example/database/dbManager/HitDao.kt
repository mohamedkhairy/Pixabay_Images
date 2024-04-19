package com.example.database.dbManager

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.database.entity.HitCachedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HitDao {

    @Query("SELECT * FROM hit_cached")
    fun getAllCashedHits(): Flow<List<HitCachedEntity>?>


    @Upsert
    fun save(hitCachedEntity: List<HitCachedEntity>)


    @Query("SELECT * FROM hit_cached WHERE tags LIKE '%' || :tagKey || '%'")
    fun getAllContainingTag(tagKey: String): Flow<List<HitCachedEntity>?>


}