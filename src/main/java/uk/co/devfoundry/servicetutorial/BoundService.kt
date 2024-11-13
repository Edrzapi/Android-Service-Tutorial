package uk.co.devfoundry.servicetutorial

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {

    private val binder = CounterBinder()
    private var counter = 0

    // Binder class to expose the service to clients
    inner class CounterBinder : Binder() {
        fun getService(): BoundService = this@BoundService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    // Method to retrieve the counter value
    fun getCounterValue(): Int {
        return counter++
    }
}