package room

import com.example.room.ui.screen.ItemsScreenViewModel
import org.koin.dsl.module
import room.repository.ItemRepository

fun dbModule() = module {
    single { get<AppDatabase>().itemDao() }
    single { DatabaseApiService(get()) }
    single { ItemRepository(get(), get(), get()) }
    single { ItemsScreenViewModel(get(), get(), get()) }
}