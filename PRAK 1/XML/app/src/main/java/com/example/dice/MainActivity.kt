    package com.example.dice

    import android.os.Bundle
    import android.widget.Button
    import android.widget.ImageView
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity

    class MainActivity : AppCompatActivity() {

        private var currentToast: Toast? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val rollButton: Button = findViewById(R.id.btnRoll)

            rollButton.setOnClickListener {
                rollDice()
            }
        }

        private fun rollDice() {
            val dice1 = Dice(6)
            val diceRoll1 = dice1.roll()

            val dice2 = Dice(6)
            val diceRoll2 = dice2.roll()

            val diceImage1: ImageView = findViewById(R.id.ivDice1)
            val diceImage2: ImageView = findViewById(R.id.ivDice2)

            val drawableResource1 = when (diceRoll1) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            val drawableResource2 = when (diceRoll2) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }

            diceImage1.setImageResource(drawableResource1)
            diceImage1.contentDescription = diceRoll1.toString()

            diceImage2.setImageResource(drawableResource2)
            diceImage2.contentDescription = diceRoll2.toString()

            currentToast?.cancel()

            if (diceRoll1 == diceRoll2) {
                currentToast = Toast.makeText(this, "Selamat, anda dapat dadu double", Toast.LENGTH_SHORT)
            } else {
                currentToast = Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT)
            }

            currentToast?.show()
        }
    }

    class Dice(private val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }