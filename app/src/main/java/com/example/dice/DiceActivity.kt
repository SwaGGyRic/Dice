package com.example.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dice.databinding.ActivityDiceBinding

class DiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDiceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.text.text=intent.extras?.getString("text")
    }
}