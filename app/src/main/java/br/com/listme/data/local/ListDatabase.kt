package br.com.listme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.listme.data.model.ListEntity

@Database(entities = [ListEntity::class], version = 1, exportSchema = false)
abstract class ListDatabase : RoomDatabase() {
    abstract fun listDao(): ListDao
}
