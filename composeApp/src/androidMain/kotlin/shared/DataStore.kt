package shared

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.pref.AppSetting
import data.pref.dataStoreFileName
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.vikunja.mobile.MainApplication.Companion.appContext

actual fun createDataStore(context: Any?): DataStore<Preferences> {
    require(
        value = context is Context,
        lazyMessage = {"Context object is required."}
    )
    return AppSetting.getDataStore(
        producePath = {
            context.filesDir
                .resolve(dataStoreFileName)
                .absolutePath
        }
    )
}

actual val dataStoreModule: Module = module {
    single { createDataStore(context = appContext) }
}