package com.example.helloarticles.view

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.helloarticles.databinding.ActivityDetailsBinding


class DetailsActivity :AppCompatActivity() {
    private var _binding :ActivityDetailsBinding ?=null
    private val binding get() = _binding!!
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mywebview = binding.webView
        val webSettings = mywebview.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        val urltoLoad:String = intent.getStringExtra("url").toString()
        mywebview.loadUrl(urltoLoad)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
