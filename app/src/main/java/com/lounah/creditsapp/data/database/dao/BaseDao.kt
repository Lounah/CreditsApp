package com.lounah.creditsapp.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(value: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg values: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(values: List<T>)

    @Delete
    fun delete(value: T)
}