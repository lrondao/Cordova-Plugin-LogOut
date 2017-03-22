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

    private static final String SILENT_LOGIN_WEBVIEW = "silentLoginWebView";

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
        cordova.getActivity().finish();
    }

    private void logoutWebView() {
        AlertDialog.Builder builder = new AlertDialog.Builder(cordova.getActivity());
        builder.setTitle("Sign Off Verification");
        builder.setMessage("You're about to sign off. Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                LogOut.this.cordova.getActivity().runOnUiThread(new NamedRunnable("LogoutRunnable") {
                    @Override
                    protected void execute() {
                        WebServicesClient.getInstance().getLoginCookies().clear();
                        CookieManager.getInstance().removeAllCookie();
                        LogOut.this.webView.loadUrl(webView.getUrl());
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
}
