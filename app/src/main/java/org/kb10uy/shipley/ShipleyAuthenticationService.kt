package org.kb10uy.shipley

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ShipleyAuthenticationService : Service() {
    private lateinit var authenticator: ShipleyAccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        authenticator = ShipleyAccountAuthenticator(this)
    }

    override fun onBind(intent: Intent): IBinder {
        return authenticator.iBinder
    }
}
