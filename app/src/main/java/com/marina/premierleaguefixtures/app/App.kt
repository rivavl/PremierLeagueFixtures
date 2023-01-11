package com.marina.premierleaguefixtures.app

import android.app.Application
import com.marina.premierleaguefixtures.di.component.DaggerAppComponent

class App : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}