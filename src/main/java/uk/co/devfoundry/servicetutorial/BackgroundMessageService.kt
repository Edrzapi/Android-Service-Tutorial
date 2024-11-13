package uk.co.devfoundry.servicetutorial

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    private val TAG = "MyBackgroundService"
    private var isRunning = false

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand called. Intent: $intent")

        // This log will tell us if the service was restarted by the system (intent == null)
        if (intent == null) {
            Log.d(TAG, "Service was restarted by the system.")
        }

        // If service is not already running, start it
        if (!isRunning) {
            isRunning = true
            Thread {
                while (isRunning) {
                    Log.d(TAG, "Service is running...")
                    try {
                        Thread.sleep(2000)  // Sleep for 5 seconds
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        // Return START_NOT_STICKY to ensure the stays dead
        return START_NOT_STICKY

    }

    override fun onBind(intent: Intent?): IBinder? {
        // This is for bound services, but since this is a started service, we return null
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop the service
        isRunning = false
        Log.d(TAG, "Service is stopped.")
    }
}
