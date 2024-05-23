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
    var seconds = 0
    var leftover = 0
    var second = 0
    var leftovers = 0
    private lateinit var counting: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)

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



        binding.starts.setOnClickListener{
            if(!isRunning) {
                //when stopped

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
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivity(menuIntent)

            val accomplishmentIntent = Intent(this, AccomplishmentActivity::class.java)
            var timeMeditated = min * 60000 - second*60000+leftover
            accomplishmentIntent.putExtra("leftovers", timeMeditated)
        }


    }


}