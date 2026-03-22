package com.parishod.watomatic.model.utils;

import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.topjohnwu.superuser.Shell;
import com.parishod.watomatic.model.preferences.PreferencesManager;

public class RootHelper {
    private static final String TAG = "RootHelper";

    public static boolean isRootAvailable() {
        return Shell.getShell().isRoot();
    }

    public static void applyRootOptimizations(Context context) {
        PreferencesManager prefs = PreferencesManager.getPreferencesInstance(context);
        if (prefs.isRootEnabled()) {
            new Thread(() -> {
                if (isRootAvailable()) {
                    Log.d(TAG, "Applying Root Optimizations");
                    int pid = Process.myPid();
                    
                    // 1. Set OOM Score Adjustment to make it unkillable (-1000)
                    Shell.cmd("echo -1000 > /proc/" + pid + "/oom_score_adj").exec();
                    Log.d(TAG, "OOM Score Adjusted for PID: " + pid);

                    // 2. Ensure Notification Listener is allowed
                    String packageName = context.getPackageName();
                    Shell.cmd("cmd notification allow_listener " + packageName + "/com.parishod.watomatic.service.NotificationService").exec();
                    Log.d(TAG, "Notification Listener Enforced via Root");
                } else {
                    Log.w(TAG, "Root requested but not available");
                }
            }).start();
        }
    }

    public static void removeRootOptimizations(Context context) {
        new Thread(() -> {
            if (isRootAvailable()) {
                Log.d(TAG, "Removing Root Optimizations");
                int pid = Process.myPid();
                
                // Set OOM Score Adjustment back to default (0)
                Shell.cmd("echo 0 > /proc/" + pid + "/oom_score_adj").exec();
                Log.d(TAG, "OOM Score Reverted to 0 for PID: " + pid);

                // Note: We don't remove the notification listener permission here
                // because it's a core requirement for the app anyway, 
                // and disabling the Root Optimizations toggle shouldn't break the app's basic functionality.
            }
        }).start();
    }
}
