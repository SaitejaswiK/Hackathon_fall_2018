package com.sawapps.baymaxhealthcare;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class Utils {


    // Storage Permissions
    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_LOCATION = 1;
    public static String BASE_URL = "http://192.168.43.244:8080/";

    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    static void showSnackbar(View view, String string) {

        Snackbar.make(view, string, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    static boolean isValidEmail(CharSequence target) {
        try {
            return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void saveLocally(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("ORNews", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
        Log.v("locale", "savingLocally " + value);
    }


    public static String getLocal(Context context, String key) {
        String locale = context.getSharedPreferences("ORNews", Context.MODE_PRIVATE).getString(key, null);

        return locale;

    }


    static String getBuildNumber(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


}
