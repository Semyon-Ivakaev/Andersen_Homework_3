package com.vertigo.task_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.KEYCODE_ENTER
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val scopeDefault = CoroutineScope(Dispatchers.Default + Job())
    private val scopeMain = CoroutineScope(Dispatchers.Main + Job())
    private val client = OkHttpClient()
    private lateinit var url: String

    private var editText: EditText? = null
    private var imageView: ImageView? = null
    private var button: Button? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        editText?.setOnKeyListener { _, keyCode, _ ->
            url = editText?.text.toString()
            var params = false
            if (keyCode == KEYCODE_ENTER && url.contains("https://")) {
                getImage()
                params = true
            } else if (keyCode == KEYCODE_ENTER && !url.contains("https://")) {
                Toast.makeText(this, "Была введена не ссылка", Toast.LENGTH_SHORT).show()
            }
            params
        }

        button?.setOnClickListener {
            url = editText?.text.toString()
            if (url.contains("https://")) {
                getImage()
            } else {
                Toast.makeText(this, "Была введена не ссылка", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImage() {
        var count = 0
        progressBar?.isVisible = true

        scopeDefault.launch {

            var icon: Bitmap? = null
            val connection = Request.Builder().url(url).build()
            try {
                client.newCall(connection).execute().use { response ->
                    if (response.isSuccessful) {
                        icon = BitmapFactory.decodeStream(response.body?.byteStream())
                    }
                }
            } catch (e: Exception) {
                showToastFailDownload()
            }
            while (icon == null) {
                Log.v("AppVerbose", "$icon")
                count++
                Thread.sleep(250)
                if (count > 15) {
                    showToastFailDownload()
                    break
                }
            }
            scopeMain.launch {
                progressBar?.isVisible = false
                imageView?.scaleType = ImageView.ScaleType.CENTER_CROP
                imageView?.setImageBitmap(icon)
            }
        }
    }

    private fun showToastFailDownload() {
        scopeMain.launch {
            Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        editText = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
    }
}