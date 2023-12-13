package org.root.gs_fa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import org.root.gs_fa.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: DiceViewModel
    private lateinit var userModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(DiceViewModel::class.java)
        userModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnRollDice.setOnClickListener {
            model.roll()
            updateUI()
        }

        binding.btnExperience.setOnClickListener {
            // Move the customView declaration inside the setOnClickListener block
            val customView = layoutInflater.inflate(R.layout.experience_dialogue, null)

            AlertDialog.Builder(this)
                .setView(customView)
                .setPositiveButton("Novice") {dialogue, _ ->
                    dialogue.dismiss()
                    userModel.updateExperienceLevel("Novice")
                    Log.i(TAG, "Dismissed")
                }
                .setNegativeButton("Expert") {dialogue, _ ->
                    dialogue.cancel()
                    userModel.updateExperienceLevel("Expert")
                    Log.i(TAG, "cancelled")
                }.show()
        }
    }

    private fun updateUI() {
        binding.tvVal1.text = model.die1.toString()
        binding.tvVal2.text = model.die2.toString()
        binding.tvResult.text = model.sum.toString()
    }
}
