package com.lounah.creditsapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lounah.creditsapp.data.database.dao.CreditOffersDao
import com.lounah.creditsapp.data.entity.CreditOffer

@Database(
        entities = [
            CreditOffer::class
        ],
        version = 2,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun creditOffersDao(): CreditOffersDao
}