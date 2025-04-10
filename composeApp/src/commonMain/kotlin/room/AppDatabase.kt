package room

import androidx.room.Database
import androidx.room.RoomDatabase
import room.dao.ItemDao
import room.entity.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao


}