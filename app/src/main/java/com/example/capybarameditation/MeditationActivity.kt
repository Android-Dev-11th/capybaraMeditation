package com.example.capybarameditation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capybarameditation.databinding.ActivityMenuBinding

import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer


class MeditationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    lateinit var timer: Chronometer
    lateinit var starts: Button
    lateinit var resets: Button
    var bases: Long = 0
    var isRunning: Boolean = false

    var displayTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        starts.setOnClickListener{
            if(!isRunning) {
                //when stopped
                timer.setBase(timer.getBase()+SystemClock.elapsedRealtime()-bases)
                timer.start()
                isRunning = true
                starts.setText("Stop")
            }else{
                //when running
                bases = SystemClock.elapsedRealtime()
                timer.stop()
                isRunning = false
                starts.setText("Start")
            }
        }

        resets.setOnClickListener {
            timer.setBase(SystemClock.elapsedRealtime())
        }



    }


}