package com.example.capybarameditation

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.capybarameditation.databinding.ActivityMeditationBinding
import java.io.File


class MeditationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeditationBinding
    var bases: Long = 0
    var isRunning: Boolean = false

    var displayTime = 0L
    var seconds = 0
    var leftover = 0
    var second = 0
    var leftovers = 0
    private lateinit var counting: CountDownTimer

    companion object{
        var totalTime = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val capybaraSong = MediaPlayer.create(this, R.raw.capysong)
        val min = intent.getIntExtra("meditationLength", 0)

        //set timer to initial values
        var initial = min*60000
         second = initial / 60000
         leftover = initial % 60000
       if(leftover<10){
            binding.timer.text = "" + second + ":0" + leftover / 1000
        }else {
            binding.timer.text = "" + second + ":" + leftover / 1000
        }

        //declare the timer
        //binding.secondProgressBar.isIndeterminate = false
        //binding.secondProgressBar.max = 100
        //binding.secondProgressBar.progress = 100

        //TODO fix all this
        var i = min*60
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i >= 0) {
                    binding.secondProgressBar.progress = (i.toFloat()/(min*60)*100).toInt()
                    i--
                    handler.postDelayed(this, 1000)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }, 100)



        binding.starts.setOnClickListener{
           var total = second*60000+leftover
            if(!isRunning) {
                //when stopped

                capybaraSong.start()
                counting = object : CountDownTimer((second*60000+leftover).toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        second = (millisUntilFinished / 60000).toInt()
                        leftover = (millisUntilFinished % 60000).toInt()
                        if(leftover<1000){
                            binding.timer.text = "" + second + ":00"
                        }else if(leftover<10000){
                            binding.timer.text = "" + second + ":0" + leftover / 1000
                        }else {
                            binding.timer.text = "" + second + ":" + leftover / 1000
                        }
                        //binding.secondProgressBar.progress = (second*60000+leftover)/total
                    }
                    override fun onFinish() {
                        binding.timer.text = "capy!"
                    }
                }
                counting.start()

                isRunning = true
                binding.starts.setText("pause")
            }else{
                //when running

                capybaraSong.stop()
                isRunning = false
                binding.starts.setText("start")

                counting.cancel()
                if(leftover<1000){
                    binding.timer.text = "" + second + ":00"
                }else if(leftover<10000){
                    binding.timer.text = "" + second + ":0" + leftover / 1000
                }else {
                    binding.timer.text = "" + second + ":" + leftover / 1000
                }
            }
        }


        binding.resets.setOnClickListener {

            binding.timer.text = "" + min + ":00"
            counting.cancel()
            //reset trackers
            initial = min * 60000
            second = initial / 60000
            leftover = initial % 60000
            //declare the timer
            var counting: CountDownTimer = object : CountDownTimer((min * 60000).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    second = (millisUntilFinished / 60000).toInt()
                    leftover = (millisUntilFinished % 60000).toInt()
                    if (leftover < 1000) {
                        binding.timer.text = "" + second + ":00"
                    } else if (leftover < 10000) {
                        binding.timer.text = "" + second + ":0" + leftover / 1000
                    } else {
                        binding.timer.text = "" + second + ":" + leftover / 1000
                    }
                }

                override fun onFinish() {
                    binding.timer.text = "capy!"
                }
            }
            if (isRunning){
                counting.start()
            }
        }

        binding.back.setOnClickListener {
            capybaraSong.stop()
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivity(menuIntent)

        }


    }


}