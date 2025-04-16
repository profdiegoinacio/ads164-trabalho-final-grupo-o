package br.com.listme.data.repository

import br.com.listme.data.local.ListDao
import br.com.listme.data.model.ListEntity

class ListRepository(private val listDao: ListDao) {
    val allItems = listDao.getAllItems()

    suspend fun insertTask(task: ListEntity) {
        listDao.insertItem(task)
    }

    suspend fun deleteTask(task: ListEntity) {
        listDao.deleteItem(task)
    }

    suspend fun getTaskById(id: Int): ListEntity? {
        return listDao.getItemById(id)
    }
}
