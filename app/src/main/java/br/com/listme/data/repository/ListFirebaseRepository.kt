package br.com.listme.data.repository

import android.util.Log
import br.com.listme.data.model.List
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

class ListFirebaseRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val listCollection = firestore.collection(LIST_COLLECTION)
    private val _itemsFlow = MutableStateFlow<kotlin.collections.List<List>>(emptyList())
    val allItems: StateFlow<kotlin.collections.List<List>> = _itemsFlow // Expose StateFlow

    companion object {
        private const val LIST_COLLECTION = "items"
    }

    init {
        listCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                println("Error fetching tasks: ${error.message}") // Log the error
                return@addSnapshotListener
            }

            _itemsFlow.value = snapshot?.documents?.mapNotNull { it.toObject<List>() } ?: emptyList()
        }
    }

    suspend fun insertItem(list: List) {
        try {
            val documentId = list.id ?: listCollection.document().id
            val itemWithId = list.copy(id = documentId)
            listCollection.document(documentId).set(itemWithId).await()
        } catch (e: Exception) {
            Log.e("ListFirebaseRepository", "Error adding item", e)
            throw e // Re-throw to propagate the error if needed
        }
    }

    suspend fun deleteItem(list: List) {
        if (list.id == null) {
            throw IllegalArgumentException("Item ID cannot be null for deletion")
        } else {
            try {
                val itemId = list.id ?: throw IllegalArgumentException("Item ID cannot be null for deletion")
                listCollection.document(itemId).delete().await()
            } catch (e: Exception) {
                Log.e("ListFirebaseRepository", "Error deleting item", e)
                throw e
            }
        }
    }
}
