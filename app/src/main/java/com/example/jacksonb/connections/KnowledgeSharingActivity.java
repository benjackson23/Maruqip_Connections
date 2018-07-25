package com.example.jacksonb.connections;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class KnowledgeSharingActivity  extends AppCompatActivity
{
    ProgressBar knowledgeSharingProgressBar;

    WebView knowledgeSharingWebView;
    WebSettings webSettings;

    String knowledgeSharingURL = "http://fs.barry-wehmiller.com";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_sharing_web_view_layout);

        // Link XML resources
        knowledgeSharingWebView = (WebView) findViewById(R.id.knowledge_sharing_web_view);
        knowledgeSharingProgressBar = (ProgressBar) findViewById(R.id.knowledge_sharing_progress_bar);

        //Intialize Websettings
        webSettings = knowledgeSharingWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        knowledgeSharingWebView.getSettings().setBuiltInZoomControls(true);
        knowledgeSharingWebView.getSettings().setTextZoom(100);
        knowledgeSharingWebView.getSettings().setLoadWithOverviewMode(true);
        knowledgeSharingWebView.getSettings().setUseWideViewPort(true);
        //knowledgeSharingWebView.getSettings().setAllowFileAccess(true);                //New
        //knowledgeSharingWebView.getSettings().setAllowFileAccessFromFileURLs(true);    //New
        knowledgeSharingWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView troubleshootingWebView, String url, Bitmap bitmap) {
                super.onPageStarted(troubleshootingWebView, url, bitmap);

                knowledgeSharingProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView troubleshootingWebView, String URL)
            {
                super.onPageFinished(troubleshootingWebView, URL);

                knowledgeSharingProgressBar.setVisibility(View.GONE);
            }
        });

        //Download from webview
        knowledgeSharingWebView.setDownloadListener(new DownloadListener()
        {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                final String fileName = URLUtil.guessFileName(url, contentDisposition, mimetype);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_SHORT).show();
            }
        });

        //Enable cookies
        CookieManager.getInstance().setAcceptCookie(true);

        // Load webpage
        knowledgeSharingWebView.loadUrl(knowledgeSharingURL);
    }
}
