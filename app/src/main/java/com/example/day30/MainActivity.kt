package com.example.day30

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import com.example.day30.R.raw.mymusic

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer:MediaPlayer
    var totalTime:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        mediaPlayer=MediaPlayer.create(this, R.raw.mymusic)
        mediaPlayer.setVolume(1f,1f)
        totalTime=mediaPlayer.duration
        val btnplay=findViewById<ImageView>(R.id.imageView2)
        val btnpause=findViewById<ImageView>(R.id.imageView3)
        val btnstop=findViewById<ImageView>(R.id.imageView4)
        val seekBarmusic=findViewById<SeekBar>(R.id.seekBar)
        btnplay.setOnClickListener{
            mediaPlayer.start()
        }
        btnpause.setOnClickListener{
            mediaPlayer.pause()
        }
        btnstop.setOnClickListener{
            mediaPlayer?.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }
        //when user cahnges the time stamp of music
        seekBarmusic.max=totalTime
     seekBarmusic.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
         override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){
                    mediaPlayer.seekTo(p1)
                }
         }

         override fun onStartTrackingTouch(p0: SeekBar?) {}

         override fun onStopTrackingTouch(p0: SeekBar?) {}


     })
        //seekbaar move with mmusic
        val handler=Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try {
                    seekBarmusic.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch (exception:java.lang.Exception){
                    seekBarmusic.progress=0
                }
            }


        },0)
}
}