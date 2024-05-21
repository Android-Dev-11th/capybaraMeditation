package com.example.capybarameditation

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
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
    var min = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        min = intent.getIntExtra("meditationLength", 0)

        //set timer to initial values
        var initial = min*60000
        var seconds = initial / 60000
        var leftover = initial % 60000
        if(leftover<10){
            binding.timer.text = "" + seconds + ":0" + leftover / 1000
        }else {
            binding.timer.text = "" + seconds + ":" + leftover / 1000
        }

        //declare the timer
        var counting:CountDownTimer = object : CountDownTimer((min*60000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = millisUntilFinished / 60000
                var leftover = millisUntilFinished % 60000
                if(leftover<10000){
                    binding.timer.text = "" + seconds + ":0" + leftover / 1000
                }else {
                    binding.timer.text = "" + seconds + ":" + leftover / 1000
                }
            }
            override fun onFinish() {
                binding.timer.text = "done!"
            }
        }


        binding.starts.setOnClickListener{
            if(!isRunning) {
                //when stopped
                counting.start()

                isRunning = true
                binding.starts.setText("Stop")
            }else{
                //when running
                bases = SystemClock.elapsedRealtime()

                isRunning = false
                binding.starts.setText("Start")
            }
        }


        binding.resets.setOnClickListener {
            binding.timer.text= "00:00"
        }

        binding.back.setOnClickListener {
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivity(menuIntent)
        }


    }


}