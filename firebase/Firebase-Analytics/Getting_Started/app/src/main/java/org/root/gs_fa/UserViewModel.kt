package org.root.gs_fa

import androidx.lifecycle.ViewModel
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class UserViewModel : ViewModel() {

    private val firebaseAnalytics = Firebase.analytics

    fun updateExperienceLevel(experience: String) {
        firebaseAnalytics.setUserProperty("experience_level", experience)
    }
}