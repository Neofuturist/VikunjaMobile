package room

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import ru.vikunja.mobile.MainApplication.Companion.appContext

actual fun platformModule() = module {
    single<AppDatabase> { getDatabase(appContext!!) }
}

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
//    appContext.getDatabasePath("my_room.db").delete()
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

fun getDatabase(ctx: Context): AppDatabase {
    return getDatabaseBuilder(ctx)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}