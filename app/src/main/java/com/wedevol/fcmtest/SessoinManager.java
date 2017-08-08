package com.wedevol.fcmtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by hp on 8/8/2017.
 */

public class SessoinManager {
    private static final String KEY_GET_TOKEN = "token";
    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = MODE_PRIVATE;

    // Shared preferences file name
    private static final String PREF_NAME = "fcmSession";


    public SessoinManager(Context _context) {
        this._context = _context;
    }

    public void setKeyGetToken(String token) {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.putString(KEY_GET_TOKEN, token);

        // commit changes
        editor.commit();

        Log.d("Session", "sender token saved");

    }

    public String getKeyGetToken() {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return pref.getString(KEY_GET_TOKEN, "");
    }

}
