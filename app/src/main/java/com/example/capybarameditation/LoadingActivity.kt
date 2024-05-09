package com.example.capybarameditation

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.capybarameditation.api.QuoteService
import com.example.capybarameditation.api.RetrofitHelper
import com.example.capybarameditation.databinding.ActivityLoadingBinding
import com.example.capybarameditation.models.QuoteObj
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding
    private lateinit var quoteResponse: List<QuoteObj>

    companion object{
        val TAG = "yay"
        val TAG2 = "noo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        var quoteCall = quoteService.getQuoteData()

        quoteCall.enqueue(object: Callback<List<QuoteObj>> {
            override fun onResponse(call: Call<List<QuoteObj>>, response: Response<List<QuoteObj>>) {
                quoteResponse = response.body()!!
                Log.d(TAG, "onResponse: ${response.body()}")
                binding.quote.text = response.body()!![0].q
            }

            override fun onFailure(call: Call<List<QuoteObj>>, t: Throwable) {
                Log.d(TAG2, "onFailure: ${t.message}")
            }
        })

        val mediaPlayer = MediaPlayer.create(this, R.raw.capysong)
        mediaPlayer.start()


        binding.button.setOnClickListener {
            mediaPlayer.stop()
        }

    }
}