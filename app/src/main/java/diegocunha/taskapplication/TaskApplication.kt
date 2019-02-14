package diegocunha.taskapplication

import android.app.Application
import diegocunha.taskapplication.dependenyinjection.appModule
import org.koin.android.ext.android.startKoin

class TaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}