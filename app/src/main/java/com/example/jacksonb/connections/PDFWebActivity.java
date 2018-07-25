package com.example.jacksonb.connections;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PDFWebActivity extends AppCompatActivity
{
    ProgressBar intranetProgressBar;

    WebView pdfWebWebView;
    WebSettings webSettings;

    String intranetURL = "http://pdfweb.marquipwardunited.net/default.aspx";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdfweb_web_view_layout);

        // Link XML resources
        pdfWebWebView = (WebView) findViewById(R.id.pdfweb_web_view);
        intranetProgressBar = (ProgressBar) findViewById(R.id.intranet_progress_bar);

        //Intialize Websettings
        webSettings = pdfWebWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        pdfWebWebView.getSettings().setBuiltInZoomControls(true);
        pdfWebWebView.getSettings().setSupportMultipleWindows(true);
        pdfWebWebView.getSettings().setAllowFileAccess(true);
        pdfWebWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        pdfWebWebView.getSettings().setTextZoom(150);
        pdfWebWebView.getSettings().setLoadWithOverviewMode(true);
        pdfWebWebView.getSettings().setUseWideViewPort(true);
        pdfWebWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed("JacksonB", "Proud937");
            }

            @Override
            public void onPageStarted(WebView troubleshootingWebView, String url, Bitmap bitmap) {
                super.onPageStarted(troubleshootingWebView, url, bitmap);

                intranetProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView troubleshootingWebView, String URL)
            {
                super.onPageFinished(troubleshootingWebView, URL);

                intranetProgressBar.setVisibility(View.GONE);
            }
        });

        //Enable cookies
        CookieManager.getInstance().setAcceptCookie(true);


        // Load webpage
        if(isVPNActive())
            pdfWebWebView.loadUrl(intranetURL);

        else
        {
            Toast.makeText(this, "You must be connected to the VPN.", Toast.LENGTH_SHORT).show();
        }
    }

    //Check if VPN is connected
    public boolean isVPNActive()
    {
        List<String> networkList = new ArrayList<>();
        try
        {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces()))
            {
                if (networkInterface.isUp())
                    networkList.add(networkInterface.getName());
            }
        }
        catch (Exception e) {e.printStackTrace();}

        if(networkList.contains("tun0") || networkList.contains("ppp0")) {
            Log.d("test2", "tun0");
            return true;
        }

        else
            return false;

    }


    // Go back in WebView if possible
    @Override
    public void onBackPressed()
    {
        if(pdfWebWebView.canGoBack())
            pdfWebWebView.goBack();

        else
            super.onBackPressed();

    }
}
