package com.example.dice

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.dice.Classes.Dice
import com.example.dice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var dice = Dice()
    var stepCount: Int = 0
    fun getDraw(side:Int):Drawable?{
        return when(side){
        1->ContextCompat.getDrawable(this, R.drawable.dice_1)
        2->ContextCompat.getDrawable(this, R.drawable.dice_2)
        3->ContextCompat.getDrawable(this, R.drawable.dice_3)
        4->ContextCompat.getDrawable(this, R.drawable.dice_4)
        5->ContextCompat.getDrawable(this, R.drawable.dice_5)
        6->ContextCompat.getDrawable(this, R.drawable.dice_6)
            else->this.getDraw(R.color.black)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var isRunning: Boolean = false
        val timer = object : CountDownTimer(30000, 1000){
            override fun onTick(time: Long){
                isRunning = true
                binding.txt.setText((time/1000).toString())
            }

            override fun onFinish() {
                isRunning = false
                val intent = Intent(this@MainActivity, DiceActivity::class.java)
                intent.putExtra("text", "Вы обосрались")
                startActivity(intent)
            }
        }
        binding.btnRoll.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Попробуй угадать)")
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            var alertDialog: AlertDialog?=null
            val btClick = View.OnClickListener {
                view->
                alertDialog?.cancel()
                val n: Int = view.tag as Int
                if (!isRunning){
                    timer.start()
                    stepCount = 0
                }
                if (n == dice.spin()){
                    Toast.makeText(this, "Ебать харош", Toast.LENGTH_SHORT).show()
                    timer.cancel()
                    isRunning = false
                    binding.txt.text = "Количество попыток - $stepCount"
                }
                binding.cube.setImageDrawable(getDraw(dice.state))
                stepCount++
            }
            for (i in 1..dice.sides){
                val bt = Button(this)
                bt.text = i.toString()
                bt.tag = i
                val draw = getDraw(i)
                draw?.setBounds(0, 0, 150, 150)
                bt.setCompoundDrawables(draw, null, null, null)
                bt.setOnClickListener(btClick)
                layout.addView(bt)
            }
            dialogBuilder.setView(layout)
            alertDialog = dialogBuilder.show()
        }
    }
}
