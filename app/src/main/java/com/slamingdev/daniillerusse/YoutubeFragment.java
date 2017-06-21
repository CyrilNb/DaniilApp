package com.slamingdev.daniillerusse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Slamingdev on 28/01/2016.
 */
public class YoutubeFragment extends Fragment {
    String url0 = "https://m.youtube.com/user/Tsarm/videos";
    WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        webView = (WebView) view.findViewById(R.id.webViewYoutube);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        webView.loadUrl(url0);
        return view;
    }


    public boolean canGoBack() {
        return this.webView != null && this.webView.canGoBack();
    }

    public void goBack() {
        if(this.webView != null) {
            this.webView.goBack();
        }
    }


}
