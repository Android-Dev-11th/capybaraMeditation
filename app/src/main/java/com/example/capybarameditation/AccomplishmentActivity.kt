package com.example.capybarameditation

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capybarameditation.databinding.ActivityAccomplishmentBinding
import com.example.capybarameditation.databinding.ActivityLoadingBinding

class AccomplishmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccomplishmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccomplishmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: get info about hours meditated, check if it meets threshold, change image res and
        //TODO: text if so


        //for testing
        var minMeditated = 30

        if (minMeditated > 5){
            binding.normaltrophy.setImageResource(R.mipmap.capytrophy_foreground)
        }
        if (minMeditated > 10){
            binding.silvertrophy.setImageResource(R.mipmap.capytrophysilver_foreground)
        }
        if (minMeditated > 20){
            binding.goldtrophy.setImageResource(R.mipmap.capytrophygold_foreground)
        }
        if (minMeditated > 30){
            binding.diamondtrophy.setImageResource(R.mipmap.capytrophydiamond_foreground)
        }




        binding.back2.setOnClickListener {
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivity(menuIntent)
        }
    }

}