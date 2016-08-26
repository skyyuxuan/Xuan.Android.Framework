package com.xuanstudio.timbersaw.social.view;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by xuanyu on 16/8/26.
 */
public class DialogOauthFragment extends DialogFragment {

    private boolean mFullScreen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context context = inflater.getContext();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final int DIALOG_HEIGHT = (int) Math.min(0.8f * metrics.heightPixels, 1024);

        FrameLayout root = new FrameLayout(context);
        root.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        WebView wv = new WebView(context);
        wv.setId(android.R.id.primary);

        if (mFullScreen) {
            root.addView(wv, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        } else {
            root.addView(wv, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, DIALOG_HEIGHT));
        }

        LinearLayout pframe = new LinearLayout(context);
        pframe.setId(android.R.id.widget_frame);
        pframe.setOrientation(LinearLayout.VERTICAL);
        pframe.setVisibility(View.GONE);
        pframe.setGravity(Gravity.CENTER);
        ProgressBar progress =
                new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        progress.setId(android.R.id.progress);
        pframe.addView(progress,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        TextView progressText = new TextView(context, null, android.R.attr.textViewStyle);
        progressText.setId(android.R.id.text1);
        pframe.addView(progressText,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        if (mFullScreen) {
            root.addView(pframe,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        } else {
            root.addView(pframe,
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DIALOG_HEIGHT));
        }

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        View rawWebView = view.findViewById(android.R.id.primary);
        if (rawWebView == null) {

        }
        if (!(rawWebView instanceof WebView)) {

        }
        WebView wv = (WebView) rawWebView;
        WebSettings webSettings = wv.getSettings();
        webSettings.setSaveFormData(false);

        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                WebView wv = (WebView) v;
                if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
                    wv.goBack();
                    return true;
                }
                return false;
            }
        });
//
//        if (mController.isJavascriptEnabledForWebView()) {
//            webSettings.setJavaScriptEnabled(true);
//        }
//
//        if (mController.disableWebViewCache()) {
//            webSettings.setAppCacheEnabled(false);
//            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        }
//
//        if (mController.removePreviousCookie()) {
//            CookieManager cookieManager = CookieManager.getInstance();
//            cookieManager.removeAllCookie();
//        }

        wv.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress != 0 && newProgress != 100) {
                    //setProgressShown(view.getUrl(), getView(), newProgress);
                }
            }

        });

        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                interceptUrlCompat(view, url, true);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (!interceptUrlCompat(view, url, false)) {
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }


            private boolean interceptUrlCompat(WebView view, String url, boolean loadUrl) {
                if (!isAdded() || isRemoving()) {
                    return false;
                }
/*
String redirectUri = null;
try {
//redirectUri = mController.getRedirectUri();
} catch (IOException e) {
return false;
}
String authorizationType = getArguments().getString(ARG_AUTHORIZATION_TYPE);
if (isRedirectUriFound(url, redirectUri)) {
if (TextUtils.equals(authorizationType, AUTHORIZATION_10A)) {
OAuth10aResponseUrl responseUrl = new OAuth10aResponseUrl(url);
mController.set(responseUrl.getVerifier(), responseUrl.getError(), null,
true);
} else if (TextUtils.equals(authorizationType, AUTHORIZATION_EXPLICIT)) {
AuthorizationCodeResponseUrl responseUrl =
new AuthorizationCodeResponseUrl(url);
String error = responseUrl.getError();
if (!TextUtils.isEmpty(error)
&& !TextUtils.isEmpty(responseUrl.getErrorDescription())) {
error += (": " + responseUrl.getErrorDescription());
}
mController.set(responseUrl.getCode(), error, null, true);
} else { // implicit
ImplicitResponseUrl implicitResponseUrl = new ImplicitResponseUrl(url);
String error = implicitResponseUrl.getError();
if (!TextUtils.isEmpty(error)
&& !TextUtils.isEmpty(implicitResponseUrl.getErrorDescription())) {
error += (": " + implicitResponseUrl.getErrorDescription());
}
mController.set(implicitResponseUrl.getAccessToken(), error,
implicitResponseUrl, true);
}
return true;
}
*/

                if (loadUrl) {
                    view.loadUrl(url);
                }
                return false;
            }

        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        WebView wv = (WebView) getView().findViewById(android.R.id.primary);
        if (wv != null) {
            //wv.loadUrl(getArguments().getString(ARG_AUTHORIZATION_REQUEST_URL));
        }
        if (mFullScreen) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            getDialog().getWindow().setBackgroundDrawable(null);
        }

    }
}
