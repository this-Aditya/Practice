package org.root.gs_fa

import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class DiceViewModel : ViewModel() {

    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    var die1 = 0
        private set

    var die2 = 0
        private set

    var sum = 0
        private set

    fun roll() {
        die1 = Random.nextInt(0, 5) + 1
        die2 = Random.nextInt(0, 5) + 1
        sum = die1 + die2

        firebaseAnalytics.logEvent("roll") {
            param("sum", sum.toLong())
            param("doubles", (die1 == die2).toString())
        }
    }
}