package com.example.capybarameditation

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capybarameditation.databinding.ActivityAccomplishmentBinding
import com.example.capybarameditation.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button2.setOnClickListener {
            val meditationIntent = Intent(this, MeditationActivity::class.java)
            meditationIntent.putExtra("meditationLength", 2)
            startActivity(meditationIntent)
        }

        binding.button5.setOnClickListener {
            val meditationIntent = Intent(this, MeditationActivity::class.java)
            meditationIntent.putExtra("meditationLength", 5)
            startActivity(meditationIntent)
        }

        binding.button10.setOnClickListener {
            val meditationIntent = Intent(this, MeditationActivity::class.java)
            meditationIntent.putExtra("meditationLength", 10)
            startActivity(meditationIntent)
        }

        binding.accomplishments.setOnClickListener {
            val accomplishmentIntent = Intent(this, AccomplishmentActivity::class.java)
           startActivity(accomplishmentIntent)
        }
    }






}