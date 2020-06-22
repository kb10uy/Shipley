package org.kb10uy.shipley

import android.app.Application

class ShipleyApplication : Application() {
    companion object {
        private lateinit var singleInstance: ShipleyApplication

        val instance get() = singleInstance
    }

    override fun onCreate() {
        super.onCreate()
        singleInstance = this
    }
}
