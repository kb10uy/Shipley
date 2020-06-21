package org.kb10uy.shipley.authentication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.kb10uy.shipley.authentication.ShipleyAccountAuthenticator

/**
 * ShipleyAccountAuthenticator に反応させる Service
 */
class ShipleyAuthenticationService : Service() {
    private lateinit var authenticator: ShipleyAccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        authenticator =
            ShipleyAccountAuthenticator(this)
    }

    override fun onBind(intent: Intent): IBinder {
        return authenticator.iBinder
    }
}
