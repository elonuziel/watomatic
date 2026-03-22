package com.parishod.watomatic.receivers

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import com.parishod.watomatic.service.NotificationService

class NotificationServiceRestartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action?.equals(Intent.ACTION_BOOT_COMPLETED) == true) {
            context?.let {
                // Apply Root Optimizations if enabled
                com.parishod.watomatic.model.utils.RootHelper.applyRootOptimizations(context)

                val enabledPackages = Settings.Secure.getString(
                    context.contentResolver,
                    "enabled_notification_listeners"
                )
                val isRootEnabled = com.parishod.watomatic.model.preferences.PreferencesManager.getPreferencesInstance(context).isRootEnabled
                
                if (enabledPackages?.contains(context.packageName) == true || isRootEnabled) {
                    // Trigger a rebind
                    val cn = ComponentName(context, NotificationService::class.java)
                    NotificationService.requestRebind(cn)
                    Log.d("NLS", "Requesting rebind to Notification Listener. Root Enabled: $isRootEnabled")
                }
            }
        }
    }

}