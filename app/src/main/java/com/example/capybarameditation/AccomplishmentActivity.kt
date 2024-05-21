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

        binding.normaltext.text = "LOCKED - Meditate for 5 minutes"
        binding.silvertext.text = "LOCKED - Meditate for 10 minutes"
        binding.goldtext.text = "LOCKED - Meditate for 20 minutes"
        binding.diamondtext.text = "LOCKED - Meditate for 30 minutes"


        //skeleton
        var minMeditated = 30


        if (minMeditated >= 5){
            binding.normaltrophy.setImageResource(R.mipmap.capytrophy_foreground)
            binding.normaltext.text = "NORMAL CAPYBARA - Meditate for 5 minutes"

        }
        if (minMeditated >= 10){
            binding.silvertrophy.setImageResource(R.mipmap.capytrophysilver_foreground)
            binding.silvertext.text = "SILVER CAPYBARA - Meditate for 10 minutes"

        }
        if (minMeditated >= 20){
            binding.goldtrophy.setImageResource(R.mipmap.capytrophygold_foreground)
            binding.goldtext.text = "GOLD CAPYBARA - Meditate for 20 minutes"

        }
        if (minMeditated >= 30){
            binding.diamondtrophy.setImageResource(R.mipmap.capytrophydiamond_foreground)
            binding.diamondtext.text = "DIAMOND CAPYBARA - Meditate for 30 minutes"

        }




        binding.back2.setOnClickListener {
            val menuIntent = Intent(this, MenuActivity::class.java)
            startActivity(menuIntent)
        }
    }

}