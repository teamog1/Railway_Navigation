package com.example.smartnav

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // Find the WebView in the layout
        val webView: WebView = findViewById(R.id.web_view)

        // Enable JavaScript and set WebViewClient to prevent opening in a browser
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // Load the required URL in the WebView
        webView.loadUrl("https://animated-marzipan-1447f4.netlify.app/")
    }
}