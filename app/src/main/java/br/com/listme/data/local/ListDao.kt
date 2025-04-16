package br.com.listme.data.local

import androidx.room.*
import br.com.listme.data.model.ListEntity

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(task: ListEntity)

    @Delete
    suspend fun deleteItem(task: ListEntity)

    @Query("SELECT * FROM items")
    fun getAllItems(): kotlinx.coroutines.flow.Flow<List<ListEntity>>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItemById(id: Int): ListEntity?
}
