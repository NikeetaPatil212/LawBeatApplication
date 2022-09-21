package com.example.lawbeatapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.lawbeatapplication.R
import com.example.lawbeatapplication.dataclass.NewsData

class NewsDetailActivity : AppCompatActivity() {

   // lateinit var list: NewsData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val imageDetail = findViewById<ImageView>(R.id.imageDetail)
        val tvTab = findViewById<TextView>(R.id.tvTab)
        val tvHeadline = findViewById<TextView>(R.id.tvHeadline)
        val tvReporter = findViewById<TextView>(R.id.tvRepoter)
        val tvReadTime = findViewById<TextView>(R.id.tvReadTime)
        val tvTime = findViewById<TextView>(R.id.tvTime)
        val webView = findViewById<WebView>(R.id.webView)


        val intent = getIntent()
        val image = intent.getStringExtra("Image")
        val category  = intent.getStringExtra("Category")
        val title = intent.getStringExtra("Title")
        val author = intent.getStringExtra("author")
        val date = intent.getStringExtra("Date")
        val time = intent.getStringExtra("time")
        val body = intent.getStringExtra("Body")


        Glide.with(applicationContext).load(image).into(imageDetail)
        tvTab.text = category
        tvHeadline.text = title
        tvReporter.text = author
        tvReadTime.text = time
        tvTime.text = date
        if (body != null) {
            webView.loadData(body,"text/html; charset=UTF-8","UTF-8")
        }
    }
}


