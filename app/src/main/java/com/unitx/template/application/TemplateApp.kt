package com.unitx.template.application

import android.app.Application
import android.graphics.Color
import com.unitx.template.BuildConfig
import com.unitx.template.di.appModule
import com.unitx.signal_core.contract.position.SnackPosition
import com.unitx.signal_core.contract.position.ToastPosition
import com.unitx.signal_core.main.Signal
import com.unitx.signal_core.queue.QueueStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class TemplateApp: Application() {

    override fun onCreate() {
        super.onCreate()
        di()
        logger()
        signal()
    }

    private fun signal() {
        Signal.createCore(this) {
            setQueueStrategy(QueueStrategy.Independent)

            theme {
                light { snackBackground = Color.WHITE }
                dark { snackBackground = Color.BLACK }
            }

            snack {
                duration = 2500L
                dismissOnBackPress = true
                position = SnackPosition.Top
            }

            toast {
                position = ToastPosition.Bottom
            }
        }
    }

    private fun logger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun di() {
        startKoin {
            androidLogger()
            androidContext(this@TemplateApp)
            modules(appModule)
        }
    }

}