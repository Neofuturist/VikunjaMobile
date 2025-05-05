package room.repository

import data.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import network.HttpUtil
import org.lighthousegames.logging.logging
import room.DatabaseApiService
import room.dao.ItemDao
import room.entity.ItemEntity

class ItemRepository(
    private val itemDao: ItemDao,
    private val databaseApiService: DatabaseApiService,
    private val httpUtil: HttpUtil
) {

    suspend fun getItems(): Flow<Resource<List<ItemEntity>>> = flow {
        try {
            loadItems().collect { result ->
                when (result) {
                    is Resource.Loading -> emit(Resource.Loading)
                    is Resource.Success -> {
                        updateItemsDB(result.data)
                    }
                    else -> {}
                }
            }
        } catch (e: Exception) {
            logging(TAG).e { e.message }
        }
        val dbItems = getItemsDB()
        emit(Resource.Success(dbItems))
    }

    suspend fun getFlowItemsDB(): Flow<Resource<List<ItemEntity>>> = flow {
        val dbItems = getItemsDB()
        emit(Resource.Success(dbItems))
    }

    suspend fun loadItems(): Flow<Resource<List<ItemEntity>>> = flow {
        emit(Resource.Loading)
        emit(httpUtil.checkResponse(databaseApiService.getItems()))
    }

    suspend fun updateItemsDB(items: List<ItemEntity>) {
        logging("test").d { "data: $items" }
        items.forEach { itemDao.insert(it) }
    }

    suspend fun getItemsDB(): List<ItemEntity> {
        return itemDao.getAll()
    }

    suspend fun getAllIds(): List<Int> {
        return itemDao.getAllIds()
    }

    suspend fun insertAllItems(items: List<ItemEntity>) {
        items.forEach { itemDao.insert(it) }
    }

    suspend fun addItem(item: ItemEntity) {
        itemDao.insert(item)
    }

    companion object {
        private const val TAG = "ItemRepository"
    }
}