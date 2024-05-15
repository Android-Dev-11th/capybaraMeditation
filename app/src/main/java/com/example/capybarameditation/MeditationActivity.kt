package com.example.capybarameditation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capybarameditation.databinding.ActivityMenuBinding

import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import com.example.capybarameditation.databinding.ActivityMeditationBinding


class MeditationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeditationBinding
    var bases: Long = 0
    var isRunning: Boolean = false

    var displayTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.starts.setOnClickListener{
            if(!isRunning) {
                //when stopped
                binding.timer.setBase(binding.timer.getBase()+SystemClock.elapsedRealtime()-bases)
                binding.timer.start()
                isRunning = true
                binding.starts.setText("Stop")
            }else{
                //when running
                bases = SystemClock.elapsedRealtime()
                binding.timer.stop()
                isRunning = false
                binding.starts.setText("Start")
            }
        }

        binding.resets.setOnClickListener {
            binding.timer.setBase(SystemClock.elapsedRealtime())
        }



    }


}