package com.example.quotify_mvvm

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.quotify_mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,MainViewModelFactory(application))[MainViewModel::class.java]
        setQuote(viewModel.getQuotes())
    }

    fun setQuote(quote: Quote){
      binding.cardInclude.txtQuotes.text = quote.text
        binding.cardInclude.txtAuthor.text = quote.author
    }

    fun previous(view: View) {
        setQuote(viewModel.previousQuote())
    }
    fun next(view: View) {
        setQuote(viewModel.nextQuote())
    }

    fun share(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,viewModel.getQuotes().text.plus("\n\n").plus(viewModel.getQuotes().author))

        startActivity(intent)
    }
}