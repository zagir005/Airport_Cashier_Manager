package common

import org.mongodb.kbson.ObjectId

interface LocalCrudDataSource<T> {
    suspend fun getById(id: ObjectId): T?
    suspend fun getAll(): List<T>
    suspend fun add(obj: T)
    suspend fun addAll(list: List<T>)
    suspend fun update(obj: T)
    suspend fun removeById(id: ObjectId)
    suspend fun remove(obj: T)
}