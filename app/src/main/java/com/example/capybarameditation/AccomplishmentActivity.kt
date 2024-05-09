package com.example.capybarameditation

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
    }


}