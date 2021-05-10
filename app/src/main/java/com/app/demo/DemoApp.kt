package com.app.demo

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.app.demo.di.module.apimodule
import com.app.demo.di.module.appViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApp : Application(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        setUpConfigData()
    }

    private fun setUpConfigData() {
        startKoin {
            modules(listOf(apimodule, appViewModule))
        }
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

}