package com.example.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val url = "https://apod.nasa.gov]/apod/image/1908/M61-HST-ESO-S1024.jpg"
    val urls = listOf<String>()
    val urls1 = listOf<String>()
    val urls2 = listOf<String>()
    val urls3 = listOf<String>()
    val progres = listOf(progressBAR, progressBAR1, progressBAR2, progressBAR3)
    val images = listOf(nasa_image, nasa_image1, nasa_image2, nasa_image3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.Main).launch {
            doInBackground(urls, progres, images)
        }
    }


    suspend fun doInBackground(
        urls: List<String>,
        progressBarList: List<ProgressBar>,
        imageList: List<ImageView>
    ) {
        //var urlDisplay = url

        var listaZip = urls.zip(progressBarList)
        var listaZipDos = listaZip.zip(progressBarList)
        var j = imageList.iterator()

        withContext(Dispatchers.Default) {
            for ((url, progressB) in listaZip) {
                try {
                    progressB.visibility = View.VISIBLE
                    val inputStream = java.net.URL(url).openStream()
                    var result = BitmapFactory.decodeStream(inputStream)
                    var imagen = j.next()
                    imagen.setImageBitmap(result)

                } catch (e: Exception) {
                    Log.e("ERROR", e.message.toString())
                    e.printStackTrace()
                }
            }
        }
     }

}




