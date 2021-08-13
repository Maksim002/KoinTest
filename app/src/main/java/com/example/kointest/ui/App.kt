package kg.bakai.countries

import android.app.Application
import com.example.kointest.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            koin.loadModules(// module list
                listOf(repositoryModules, viewModelModules, networkModule))
        }
    }
}