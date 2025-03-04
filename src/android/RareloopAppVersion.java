package com.rareloop.cordova.appversion;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Modern Android imports
import androidx.annotation.NonNull;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class RareloopAppVersion extends CordovaPlugin {

    private static final String GET_APP_VERSION_ACTION = "getAppVersion";

    @Override
    public boolean execute(String action, @NonNull JSONArray args, 
                          @NonNull CallbackContext callbackContext) {
        if (GET_APP_VERSION_ACTION.equals(action)) {
            getVersionInfo(callbackContext);
            return true;
        }
        return false;
    }

    private void getVersionInfo(@NonNull CallbackContext callbackContext) {
        try {
            Context context = cordova.getActivity().getApplicationContext();
            PackageInfo pInfo = context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0);

            JSONObject response = new JSONObject();
            response.put("version", pInfo.versionName);
            response.put("build", pInfo.versionCode);

            callbackContext.success(response);
        } catch (PackageManager.NameNotFoundException | JSONException e) {
            callbackContext.error("Error retrieving version info: " + e.getMessage());
        }
    }
}
