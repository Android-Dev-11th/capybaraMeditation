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
    var isRunning: Boolean = false
    var leftover = 0
    var second = 0
    private lateinit var counting: CountDownTimer

    companion object {
        var totalTime = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeditationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val capybaraSong = MediaPlayer.create(this, R.raw.capysong)
        val min = intent.getIntExtra("meditationLength", 0)


        //set timer to initial values
        var initial = min * 60000
        second = initial / 60000
        leftover = initial % 60000
        if (leftover < 10) {
            binding.timer.text = "" + second + ":0" + leftover / 1000
        } else {
            binding.timer.text = "" + second + ":" + leftover / 1000
        }

        binding.secondProgressBar.progress = 100


        //declare the timer
        //binding.secondProgressBar.isIndeterminate = false
        //binding.secondProgressBar.max = 100
        //binding.secondProgressBar.progress = 100

        //TODO fix all this
        val handler = Handler()
        var timerRunnable : Runnable? = null
        var i = min * 60

        fun getLeftoverTime(): Int {
            return min*60000 - second*60000 - leftover
        }
        binding.starts.setOnClickListener {
            if (!isRunning) {
                //when stopped

                capybaraSong.start()
                counting = object : CountDownTimer((second * 60000 + leftover).toLong(), 1000) {
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
                        //binding.secondProgressBar.progress = (second*60000+leftover)/total
                    }

                    override fun onFinish() {
                        binding.timer.text = "capy!"
                    }
                }
                counting.start()

                isRunning = true
                binding.starts.setText("pause")
              timerRunnable = object : Runnable {
                    override fun run() {
                        // set the limitations for the numeric
                        // text under the progress bar

                        if (i >= 0) {
                            binding.secondProgressBar.progress =
                                (i.toFloat() / (min * 60) * 100).toInt()
                            i--
                            handler.postDelayed(this, 1000)
                        } else {
                            handler.removeCallbacks(this)
                        }
                    }
                }

                handler.postDelayed(timerRunnable!!, 100)
            } else {

                //when running
                handler.removeCallbacks(timerRunnable!!)

                capybaraSong.stop()
                isRunning = false
                binding.starts.setText("start")
                totalTime += getLeftoverTime()

                counting.cancel()
                if (leftover < 1000) {
                    binding.timer.text = "" + second + ":00"
                } else if (leftover < 10000) {
                    binding.timer.text = "" + second + ":0" + leftover / 1000
                } else {
                    binding.timer.text = "" + second + ":" + leftover / 1000
                }
            }


        }


        binding.resets.setOnClickListener {

            binding.timer.text = "" + min + ":00"
            counting.cancel()
            //reset trackers
            i=min*60
            binding.secondProgressBar.progress = 100
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
            if (isRunning) {
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