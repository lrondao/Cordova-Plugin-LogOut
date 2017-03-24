package com.os.mobile.silentlogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.CookieManager;

import com.outsystems.android.core.WebServicesClient;
import com.squareup.okhttp.internal.NamedRunnable;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

/**
 * Created by vmfo on 03/05/15.
 * @author Vítor Oliveira (vitor.oliveira@outsystems.com)
 * @author João Pereira Gonçalves (joao.goncalves@outsystems.com)
 */
public class SilentLogin extends CordovaPlugin {

    private static final String SILENT_LOGIN = "silentLogin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        if (action.equals(SILENT_LOGIN)) {
            silentLogin();
        } else if (action.equals(SILENT_LOGIN_WEBVIEW)) {
            silentLoginWebView();
        }
        return true;
    }

    private void silentLogin() {
        //if(!BuildConfig.DEV) {
            Intent intent = new Intent(cordova.getActivity(), SplashScreen.class);
            cordova.getActivity().startActivity(intent);
        //}
    }
}
